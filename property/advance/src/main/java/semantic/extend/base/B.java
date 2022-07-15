package semantic.extend.base;

public class B extends A{

     public B(int a) {}

    public static B getInstance() {
        return new B(1);
    }
}
