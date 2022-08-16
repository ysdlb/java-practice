package notify;

public class WaitNotifyTest {

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> {
            System.out.println("线程A等待获取lock锁");
            synchronized (lock) {
                try {
                    System.out.println("线程A获取了lock锁");
                    Thread.sleep(1000);
                    System.out.println("线程A将要运行lock.wait()方法进行等待");
                    lock.wait();
                    System.out.println("线程A等待结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            System.out.println("线程B等待获取lock锁");
            synchronized (lock) {
                System.out.println("线程B获取了lock锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程B将要运行lock.notify()方法进行通知");
                // 这里的notify将线程A从_WaitSet转移到_EntryList中，但不会释放锁
                // 如果没有notify的工作，线程A将永远呆在wait状态，即永远不会结束
                lock.notify();
                System.out.println("线程B等待结束");
            }
        }).start();
    }
}
