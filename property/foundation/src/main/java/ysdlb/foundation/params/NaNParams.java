package ysdlb.foundation.params;

import java.util.NavigableMap;

public class NaNParams {
    public Object[] func(Object... objects) {
        System.out.println("received: " + objects);
        for (Object object: objects) {
            System.out.println(object.toString());
        }
        return objects;
    }
    public static void funcTest() {
        NaNParams naN = new NaNParams();

        System.out.println("input:");
        naN.func();

        System.out.println("input: 1,2,3");
        naN.func(1,2,3);

        System.out.println("input: 1,2,3 but fun(fun())");
        naN.func(naN.func(1,2,3));

        // 这个重要
        System.out.println("input: 1,2,3 but fun(fun(), 4)");
        naN.func(naN.func(1,2,3), 4);

        System.out.println("input: [1,2,3]");
        naN.func(new int[]{1,2,3});

        System.out.println("input: null");
        naN.func(null);
    }

    public Object[] func2(int item, Object... objects) {
        StringBuilder buffer = new StringBuilder().append(item);
        for (int i = 1; i < objects.length; i++) {
            buffer.append("-").append(objects[i]);
        }
        System.out.println("string: " + buffer.toString());
        return objects;
    }
    /*
    public Object[] func2(Object... objects) {
        func2((Integer)objects[0], objects);
        return objects;
    }
     */
    public static void func2Test() {
        NaNParams naN = new NaNParams();
        naN.func2(1, 1, 2, 3, 4, 5);
    }

    public static void main(String[] args) {
        func2Test();
    }


}
