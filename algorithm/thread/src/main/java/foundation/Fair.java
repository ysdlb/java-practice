package foundation;

import java.util.concurrent.locks.ReentrantLock;

public class Fair {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(true);

        Runnable action = () -> {
            System.out.println("线程 "+Thread.currentThread().getName()+" 开始获取锁...");
            lock.lock();
            System.out.println("线程 "+Thread.currentThread().getName()+" 成功获取锁！");
            lock.unlock();
        };
        for (int i = 0; i < 10; i++) {   //建立10个线程
            new Thread(action, "T"+i).start();
        }
    }

}
