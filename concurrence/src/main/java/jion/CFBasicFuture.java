package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFBasicFuture {

    public static CompletableFuture<Integer> compute() {
        // final CompletableFuture<Integer> future = new CompletableFuture<>();
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->
        {
            return 100;
        });
        return future;
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();

        class Client extends Thread {
            CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {
                    // future.get()在等待执行结果时，程序会一直block，如果此时调用complete(T t)会立即执行。
                    // 上面的代码中future没有关联任何的Callback、线程池、异步任务等
                    // 如果客户端调用future.get就会一致傻等下去。
                    // 你可以通过下面的代码 (complete*) 完成一个计算或抛出一个异常，触发客户端的等待
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");

        // 可以看到我们并没有把f.complete(100);放在另外的线程中去执行，但是在大部分情况下我们可能会用一个线程池去执行这些异步任务。
        // CompletableFuture.complete()、CompletableFuture.completeExceptionally只能被调用一次。
        // 但是我们有两个后门方法可以重设这个值:obtrudeValue、obtrudeException，但是使用的时候要小心，
        // 因为complete已经触发了客户端，有可能导致客户端会得到不期望的结果
        boolean b = f.complete(100);
        System.out.println("=================" + b + "=====================");
        f.completeExceptionally(new Exception());
    }
}
