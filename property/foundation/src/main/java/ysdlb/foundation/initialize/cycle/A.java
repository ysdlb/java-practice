package ysdlb.foundation.initialize.cycle;

public class A {
    public final B b = new B(this);

    public A() {
        System.out.println("construct A");
    }
}
