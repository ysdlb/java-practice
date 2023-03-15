package org.ysdlb.bytebuddy.dep.advice;

public class Cooker {

    String name = "foo";

    public Cooker() {
        System.out.println();
        System.out.println("Im Cooker");
        System.out.println("Constructor();");
    }

    public void hello() {
        System.out.println("public void hello();");
    }

    public String makeFood(String foodName, int deskId, Double[] materialsPrice) {
        System.out.println(materialsPrice.getClass().getName());

        System.out.println("public String makeFood(String foodName, int deskId, Double[] materialsPrice)! ");

        return foodName + ":" + deskId + ":" + materialsPrice.length;
    }

    public static void taste(String foodName) {
        System.out.println("public static void taste(String foodName)! ");

    }
}