package ysdlb.foundation.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimeTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 3, 1);
        System.out.println(localDate.minusDays(1));
    }

    @Test
    void max() {
        System.out.println(LocalDateTime.MAX);
        System.out.println(LocalDate.MAX);
        System.out.println(LocalTime.MAX);

        System.out.println();

        System.out.println(LocalDateTime.MIN);
        System.out.println(LocalDate.MIN);
        System.out.println(LocalTime.MIN);
    }

    @Test
    void zone() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("UTC+8"));
        System.out.println(dateTime);
    }
}
