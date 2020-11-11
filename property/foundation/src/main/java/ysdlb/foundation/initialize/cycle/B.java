package ysdlb.foundation.initialize.cycle;

public class B {
    public A a;
    public B(A a) {
        System.out.println("construct B");
        this.a = a;
    }
}
