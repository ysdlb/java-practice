package jion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CFCompleteMethod {

    @Test
    @DisplayName("no complete and async print")
    void noCompleteAsync() {
        CompletableFuture<Integer> future;

        future = CompletableFuture.supplyAsync(() -> {
            sleep(5000);
            return 1111;
        });

        asyncPrint(future);
    }

    @Test
    @DisplayName("no complete and sync print")
    void noCompleteSync() {
        CompletableFuture<Integer> future;

        future = CompletableFuture.supplyAsync(() -> {
            sleep(5000);
            return 1111;
        });

        syncPrint(future);
    }

    @Test
    @DisplayName("no complete and async print but main wait")
    void noCompleteAsyncMainWait() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future;

        future = CompletableFuture.supplyAsync(() -> {
            sleep(5000);
            return 1111;
        });

        asyncPrint(future);

        // 这里只要能阻塞，确保在 main 函数结束之前 future 能返回值，异步任务就行正常执行
        future.get();
        // sleep(11000);
    }

    @Test
    @DisplayName("method complete demonstration")
    void methodComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future;

        future = CompletableFuture.supplyAsync(() -> {
            sleep(10000);
            return 1111;
        });

        asyncPrint(future);

        future.get();
    }


    private static void sleep(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void asyncPrint(CompletableFuture<?> future) {
        new Thread(() -> syncPrint(future)).start();
    }

    private static void syncPrint(CompletableFuture<?> future) {
        try {
//            System.out.println("hhh");
//            sleep(5000);
            System.out.println("future get: " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


}
