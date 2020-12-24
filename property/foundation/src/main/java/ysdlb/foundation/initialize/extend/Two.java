package ysdlb.foundation.initialize.extend;

public class Two extends One {
    public Two() {
        System.out.println(this);
    }
    public Two(int i) {
        System.out.println(i);
        System.out.println(this);
    }
}
