package ysdlb.property.collection.util;


import java.util.ArrayList;
import java.util.List;

public class ListObject {
    public static void main(String[] args) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }

        Test test = new Test();
        test.setList(ids);

        List<Integer> getlist = (List<Integer>) test.list;
        for (Integer i : getlist) {
            System.out.println(i);
        }

    }


    public static class Test {
        public List<?> list;
        public void setList(List<?> list) {
            this.list = list;
        }
    }
}
