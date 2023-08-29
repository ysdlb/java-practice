package deep_learn_jvm.bytecode_exec_engine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    public static void main(String[] args) throws Throwable {
        {
            MethodHandle methodHandle = getPrintlnMH(System.out);
            methodHandle.invokeExact("System.out");
        }
        {
            MethodHandle methodHandle = getPrintlnMH(new ClassA());
            methodHandle.invokeExact("ClassA");
        }
    }

    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    private static MethodHandle getPrintlnMH(Object receiver) {
        // MethodType: 方法类型，包含方法的返回值 (第一个参数) 和具体参数 (第二个及以后参数)
        MethodType methodType = MethodType.methodType(void.class, String.class);

        // 在指定类中查找符合给定方法名称、方法类型，并且符合调用权限的方法句柄
        try {
            return MethodHandles.lookup()
                    .findVirtual(receiver.getClass(), "println", methodType)
                    .bindTo(receiver);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
