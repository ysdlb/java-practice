package ysdlb.foundation.string;

public class NullTest {
    public static final String FOR_TEST = "for_test";
    public static void main(String[] args) {
        String t1 = null;
        String t2 = "for_test";
        String t3 = new String("for_test");

        System.out.println("equals t1 -> " + FOR_TEST.equals(t1));
        System.out.println("equals t2 -> " + FOR_TEST.equals(t2));
        System.out.println("equals t3 -> " + FOR_TEST.equals(t3));

        System.out.println("== t1 -> " + (FOR_TEST == t1));
        System.out.println("== t2 -> " + (FOR_TEST == t2));
        System.out.println("== t3 -> " + (FOR_TEST == t3));
    }
}
