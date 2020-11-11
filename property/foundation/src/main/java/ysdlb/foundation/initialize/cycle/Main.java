package ysdlb.foundation.initialize.cycle;

public class Main {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a);
        System.out.println(a.b);
        System.out.println(a.b.a);
    }
}
