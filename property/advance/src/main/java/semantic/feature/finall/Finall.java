package semantic.feature.finall;

public class Finall {
    // 如果 a = 3, 运行的结果是什么
    public static int test4(int a) {
        try {
            a += 10;
            a = a / 0;
        } finally {
            a++;
            return a;
        }
    }
    public static String testStr3() {
        while (true) {
            try {
                return "foo";
            } finally {
                break;
            }
        }
        return "bar";
    }

    public static void main(String[] args) {
        int a = Finall.test4(3);
        System.out.println(a);

        String str = Finall.testStr3();
        System.out.println(str);
    }
}
