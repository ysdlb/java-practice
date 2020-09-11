import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long starttime = System.currentTimeMillis();

        //input1生成，需要耗费2秒
        FutureTask<Integer> input1_futuretask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 3;
            }
        });
        //input2生成， 需要耗费3秒
        FutureTask<Integer> input2_futuretask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 5;
            }
        });

        new Thread(input1_futuretask).start();
        new Thread(input2_futuretask).start();

        Integer integer1 = input1_futuretask.get();
        System.out.println("用时：" + (System.currentTimeMillis() - starttime));
        Integer integer2 = input2_futuretask.get();
        System.out.println("用时：" + (System.currentTimeMillis() - starttime));

        System.out.println(integer1 + integer2);
    }
}
