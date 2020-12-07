package semantic.closure;

import java.util.ArrayList;
import java.util.List;

public class CaptureByValue {
    public static void main(String[] args) {
        String[] names = {"Peter", "Paul", "Mary"};
        List<Runnable> runners = new ArrayList<>();

        int[] ind = {0};
        for (int i = 0; i < names.length; i++) {
            ind[0] = i;
            System.out.println(ind[0]);
            // 匿名内部类只捕获到了 names 与 ind 的引用值
            // ind[0] 的值是由外部控制的，最后变成了3
            runners.add(() -> {
                System.out.println(names[ind[0]]);
            });
        }

        for (int k = 0; k < runners.size(); k++) {
            runners.get(k).run();
        }

        System.out.println("byForInt ----->");
        byForInt();
        System.out.println("byForIterator -->");
        byIterator();
    }

    private static void byForInt() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();

        for (String name : names) {
            runners.add(() -> System.out.println(name));
        }

        for (int k=0; k<runners.size(); k++) {
            runners.get(k).run();
        }
    }

    private static void byIterator() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            final int ii = i;
            runners.add(() -> System.out.println(names[ii]));
        }

        for (int k=0; k<runners.size(); k++) {
            runners.get(k).run();
        }
    }
}
