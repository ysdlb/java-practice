package ysdlb.foundation.generic;

public class MultiLimit implements InterfaceA, InterfaceB {
    /**
     * 使用 & 符合设定多重边界 (Multi Bounds)
     */
    public static <T extends InterfaceA & InterfaceB> void test(T t) {}
}

interface InterfaceA {}
interface InterfaceB {}
