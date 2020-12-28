package ysdlb.foundation.compute;

public class Bits {
    public static void main(String[] args) {
        int i = 1 << 31;
        int j = 1 << 0;
        System.out.println(i);
        System.out.println(j);
        System.out.println((1 << 31) >>> 31);
        System.out.println((1 << 31) >> 31);
    }
}
