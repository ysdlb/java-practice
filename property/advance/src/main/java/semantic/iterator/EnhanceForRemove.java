package semantic.iterator;

import java.util.ArrayList;
import java.util.List;

public class EnhanceForRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        for (String temp : list) {
            if ("b".equals(temp)) {
                list.remove(temp);
            }
        }

        System.out.println(list);
    }
}
