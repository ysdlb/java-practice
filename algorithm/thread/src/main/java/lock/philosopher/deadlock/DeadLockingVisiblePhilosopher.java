package lock.philosopher.deadlock;

import lock.philosopher.AbstractPhilosopher;
import lock.philosopher.Chopstick;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁模拟的显式锁实现
 */
public class DeadLockingVisiblePhilosopher extends AbstractPhilosopher {
    /**
     * 为确保每个 Chopstick 实例有且仅有一个显式锁（不重复创建）与之对应
     * 这里的 map 必须采用 static 修饰
     */
    protected final static ConcurrentMap<Chopstick, ReentrantLock> LOCK_MAP = new ConcurrentHashMap<>();

    public DeadLockingVisiblePhilosopher(int id, Chopstick left, Chopstick right) {
        super(id, left, right);
        // 每个筷子对应唯一的锁实例
        LOCK_MAP.putIfAbsent(left, new ReentrantLock());
        LOCK_MAP.putIfAbsent(right, new ReentrantLock());
    }

    @Override
    public void eat() {
        if (pickUpChopstick(left) && pickUpChopstick(right)) {
            doEat();
            putDownChopstick(left);
            putDownChopstick(right);
        }
    }

    public boolean pickUpChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        lock.lock();
        System.out.println(String.format("%s is picking up %s on his %s...", this, chopstick, chopstick == left ? "left" : "right"));
        chopstick.pickUp();
        return true;
    }
    private void putDownChopstick(Chopstick chopstick) {
        final ReentrantLock lock = LOCK_MAP.get(chopstick);
        chopstick.putDown();
        lock.unlock();
    }
}
