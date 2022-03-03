package performance.benchmark.lock;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@State(Scope.Benchmark)
public class SyncBenchmark {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(SyncBenchmark.class.getSimpleName()).forks(1).mode(Mode.Throughput)
                .warmupIterations(1).measurementIterations(2).build();
        new Runner(options).run();
    }

    int value = 10;
    private final Lock lock = new ReentrantLock();
    private final Object obj = new Object();

    @Benchmark
    public void sync(Blackhole bh) {
        int temp = 0;
        lock.lock();
        temp = value++;
        lock.unlock();
        bh.consume(temp);
    }

    @Benchmark
    public void lock(Blackhole bh) {
        int temp = 0;
        synchronized (obj) {
            temp = value++;
        }
        bh.consume(temp);
    }
}
