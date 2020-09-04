/*
用于解释对象控制权
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
 */
public class ObjectMonitor {
    public static void main(String[] args) {
        final Object object = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
