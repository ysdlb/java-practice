package ysdlb.foundation.type;

public class LongIntCompare {
    public static void main(String[] args) {
        long l = 5;
        int i = 4;
        System.out.println(l < i);

        Long L = 5L;
        Integer I = 4;
        System.out.println(L < I);
        System.out.println(I < L);
    }
}
