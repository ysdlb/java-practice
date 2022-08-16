package aqs.clh.test;

import aqs.clh.CLHLock;
import aqs.clh.Lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class KFC {
    private final Lock lock = new CLHLock();
    private int i = 0;

    public void takeout() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": 拿了第" + ++i + "份外卖");
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final KFC kfc = new KFC();
        Executor executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            executor.execute(kfc::takeout);
        }
    }
}
