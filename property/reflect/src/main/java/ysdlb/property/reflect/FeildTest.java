package ysdlb.property.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FeildTest {
    public static class P {
        String name = "hhhh";
        int age = 23;
    }

    public static void main (String[] args) throws java.lang.Exception {
        ArrayList<P> arr = new ArrayList<P>();
        arr.add(new P());
        arr.add(new P());
        arr.add(new P());


        for(P a : arr) {
            Field field = a.getClass().getDeclaredField("name");
            field.setAccessible(true);
            Object o = field.get(a);
            System.out.println(o.toString());
        }

        ArrayList o = (ArrayList)arr;
        System.out.println(o.getClass());
        ArrayList list = (ArrayList)o;
        for(Object a : o) {
            Field field = a.getClass().getDeclaredField("name");
            field.setAccessible(true);
            Object obj = field.get(a);
            System.out.println(obj.toString());
        }
    }
}
