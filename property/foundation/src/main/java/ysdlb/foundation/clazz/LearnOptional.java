package ysdlb.foundation.clazz;

import ysdlb.foundation.component.Student;

import java.util.Objects;
import java.util.Optional;

public class LearnOptional {
    public static void main(String[] args) {
        Student stu = new Student(111, "gqhan");

        Optional<Student> student = Optional.ofNullable(stu);

        Student s1 = null;
        Student s2 = null;
        System.out.println(s1 == s2);
        System.out.println(Objects.equals(s1, s2));
    }
}
