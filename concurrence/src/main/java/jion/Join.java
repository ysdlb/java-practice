package jion;

public class Join {
    public static void main(String[] args) throws InterruptedException {

        MyThread threadTest = new MyThread();
        threadTest.start();

        //Thread.sleep(?);//因为不知道子线程要花的时间这里不知道填多少时间
        System.out.println("after MyThead start");
        threadTest.join();
        System.out.println("我想当threadTest对象执行完毕后我再执行");
    }

    static public class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("我想先执行");
        }

    }
}
