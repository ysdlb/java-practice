package lock.philosopher;

import lock.philosopher.deadlock.DeadLockingPhilosopher;
import lock.philosopher.deadlock.DeadLockingVisiblePhilosopher;
import lock.philosopher.deadresolve.FixedPhilosopher;
import lock.philosopher.deadresolve.GlobalLockPhilosopher;
import lock.philosopher.deadresolve.TryLockPhilosopher;

/**
 * 哲学家就餐问题代码的执行程序
 */
public class DinningPhilosopherProblem {
    public static void main(String[] args) {
        int numOfPhilosophers;
        numOfPhilosophers = args.length > 0 ? Integer.valueOf(args[0]) : 2;

        // 创建筷子
        Chopstick[] chopsticks = new Chopstick[numOfPhilosophers];
        for (int i = 0; i < numOfPhilosophers; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        // 创建并启动哲学家，每个哲学家拥有自己同id的筷子以及下一支筷子的使用权
        for (int i = 0; i < numOfPhilosophers; i++) {
            Philosopher philosopher = createPhilosopher(Type.TRY_LOCK, i, chopsticks[i], chopsticks[(i + 1) % numOfPhilosophers]);
            philosopher.start();
        }
    }

    private static Philosopher createPhilosopher(Type type, int id, Chopstick left, Chopstick right) {
        switch (type) {
            case SYNCHRONIZED:
                return new DeadLockingPhilosopher(id, left, right);
            case REENTRANT_LOCK:
                return new DeadLockingVisiblePhilosopher(id, left, right);
            case GLOBAL_LOCK:
                return new GlobalLockPhilosopher(id, left, right);
            case FIXED_LOCK:
                return new FixedPhilosopher(id, left, right);
            case TRY_LOCK:
                return new TryLockPhilosopher(id, left, right);

            default:
                return null;
        }
    }

    static enum Type {
        // synchronized 死锁模拟
        SYNCHRONIZED,
        // ReentrantLock 死锁模拟
        REENTRANT_LOCK,
        // 粗锁法规避死锁
        GLOBAL_LOCK,
        // 锁排序法规避死锁
        FIXED_LOCK,
        // try_lock 规避死锁
        TRY_LOCK
    }
}
