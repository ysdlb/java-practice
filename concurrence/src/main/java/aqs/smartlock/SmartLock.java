package aqs.smartlock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class SmartLock {

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            Thread thread = Thread.currentThread();
            if (getExclusiveOwnerThread() == thread) {
                System.out.println("\033[48;2;255;0;0m thread == " + thread.getName() + "\033[0m");
                return true;
            }

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(thread);
                return true;
            } else {
                return false;
            }
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }
    }

    private final Sync mSync;

    public SmartLock() {
        mSync = new Sync();
    }

    public void lock() {
        mSync.acquire(1);
    }

    public void unLock() {
        mSync.release(1);
    }

}
