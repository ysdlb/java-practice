package semantic.generic;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 隐式泛型转换
 */
public class ImplicitGenericCast {

    /**
     * 该方法适用于需要将 Consumer<T> 保存的情况
     * 不得已必须将其转换成 Consumer<Object>
     * @param supplier
     * @param consumer
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> void method(Supplier<T> supplier, Consumer<T> consumer) {
        Object object = supplier.get();

        ((Consumer<Object>)consumer).accept(object);
    }

    @Test
    void test() {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("thread: " + Thread.currentThread().getName());
            return 1;
        }, executor).thenAccept(i -> {
            System.out.println("thread: " + Thread.currentThread().getName());
        });
    }


}
