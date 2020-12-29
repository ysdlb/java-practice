package ysdlb.foundation.compute;

/**
 * 盲猜 java 位运算之前会先取模
 */
public class Bits {
    public static void main(String[] args) {
        System.out.println((1 << 31) >>> 31);
        System.out.println((1 << 31) >> 31);

        System.out.println();
        System.out.println(1 << 0);
        System.out.println(1 << 32);

        System.out.println();
        System.out.println((1 << 63) >>> 31);
        System.out.println((1 << 63) >> 31);

        System.out.println();
        System.out.println((1 << 63) >>> 63);
        System.out.println((1 << 63) >> 63);
    }
}
