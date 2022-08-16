package lock.philosopher.deadresolve;

import lock.philosopher.AbstractPhilosopher;
import lock.philosopher.Chopstick;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 产生死锁的一个必要条件是占用并等待资源，然后循环等待资源，比如一个线程持有A，申请B；另一个线程持有B，申请A.
 * 锁排序方法规避死锁的原理是规定锁的申请只能先申请A，拿到A之后才能申请B，即规定了一个全局顺序。
 *
 * 对应到哲学家就餐里，可以将筷子 id 作为锁的全局序列（其他复杂的可以用 hash 值）
 * 然后规定任何哲学家拿筷子的顺序只能是 先拿序列前面的，然后才能拿序列后面的。
 * 比如5个人，5支筷子，
 * 哲学家0 只能先拿 筷子0 然后再拿 筷子1；
 * 哲学家4 只能先拿 筷子0 然后再拿 筷子4；
 * 而不是原来的先拿左边，再拿右边
 */
public class FixedPhilosopher extends AbstractPhilosopher {
    private Chopstick one;
    private Chopstick theOther;
    public FixedPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        // 确定两支筷子在全局序列中的先后
        int leftHash = left.hashCode();
        int rightHash = right.hashCode();
        if (leftHash < rightHash) {
            one = left;
            theOther = right;
        } else if (leftHash > rightHash) {
            one = right;
            theOther = left;
        } else {
            // 极低概率无法区分先后，这时候升级为粗粒度锁
            one = null;
            theOther = null;
        }
    }

    @Override
    public void eat() {
        if (null != one) {
            synchronized (one) {
                System.out.println(String.format("%s is picking up %s on his %s...", this, one, one == left ? "left" : "right"));
                one.pickUp();
                synchronized (theOther) {
                    System.out.println(String.format("%s is picking up %s on his %s...", this, theOther, theOther == left ? "left" : "right"));
                    theOther.pickUp();
                    doEat(); // 继续拿起右边的筷子才能吃饭
                    theOther.putDown();
                }
                one.putDown();
            }

        } else {
            // 退化为粗锁用法
            System.out.println(String.format("%s is picking up %s on his left...", this, left));
            left.pickUp();

            System.out.println(String.format("%s is picking up %s on his right...", this, right));
            right.pickUp();

            doEat(); // 继续拿起右边的筷子才能吃饭
            right.putDown();
            left.putDown();
        }
    }
}
