package ysdlb.property.collection.util;

import java.util.ArrayList;
import java.util.List;

public class SetOperation {
    public static void main(String[] args) {
        and();
        or();
        negation();
    }

    private static void and() {
        List<String> collection1 = new ArrayList<>();
        List<String> collection2 = new ArrayList<>();

        collection1.add("a");
        collection1.add("b");
        collection1.add("c");

        collection2.add("c");
        collection2.add("d");
        collection2.add("e");

        collection1.retainAll(collection2);

        System.out.println("交集是： " + collection1);
    }

    private static void or() {
        List<String> collection1 = new ArrayList<>();
        List<String> collection2 = new ArrayList<>();

        collection1.add("a");
        collection1.add("b");
        collection1.add("c");

        collection2.add("c");
        collection2.add("d");
        collection2.add("e");

        collection1.addAll(collection2);

        System.out.println("并集是： " + collection1);
    }

    private static void negation() {
        List<String> collection1 = new ArrayList<>();
        List<String> collection2 = new ArrayList<>();

        collection1.add("a");
        collection1.add("b");
        collection1.add("c");

        collection2.add("c");
        collection2.add("d");
        collection2.add("e");

        collection1.removeAll(collection2);

        System.out.println("差集是： " + collection1);
    }
}
