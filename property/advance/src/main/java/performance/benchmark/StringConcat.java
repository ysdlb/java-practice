package performance.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.AverageTime, Mode.Throughput})
@Warmup(iterations = 10)
@Fork(value = 1)
@State(Scope.Benchmark)
public class StringConcat {

    private static final int LOOPS = 1000;

    @Benchmark
    public int testPlus() {
        int counter = 0;
        for (int i = 0; i < LOOPS; i++) {
            String s = "What do you get if you multiply " + counter + " by " + counter + "?";
            counter += s.length();
        }
        return counter;
    }

    @Benchmark
    public int testFormat() {
        int counter = 0;
        for (int i = 0; i < LOOPS; i++) {
            String s = String.format("What do you get if you multiply %d by %d?", counter, counter);
            counter += s.length();
        }
        return counter;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConcat.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
