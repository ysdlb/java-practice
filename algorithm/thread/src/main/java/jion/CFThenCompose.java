package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFThenCompose {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<String> f =  future.thenCompose(i -> CompletableFuture.supplyAsync(() -> (i * 10)))
                .thenCompose(i -> CompletableFuture.supplyAsync(i::doubleValue))
                .thenCompose(aDouble -> CompletableFuture.supplyAsync(() -> String.valueOf(aDouble)));
        System.out.println(f.get()); //1000
    }
}
