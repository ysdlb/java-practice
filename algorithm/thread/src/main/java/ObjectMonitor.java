/*
用于解释对象控制权
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
 */
public class ObjectMonitor {
    public static void main(String[] args) {
        System.out.println("main start");
        final Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread start");
                synchronized (object) {
                    System.out.println("thread catch lock");
                    try {
                        System.out.println("thread will release lock");
                        object.wait();
                        System.out.println("thread back");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        System.out.println("main finished");
        // notify必须要拿到锁之后才存在该锁上的对应wait状态的其他线程，才能把它们放进_EntryList中
        // 这样就会报java.lang.IllegalMonitorStateException异常
        object.notify();
    }
}
