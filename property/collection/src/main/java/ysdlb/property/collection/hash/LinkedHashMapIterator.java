package ysdlb.property.collection.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapIterator {
    public static void main(String[] args) {
        Map map = new LinkedHashMap<String, String>();

        map.put("apple", "苹果");
        map.put("banana", "香蕉");
        map.put("peach", "桃子");
        map.put("watermelon", "西瓜");

        // Iterator遍历
        System.out.println("Iterator遍历: ");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
        System.out.println("Iterator遍历结束");
        // Iterator遍历结束

        System.out.println();

        // ForEach遍历
        System.out.println("ForEach遍历");
        map.forEach((k, v) -> System.out.println(k +  "---" + v));
        System.out.println("ForEach遍历结束");
        // ForEach遍历结束
    }
}
