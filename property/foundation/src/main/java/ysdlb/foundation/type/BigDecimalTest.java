package ysdlb.foundation.type;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        float a1 = 0.9f;
        double a2 = 0.9;
        System.out.println(new BigDecimal(a1));
        System.out.println(new BigDecimal(a2));

        System.out.println(new BigDecimal("0.9"));
        System.out.println(BigDecimal.valueOf(0.9));
        System.out.println(new BigDecimal(Double.valueOf(0.9).toString()));
    }

}
