package ysdlb.foundation;

import ysdlb.foundation.component.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsToMap {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(null, "A"));
        list.add(new Student(null, "B"));
        list.add(new Student(null, "C"));
        list.add(new Student(null, "D"));

        Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, t -> t, (n1, n2) -> n1));
        Set<Integer> set = list.stream().map(Student::getId).collect(Collectors.toSet());
        System.out.println(map.size());
        System.out.println(map.size());
        System.out.println("set is null ? :" + (set == null));
        System.out.println("set is empty ? :" + set.isEmpty());
    }
}
