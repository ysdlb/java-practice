package aqs.smartlock;

public class SmartAdder {

    private volatile int counter;

    private final SmartLock lock;

    public SmartAdder() {
        lock = new SmartLock();
    }

    public void increase() {
        lock.lock();
        try {
            counter ++;
        } finally {
            lock.unLock();
        }
    }

    public int getCounter() {
        return counter;
    }

}
