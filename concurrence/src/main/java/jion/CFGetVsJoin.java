package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFGetVsJoin {
    public static void main(String[] args) {
        forJoin();
    }

    public static void forJoin() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
        future.join();
    }
    public static void forGet() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
