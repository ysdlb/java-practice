package lock.philosopher.deadresolve;

import lock.philosopher.AbstractPhilosopher;
import lock.philosopher.Chopstick;

/**
 * 由于锁是一种满足 “互斥”、”不可抢夺“ 的资源，产生死锁的代码具有在持有一个锁的情况下去申请另一个锁的特征
 * 因此可以使用 “粗粒度锁” 来代替多个锁，以此来规避上述现象
 * 该方法被称为 “粗锁法”
 *
 * 局限是一次只有一个哲学家能够吃饭，期间别的哲学家只能思考或者等待筷子
 * 明显降低了并发性，很可能造成资源浪费
 */
public class GlobalLockPhilosopher extends AbstractPhilosopher {
    // 确保该字段为类属性，即所有该类实例共享一个对象
    private final static Object GLOBAL_LOCK = new Object();

    public GlobalLockPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (GLOBAL_LOCK) {
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
