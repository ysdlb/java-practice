package semantic.extend;

import semantic.extend.base.A;
import semantic.extend.base.B;

import java.lang.reflect.InvocationTargetException;

public class PrivateConstruct {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        A a = A.getInstance();
        B b = B.getInstance();

        b.getClass().getConstructor().newInstance();
        a.getClass().getConstructor().newInstance();
    }
}
