package lock.philosopher.deadlock;

import lock.philosopher.AbstractPhilosopher;
import lock.philosopher.Chopstick;

/**
 * synchronized 死锁模拟
 */
public class DeadLockingPhilosopher extends AbstractPhilosopher {

    public DeadLockingPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public void eat() {
        synchronized (left) {
            System.out.println(String.format("%s is picking up %s on his left...", this, left));
            left.pickUp();

            synchronized (right) {
                System.out.println(String.format("%s is picking up %s on his right...", this, right));
                right.pickUp();
                doEat(); // 继续拿起右边的筷子才能吃饭
                right.putDown();
            }

            left.putDown();
        }
    }
}
