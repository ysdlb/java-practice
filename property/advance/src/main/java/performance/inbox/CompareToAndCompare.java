package performance.inbox;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动拆箱比显示的使用 .value 获取值要差一些
 */
public class CompareToAndCompare {
    public static void main(String[] args) {
        foreachTest();
        foreachTest2();
    }

    public static void test() {
        Long before = System.currentTimeMillis();
        Float a = 0.0f;
        Float b = 1.0f;
        for (int i = 0; i < 100000000; i++) {
            Float.compare(a, b);
        }
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void test2() {
        Long before = System.currentTimeMillis();
        Float a = 0.0f;
        Float b = 1.0f;
        for (int i = 0; i < 100000000; i++) {
            a.compareTo(b);
        }
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void foreachTest() {
        Long before = System.currentTimeMillis();
        List<Float> list = new ArrayList<>(10000);
        for (float i = 0; i < 10000; i++) {
            float r = list.stream().max(Float::compare).orElse(11111f);
            System.out.print(String.format("\r%f", r));
            list.add(i);
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void foreachTest2() {
        Long before = System.currentTimeMillis();
        List<Float> list = new ArrayList<>(10000);
        for (float i = 0; i < 10000; i++) {
            float r = list.stream().max(Float::compareTo).orElse(11111f);
            System.out.print(String.format("\r%f", r));
            list.add(i);
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - before);
    }
}
