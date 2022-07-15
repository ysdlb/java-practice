package reflect.foundation;


import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import org.junit.jupiter.api.Test;

/**
 * jdk9 之后，运行时要将 这个包 通过 module 导出来， jvm 参数如下：
 * --add-opens java.base/jdk.internal.reflect=ALL-UNNAMED
 */
public class CallerSensitiveTest {

    /*有CallerSensitive注解*/

    /**
     * {@link CallerSensitive @CallerSensitive} 有个特殊之处，必须由启动类 classloader 加载（如rt.jar ），才可以被识别。<br>
     * 所以rt.jar下面的注解可以正常使用。<br>
     *
     * 开发者自己写的 @CallerSensitive 不可以被识别。 但是，可以利用jvm参数 -Xbootclasspath/a: path 假装自己的程序是启动类。
     */
    @Test
    @CallerSensitive
    void withCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

    /*无注解直接调用*/
    @Test
    void noCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }
}
