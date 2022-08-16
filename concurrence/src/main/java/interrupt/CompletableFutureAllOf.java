package interrupt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 超时并不会对线程造成任何影响，该跑的还是在跑
 * 这样造成的隐患就是，对一个或一组 修改某个map或list的线程 进行 join
 * 如果这个 join 因为超时结束，
 * 如果在后面对上述的 map或list 进行 for each 遍历的时候，map或list 被还在跑的线程修改了
 * for each 就会抛出 ConcurrentModificationException 异样
 */
public class CompletableFutureAllOf {
    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) {

        CompletableFuture<Void> a_future = CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted())
                    System.out.println("a is interrupt");
                put("a" + i, "a_" + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        CompletableFuture<Void> future = CompletableFuture.allOf(a_future);

        try {
            future.get(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        // for each 遍历启动后，map 再有变化会抛出 ConcurrentModificationException 异常
        for (Map.Entry<String, String> entry: map.entrySet()) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            entry.getValue();
        }

        // 主线程结束后，子线程也没了，等待子线程全部结束
        try {
            Thread.sleep(3000);
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void put(String key, String value) {
        System.out.println("put: " + key + "-" + value);
        map.put(key, value);
    }
}
