package ysdlb.property.collection.hash;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Long, Long> map = new TreeMap<>();
        map.put(0L, 1L);
        map.put(30L, 1L);
        map.put(40L, 1L);
        map.put(50L, 1L);
        map.put(20L, 1L);
        map.put(10L, 1L);

        System.out.println(map);
        AtomicLong sum = new AtomicLong();
        map.descendingMap().entrySet().forEach(entry -> {
            sum.addAndGet(entry.getValue());
            entry.setValue(sum.longValue());
        });
        System.out.println(map);
    }
}
