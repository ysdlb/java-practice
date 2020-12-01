package ysdlb.property.collection.failfast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<>();
        String b = map.put("2","2Value");
        String c = map.put("3","3Value");
        String a = map.put("1","1Value");
        String e = map.put("5","5Value");
        String f = map.put("6","6Value");
        String d = map.put("4","4Value");

        Set<String> set = map.keySet();

        System.out.println("初始");
        System.out.println(map.toString());
        System.out.println(set.toString());
        System.out.println("===============");
        System.out.println("set删除1");

        set.remove("1");
        System.out.println(map.toString());
        System.out.println(set.toString());
        System.out.println("===============");
        System.out.println("map删除3");

        map.remove("3");
        System.out.println(map.toString());
        System.out.println(set.toString());
        System.out.println("===============");

        Iterator<String> iterable = set.iterator();
        System.out.println("iterable 初始");
        System.out.println(map.toString());
        System.out.println(set.toString());

        System.out.println("===============");
        System.out.println("set删除2");
        set.remove("2");
        System.out.println(map.toString());
        System.out.println(set.toString());
        System.out.println("===============");

        while (iterable.hasNext()){
            System.out.println(iterable.next());
        }
    }
}
