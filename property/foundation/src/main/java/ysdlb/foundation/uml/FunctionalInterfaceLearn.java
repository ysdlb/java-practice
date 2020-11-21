package ysdlb.foundation.uml;

public class FunctionalInterfaceLearn {
   public static void main(String[] args) {
      Sub sub = new Sub();
      sub.foo();
      sub.bar();
   }
}

interface MyInterface {
   void foo();
   void bar();
}

class Super {
   public void foo() {
      System.out.println("foo");
   }
}

class Sub extends Super implements MyInterface {
   public void bar() {
      System.out.println("bar");
   }
}
