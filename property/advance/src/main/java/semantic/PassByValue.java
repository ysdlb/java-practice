package semantic;

public class PassByValue {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(a);
        add(a);
        System.out.println(a);
    }
    public static void add(int i) {
        i = i + 1;
    }
}
