package jion.cf.code;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletionCode {
    public static void main(String[] args) {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "one");

        CompletableFuture<Void> f3 = f1.thenAcceptBoth(f2, (i, s) -> System.out.println(s + i));

        System.out.println("end");
    }

    @Test
    void simple() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(100 * 1000);
            return 1;
        });

        CompletableFuture<Integer> f2 = f1.thenApply(i -> ++i);
        CompletableFuture<Integer> f3 = f2.thenApply(i -> ++i);
        CompletableFuture<Integer> f4 = f3.thenApply(i -> ++i);
        CompletableFuture<Integer> f5 = f4.thenApply(i -> ++i);

        try {
            f5.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    void notSimple() throws ExecutionException, InterruptedException {
        final boolean[] tag = new boolean[]{true};
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            while (tag[0]);
            System.out.println("future task end, and run stack.tryFire()");
        });

        // ************ future -> stack **********
        CompletableFuture<Void> f_a = future.thenRun(() -> System.out.println("this is f_a"));
        CompletableFuture<Void> f_b = future.thenRun(() -> System.out.println("this is f_b"));

        // ************* f_a -> stack ***********
        CompletableFuture<Void> f_a_1 = f_a.thenRun(() -> System.out.println("this is f_a_1"));
        CompletableFuture<Void> f_a_2 = f_a.thenRun(() -> System.out.println("this is f_a_2"));
        CompletableFuture<Void> f_a_3 = f_a.thenRun(() -> System.out.println("this is f_a_3"));
        CompletableFuture<Void> f_a_4 = f_a.thenRun(() -> System.out.println("this is f_a_4"));
        CompletableFuture<Void> f_a_5 = f_a.thenRun(() -> System.out.println("this is f_a_5"));

        // ************* f_b -> stack ***********
        CompletableFuture<Void> f_b_1 = f_b.thenRun(() -> System.out.println("this is f_b_1"));
        CompletableFuture<Void> f_b_2 = f_b.thenRun(() -> System.out.println("this is f_b_2"));
        CompletableFuture<Void> f_b_3 = f_b.thenRun(() -> System.out.println("this is f_b_3"));
        CompletableFuture<Void> f_b_4 = f_b.thenRun(() -> System.out.println("this is f_b_4"));
        CompletableFuture<Void> f_b_5 = f_b.thenRun(() -> System.out.println("this is f_b_5"));

        tag[0] = false;

        f_b_1.get();

    }

    private static void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
