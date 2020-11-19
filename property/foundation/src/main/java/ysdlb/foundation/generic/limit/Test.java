package ysdlb.foundation.generic.limit;

import com.sun.scenario.animation.shared.AnimationAccessor;
import ysdlb.foundation.component.Animal;
import ysdlb.foundation.component.Chicken;
import ysdlb.foundation.component.Dog;

import java.util.HashMap;
import java.util.function.Function;

public class Test {

    public <K, V> V doSomething1(K k, Function<? super K, ? extends V> keMapper) {
        return keMapper.apply(k);
    }

    public <K, V> V doSomething2(K k, Function<K, V> keMapper) {
        return keMapper.apply(k);
    }

    static class SuperTest<T> {
        T t;
        public T get() {
            System.out.println("do get");
            return t;
        }
        public void set(T t) {
            System.out.println("do set");
            this.t = t;
        }
    }


    public <P, R> void doSuperTest(P param, R result, SuperTest<? super P> testP, SuperTest<? extends R> testR) {
        testP.set(param);
        // P p = testP.get();
        Object p = testP.get();

    }

    public static void main(String[] args) {
        Integer i = 10;

        Test test = new Test();
        test.doSomething1(i, Integer::doubleValue);
        test.doSomething2(i, Integer::doubleValue);


        SuperTest<Integer> superTest1 = new SuperTest<>();
        superTest1.set(i);
        superTest1.get();

        SuperTest<? extends Integer> superTest2 = new SuperTest<>();
        // superTest2.set(i);
        Integer r2 = superTest2.get();

        SuperTest<? super Integer> superTest3 = new SuperTest<>();
        superTest3.set(i);
        // Integer r3 = superTest3.get();
        superTest3.get();

        Dog dog = new Dog();
        SuperTest<? extends Animal> superTest4 = new SuperTest<>();
        // SuperTest 类定义中的 T 为确定类型， 通过 ? extends Animal 无法推断到某一类型，无法 set
        // 但 get 时都可以 指定到唯一的 超类 Animal 上来。
        // superTest4.set(dog);

        SuperTest<? super Dog> superTest5 = new SuperTest<>();
        superTest5.set(dog);
        // superTest5.set((Animal)dog);

    }

}
