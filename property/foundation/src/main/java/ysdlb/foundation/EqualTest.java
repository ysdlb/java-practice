package ysdlb.foundation;

import ysdlb.foundation.component.Student;

public class EqualTest {
    public static void main(String[] args) {
        Student stu = new Student(null, "John");
        try {
            if (0 == stu.getId()) ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("0 == null failed");
        }
    }
}


