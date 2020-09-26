package ysdlb.property.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 并行流与普通流大写转小写的速度对比
 */
public class ParallelStream {
    public static void main(String[] args) {
        Function<Stream<String>, Long> timeOperation = stream -> {
            long time1 = System.nanoTime();
            final List<String> list =
                    stream
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());
            long time2 = System.nanoTime();
            return time2 - time1;
        };

        Consumer<Stream<String>> printTime = stream ->
                System.out.println(timeOperation.apply(stream) / 1000000f);

        String[] array = new String[1000000];
        Arrays.fill(array, "AbabagalamagA");

        printTime.accept(Arrays.stream(array));            // prints around 600
        printTime.accept(Arrays.stream(array).parallel()); // prints around 900
    }
}
