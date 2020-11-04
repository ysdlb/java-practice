package nullable.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalLow {

    public static void main(String[] args) {
        Map<Integer, String> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        map1.put(1, "a");
        map1.put(2, null);
        map1.put(3, "c");
        map1.put(4, null);

        map2.put("a", 1);
        map2.put("c", null);

        for (int i = 1; i <= 4; i++) {
            Integer re = Optional.of(Integer.valueOf(i))
                    .map(id -> map1.get(id))
                    .map(str -> map2.get(str))
                    .orElse(999);

            System.out.println(re);
        }
    }


}
