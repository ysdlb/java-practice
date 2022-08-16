package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 如果没有 Future::get 阻塞
 * 即使 子线程 未执行完, 父线程 main 也会退出
 */
public class CFAllOf {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 executed");
            return "future1 finished!";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 executed");
            return "future2 finished!";
        });

        CompletableFuture<Void> future3 = future2.thenAcceptAsync(s -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {}
            System.out.println(s);
        });

        CompletableFuture<Void> futures = CompletableFuture.allOf(future1, future2);

        System.out.println("in main will execute allOf get");

        futures.get();

        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());

        // future3.get(3, TimeUnit.SECONDS);
        future3.join();
    }
}
