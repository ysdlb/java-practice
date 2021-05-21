package performance.benchmark;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

class A {}
class B extends A {}

/**
 * <a href=https://stackoverflow.com/questions/103564/the-performance-impact-of-using-instanceof-in-java>instanceof 性能问题</a>
 *
 * <p>In Java 1.8 instanceof is the fastest approach, although getClass() is very close.
 *
 */

// | Operation  | Runtime in nanoseconds per operation | Relative to instanceof |
// |------------|--------------------------------------|------------------------|
// | INSTANCEOF | 39,598 ± 0,022 ns/op                 | 100,00 %               |
// | GETCLASS   | 39,687 ± 0,021 ns/op                 | 100,22 %               |
// | TYPE       | 46,295 ± 0,026 ns/op                 | 116,91 %               |
// | OO         | 48,078 ± 0,026 ns/op                 | 121,42 %               |
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class TypeJudge {

    public static final Object a = new A();
    public static final Object b = new B();

    @Benchmark
    public boolean testInstanceOf() {
        return b instanceof A;
    }

    @Benchmark
    public boolean testIsInstance() {
        return A.class.isInstance(b);
    }

    @Benchmark
    public boolean testIsAssignableFrom() {
        return A.class.isAssignableFrom(b.getClass());
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TypeJudge.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}