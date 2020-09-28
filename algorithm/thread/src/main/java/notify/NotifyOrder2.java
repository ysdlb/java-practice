package notify;

public class NotifyOrder2 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 100; i += 2) {
                synchronized (o) {
                    System.out.println("ThreadA: " + i);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("ThreadA final!");
        });

        Thread threadB = new Thread(() -> {
            for (int i = 1; i < 100; i += 2) {
                synchronized (o) {
                    System.out.println("ThreadB: " + i);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("ThreadB final!");
        });

        threadA.start();
        // threadA.join(); // 这里A等待B其他线程的notify，mian获得了A的锁，等待A的结束，B等待main的执行
        threadB.start();
        Thread.sleep(3000);
        System.out.println("sleep");
        threadA.join();
        threadB.join();
        System.out.println("over");
    }
}
