package ysdlb.foundation.time;

import java.time.LocalDate;

public class TimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 3, 1);
        System.out.println(localDate.minusDays(1));
    }
}
