package ysdlb.property.collection.hash;

import java.util.HashMap;
import java.util.Map;

public class MapGetBug {
    public static void main(String[] args) {
        Integer integer = Integer.valueOf(8);
        Long key = Long.valueOf(8);
        Map map = new HashMap<Integer, String>();
        map.put(integer, "test");
        System.out.println(map.get(key));
    }
}
