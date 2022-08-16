package jion.cf.code;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 未指定线程池的 CompletableFuture dep 由哪个线程执行取决于它的真实 trigger 是谁
 */
public class WhichThread {

    @Test
    void mainThread() {
        CompletableFuture<Void> src = CompletableFuture.completedFuture(null);

        CompletableFuture<Void> dep = src.thenAccept(i -> {
            System.out.println(Thread.currentThread().getName());
        });
    }

    @Test
    void sourceThread() {
        CompletableFuture<Void> src = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            // while (true);
            sleep(2000);
        });

        CompletableFuture<Void> dep = src.thenAccept(i -> {
            System.out.println(Thread.currentThread().getName());
        });


        try {
            dep.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
