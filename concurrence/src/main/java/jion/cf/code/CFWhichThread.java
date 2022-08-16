package jion.cf.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFWhichThread {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (int i = 1; i < 1; i++) {
            final int I = i;
            CompletableFuture<Void> future = new CompletableFuture<>();
            CompletableFuture.runAsync(() -> consumer(I), executorService)
                    .whenComplete((e, v) -> {
                        if (v == null) {
                            System.out.println("no error");
                            future.complete(null);
                        } else {
                            System.out.println("complete error");
                            future.completeExceptionally(v);
                        }
                    });
            futures.add(future);
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
//        allOf.exceptionally(e -> {
//            log.error("error: ", e);
//            return null;
//        });
        try {
            allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("error: ");
            e.printStackTrace();
        }
        System.out.println("end");
    }

    public static void consumer(int i) {
        int random = new Random().nextInt(10);
        try {
            Thread.sleep(random * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i % 2 == 0)
            throw new RuntimeException("consumer i -> " + i);
    }
}
