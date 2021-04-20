package performance.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *
 * <a href = "https://stackoverflow.com/questions/58863128/unexpected-varhandle-performance-4x-slower-than-alternatives">
 *     Unexpected VarHandle performance (4X slower than alternatives)
 * </a>
 */
@State(Scope.Thread)
@Fork(value = 1, jvmArgs = {"-Xms256m", "-Xmx256m", "-XX:+UseG1GC"})
@Warmup(iterations = 3, time = 3)
@Measurement(iterations = 5, time = 5)
@Threads(4)
public class VarHandleAtomicInt {

    // array option
    private final AtomicIntegerArray array = new AtomicIntegerArray(1);

    // vanilla AtomicInteger
    private final AtomicInteger counter = new AtomicInteger();

    // count field and its VarHandle
    private volatile int count;
    private static final VarHandle COUNT;

    // count2 field and its field updater
    private volatile int count2;
    private static final AtomicIntegerFieldUpdater<VarHandleAtomicInt> COUNT2 ;

    static {
        try {

            COUNT = MethodHandles.lookup()
                    .findVarHandle(VarHandleAtomicInt.class, "count", Integer.TYPE);
            COUNT2 = AtomicIntegerFieldUpdater.newUpdater(VarHandleAtomicInt.class, "count2");
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(e);
        }
    }

    @Benchmark
    public void atomic(Blackhole bh) {
        bh.consume(counter.getAndAdd(1));
    }

    @Benchmark
    public void atomicArray(Blackhole bh) {
        bh.consume(array.getAndAdd(0, 1));
    }

    /**
     * {@link VarHandle#getAndAdd(Object...)} 返回的是 Object，因为方法重载的原因
     * 实际调用的可能是 {@link Blackhole#consume(Object)}
     * 所以 返回的实际对象 int 类会被装箱为 Integer，格外增加了开销
     * 为了避免，这里要将返回值强转为 int
     *
     * 存在拆装箱的时候
     *   Benchmark                         Mode  Cnt          Score          Error  Units
     *   VarHandleAtomicInt.atomic        thrpt    5  617588117.314 ±  9468855.241  ops/s
     *   VarHandleAtomicInt.atomicArray   thrpt    5  474550921.473 ±  9857208.260  ops/s
     *   VarHandleAtomicInt.fieldUpdater  thrpt    5  611730324.595 ± 26271282.636  ops/s
     *   VarHandleAtomicInt.varHandle     thrpt    5  216382396.990 ± 13465238.553  ops/s
     *
     * 不存在拆装箱的时候
     *   Benchmark                         Mode  Cnt          Score          Error  Units
     *   VarHandleAtomicInt.atomic        thrpt    5  618517633.011 ±  6532616.385  ops/s
     *   VarHandleAtomicInt.atomicArray   thrpt    5  483843585.199 ±  2573086.695  ops/s
     *   VarHandleAtomicInt.fieldUpdater  thrpt    5  616491591.979 ± 13980969.239  ops/s
     *   VarHandleAtomicInt.varHandle     thrpt    5  612441917.954 ±  4679794.065  ops/s
     */

    @Benchmark
    public void varHandle(Blackhole bh) {
        bh.consume((int)COUNT.getAndAdd(this, 1));
    }

    @Benchmark
    public void fieldUpdater(Blackhole bh) {
        bh.consume(COUNT2.getAndAdd(this, 1));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(VarHandleAtomicInt.class.getSimpleName())
                .build();
        new Runner(options).run();
    }


}
