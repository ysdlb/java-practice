package ysdlb.property.collection.util;

import ysdlb.property.clzobj.clazz.Student;

import java.util.Arrays;

public class SystemCopyOf {
    public static void main(String[] args) {
        Student[] stu1 = new Student[]{new Student(1, "A"), new Student(2, "B")};
        Student[] stu2 = new Student[2];
        System.arraycopy(stu1, 0, stu2, 0, 2);
        Arrays.stream(stu1).forEach(System.out::println);
        stu2[0].setName("我是A");
        Arrays.stream(stu1).forEach(System.out::println);

        System.out.println("constant string");
        String[] str1 = new String[]{new String("A"), new String("B")};
        String[] str2 = new String[2];
        System.arraycopy(str1, 0, str2, 0, 2);
        Arrays.stream(str1).forEach(System.out::println);
        str2[0] = ("我是A");
        Arrays.stream(str1).forEach(System.out::println);
    }
}
