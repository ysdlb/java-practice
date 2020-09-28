package notify;

/**
 * 当线程执行结束以后，会触发两个事情：
 * 第一个是设置 native 线程对象为 null
 * 第二个是通过 notifyAll() 方法，唤醒等待在该线程对象上 等待集（WaitSet）中的线程。
 * 本文件是对第二个事情的验证
 */
public class AutoNotify {
    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            System.out.println("ThreadA start!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA final!");
        });

        Thread threadB = new Thread(() -> {
            System.out.println("ThreadB start");
            synchronized (threadA) {
                try {
                    threadA.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ThreadB final!");
        });

        threadB.start();
        // threadB.join(); // 由于threadA线程没有开始，当然也不会结束销毁，不会在最后隐式调用notifyAll，所以threadB永远不会被唤醒
        threadA.start();
    }
}
