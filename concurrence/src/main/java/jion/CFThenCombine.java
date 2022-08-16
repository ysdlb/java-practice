package jion;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @see CFThenAcceptBoth
 */
public class CFThenCombine {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 100;
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "abc";
        });
        CompletableFuture<String> f =  future.thenCombine(future2, (x, y) -> y + "-" + x);
        System.out.println(f.get()); //abc-100

    }
}
