package ysdlb.foundation.type;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class BigIntegerTest {

    public String[] intArrToStrArr(int[] ex) {
        String[] strArr = new String[ex.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(ex[i]);
        }
        return strArr;
    }

    @Test
    public void doSomething() {
        int[] bits = new int[64];
        bits[62] = 1;
        bits[47] = 1;
        String[] strArr = intArrToStrArr(bits);
        String bin = String.join("", strArr);
        System.out.println(bin);
        BigInteger bigInteger = new BigInteger(bin, 2);
        long l = bigInteger.longValue();
        String r = String.format("%016X", l);
        System.out.println(r);

        // 恢复数据
        BigInteger back = new BigInteger(r, 16);
        long back_l = back.longValue();
        System.out.println(Long.toBinaryString(back_l));
    }

}
