
public class NotifyOrder {
    private final Object flag = new Object();

    public static void main(String[] args) {
        NotifyOrder threadTest = new NotifyOrder();
        ThreadA threadA = threadTest.new ThreadA();
        threadA.start();
        ThreadB threadB = threadTest.new ThreadB();
        threadB.start();
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (flag) {
                for (int i = 1; i <= 100; i += 2) {
                    flag.notify();
                    System.out.println("ThreadA: " + i); // 奇数
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (flag) {
                for (int i = 2; i <= 100; i += 2) {
                    flag.notify();
                    System.out.println("ThreadB: " + i); // 奇数
                    if (i == 100) {
                        // 当输出了最后一个数字的时候，不能再wait了
                        break;
                    }
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
