package ysdlb.property.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Set<String> a = list.stream().map(String::toUpperCase).collect(Collectors.toSet());
        a.forEach(System.out::println);
        Set<String> set = new HashSet<>();
        set.addAll(a);
    }
}
