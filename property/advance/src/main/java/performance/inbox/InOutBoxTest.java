package performance.inbox;

public class InOutBoxTest {
    public static void test() {
        Long before = System.currentTimeMillis();
        int i = 0;
        for (;i < 100000000; i++);
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void test2() {
        Long before = System.currentTimeMillis();
        Integer i = 0;
        for (;i < 100000000; i++);
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void compareTest() {
        Long before = System.currentTimeMillis();
        float a = 0.0f;
        float b = 1.0f;
        for (int i = 0; i < 100000000; i++) {
            Float.compare(a, b);
        }
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void compareTest2() {
        Long before = System.currentTimeMillis();
        Float a = 0.0f;
        Float b = 1.0f;
        for (int i = 0; i < 100000000; i++) {
            Float.compare(a, b);
        }
        System.out.println(System.currentTimeMillis() - before);
    }

    public static void main(String[] args) {
        test();
        test2();

        compareTest();
        compareTest2();
    }
}
