package ysdlb.property.collection.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sort {
    int id;
    String name;
    public Sort(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        List<Sort> list = new ArrayList<Sort>();
        list.add(new Sort(0, "1"));
        list.add(new Sort(3, "2"));
        list.add(new Sort(2, "22"));
        list.add(new Sort(3, "11"));
        list.add(new Sort(3, "222"));
        // 简单排序
        // list.sort(Comparator.comparing(DoubleSort::getId));
        // 二次排序
        // list.sort(Comparator.comparing(DoubleSort::getId).thenComparing(DoubleSort::getName));
        list.add(new Sort(3, null));
        list.add(new Sort(4, null));
        list.add(new Sort(4, null));
        // 空指针处理空值放到前面
        // list.sort(Comparator.comparing(DoubleSort::getName, Comparator.nullsFirst(Comparator.naturalOrder())));

        //
        list.sort(Comparator.comparing(Sort::getName));
        list.forEach(e -> System.out.println(e.getId() + "," + e.getName()));
    }
}

