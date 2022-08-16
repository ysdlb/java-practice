package lock.philosopher.deadresolve;

import lock.philosopher.Chopstick;
import lock.philosopher.deadlock.DeadLockingVisiblePhilosopher;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock::tryLock() 方法规避死锁
 * 如果一个线程在给定时间内未成功申请到需要的锁A，
 * 且此时该线程持有另一个成功申请到的锁B
 * 那么该线程释放掉锁B
 *
 * 如果一个线程未持有任何一把锁，那么本次循环不做操作，进入下一次循环
 */
public class TryLockPhilosopher extends DeadLockingVisiblePhilosopher {
    public TryLockPhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
    }

    @Override
    public boolean pickUpChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        boolean lockAcquired = false;

        try {
            // 减小这里的 timeout 可以增大 获取锁失败的概率
            lockAcquired = lock.tryLock(10, TimeUnit.MILLISECONDS);
            if (!lockAcquired) {
                System.out.println(String.format("%s is trying to pick up %s on his %s, but it is held by other philosopher...",
                        this, chopstick, chopstick == left ? "left" : "right"));

                // 若线程已经拿起另一支筷子，则使其放下
                Chopstick another = chopstick == left ? right : left;
                if (LOCK_MAP.get(another).isHeldByCurrentThread()) {
                    another.putDown();
                    LOCK_MAP.get(another).unlock();
                    System.out.println(String.format("%s has putted down %s on his %s...", this, another, another == left ? "left" : "right"));
                }
                return false;
            }

            System.out.println(String.format("%s is picking up %s on his %s...", this, chopstick, chopstick == left ? "left" : "right"));
            chopstick.pickUp();

        } catch (InterruptedException e) {
            if (lockAcquired) {
                lock.unlock();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
