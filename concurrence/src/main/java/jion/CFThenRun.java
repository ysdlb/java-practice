package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFThenRun {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<Void> f =  future.thenRun(() -> System.out.println("finished"));
        System.out.println(f.get());

    }

}
