package ysdlb.property.collection.hash;

import ysdlb.property.clzobj.clazz.Student;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MaptoList {
    public static void main(String[] args) {
        LinkedList<Student> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student(i, "stu" + i);
            list.add(student);
        }

        // list转map
        Map map = new HashMap<Integer, Student>();
        for (Student stu: list) {
            map.put(stu.getId(), stu);
        }

        // 对map修改是否会影响到list
        Student stu;
        stu = (Student) map.get(0);
        stu.setName("我修改过了");

        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}
