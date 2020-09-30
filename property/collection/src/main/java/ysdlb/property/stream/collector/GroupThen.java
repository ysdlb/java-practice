package ysdlb.property.stream.collector;

import ysdlb.property.clzobj.clazz.Man;

import java.util.*;
import java.util.stream.Collectors;

public class GroupThen {
    public static void main(String[] args) {
        List<Man> list = new ArrayList<>();
        list.add(new Man(1, 18,"one1"));
        list.add(new Man(2, 18,"one2"));
        list.add(new Man(3, 18,"one3"));
        list.add(new Man(4, 19,"one4"));
        list.add(new Man(5, 19,"one5"));
        list.add(new Man(6, 22,"one6"));
        list.add(new Man(7, 22,"one7"));
        list.add(new Man(8, 22,"one8"));

        // 分组去第一个数据
        Map<Integer, Man> map = list.stream().collect(Collectors.groupingBy(
                Man::getAge,
                Collectors.collectingAndThen(Collectors.toList(), v -> v.get(0))
        ));
        map.values().forEach(System.out::println);

        // 分组取最大的数据
        System.out.println();
        Map<Integer, Man> map1 = list.stream().collect(Collectors.groupingBy(
                Man::getAge,
                Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> c1.getId() > c2.getId() ? c1 : c2), Optional::get)
        ));
        map1.values().forEach(System.out::println);
    }
}
