package aqs.smartlock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartLockTest {

    @Test
    void smartAdderTest() {
        int threadCount = 20;
        int addCount = 100_000;

        SmartAdder smartAdder = new SmartAdder();
        CountDownLatch latch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < addCount; j++) {
                    smartAdder.increase();
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count -> " + smartAdder.getCounter());
        executorService.shutdown();
    }
}
