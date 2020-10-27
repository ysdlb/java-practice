package ysdlb.foundation.initialize;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DoubleBraceTest {

    private void doubleBrace() {
        Map source = new HashMap(){{
            put("firstName", "John");
            put("lastName", "Smith");
            put("organizations", new HashMap(){{
                put("0", new HashMap(){{
                    put("id", "1234");
                }});
                put("abc", new HashMap(){{
                    put("id", "5678");
                }});
            }});
        }};
    }

    /**
     public void test() {
        HashSet var10000 = new HashSet<String>() {{
            this.add("a");
            this.add("b");
        }};
     }
     */
    public void test() {
        Set<String> flavors = new HashSet<String>() {{
            add("a");
            add("b");
        }};
    }

    public void add(Object o) {
        System.out.println("outer " + o);
    }

    public Set<String> makeSet() {
        return new HashSet<String>() {
            {
                add("hello"); // HashSet
                DoubleBraceTest.this.add("hello1"); // outer instance
            }
            @Override
            public boolean add(String s) {
                System.out.println("inner " + s);
                return true;
            }
        };
    }

    public static void main(String[] args) {
        DoubleBraceTest doubleBraceTest =  new DoubleBraceTest();
        doubleBraceTest.doubleBrace();
        doubleBraceTest.test();
        System.out.println(doubleBraceTest.makeSet());
    }
}
