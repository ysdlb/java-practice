package jion;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CFSupplierBasic {

    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();

    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }

    public static void main(String[] args) throws Exception {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CFSupplierBasic::getMoreData);

        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println("in CompletableFuture::whenComplete");
            System.out.println("? super T is " + v);
            System.out.println("? super Throwable is " + e);
        }).whenComplete((v, e) -> {
            System.out.println("another whenComplete, the T is " + v);
        });

        System.out.println("now outside");
        System.out.println("f.get() -> " + f.get());
    }
}
