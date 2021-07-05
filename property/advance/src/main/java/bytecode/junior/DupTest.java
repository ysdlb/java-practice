package bytecode.junior;

public class DupTest {

    private void dup(Integer x) {
        System.out.println(x++);
        System.out.println(x);
    }

    public static void main(String[] args) {
        new DupTest().dup(5);
    }
}
