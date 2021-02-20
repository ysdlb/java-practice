package semantic.feature.character;

import org.junit.jupiter.api.Test;

public class CharTest {
    @Test
    void charTest() {
        char a;
        a = Character.MIN_HIGH_SURROGATE;
        System.out.println(String.format("char %c, value %d", a, (int)a));

        a = Character.MAX_LOW_SURROGATE;
        System.out.println(String.format("char %c, value %d", a, (int)a));

        for (a = Character.MIN_HIGH_SURROGATE + 1; a < Character.MAX_LOW_SURROGATE; a++) {
            System.out.print(a);
            System.out.print(" ");
        }
    }
}
