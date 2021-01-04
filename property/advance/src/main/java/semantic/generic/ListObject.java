package semantic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * add1 vs add2<br>
 * you can pass a List<String> to a parameter of type List, you can't pass it to a parameter of type List<Object><br>
 * There are subtyping rules for generics:<br>
 *
 * List<String> is a subtype of the raw type List, but not of the parameterized type List<Object><br>
 *
 * As a consequence<br>
 * you lose <b>type safety</b> if you use raw type like List, but not if you use a parameterized type like List<Object><br>
 *
 */
public class ListObject {

    public static void add1(List list) {
        list.add(new Object());
    }
    public static void add2(List<Object> list) {
        list.add(new Object());
    }

    public static void main(String[] args) {
        List list1 = new ArrayList();
        List<Object> list2 = new ArrayList<>();

        /**
         * you can't add just any arbitrary object to a List<?>
         */
        List<?> list3 = new ArrayList<>();

        Object a = new Object();
        list1.add(a);
        list2.add(a);
        // list3.add(a); // 未知的类型约束，报错

        List<String> b = new ArrayList<>(); b.add("1");
        list1.add(b);
        list2.add(b);
        // list3.add(b);   // 同上，capture of ?

        add1(b);
//        add2(b);        // error: required List<Object>, provided List<String>;
    }

}
