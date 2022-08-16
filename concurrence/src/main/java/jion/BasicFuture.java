package jion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<Integer>> futureList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            final int r = i;
            Future<Integer> future = executorService.submit(() -> {
                return r;
            });
            futureList.add(future);
        }

        for (Future<Integer> future : futureList) {
            System.out.println(future.get());
        }

        // 关闭线程池，否则程序不会退出
        executorService.shutdown();
    }
}
