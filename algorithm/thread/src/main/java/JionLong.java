public class JionLong {

    public static void main(String[] args) {
        try {
            MyThread threadTest = new MyThread();
            threadTest.start();

            threadTest.join(200000);// 只等2秒
            //Thread.sleep(2000);

            System.out.println("  end timer=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("begin Timer=" + System.currentTimeMillis());
                Thread.sleep(100000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

