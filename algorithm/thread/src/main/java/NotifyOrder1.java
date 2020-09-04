public class NotifyOrder1 {
    public static void main(String[] args) {
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
            synchronized (o) {
                o.notify();
            }
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
        threadB.start();
    }
}
