package org.ysdlb.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    void helloWorld() throws InstantiationException, IllegalAccessException {
        Class<?> helloWorldClazz = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();

        Object o = helloWorldClazz.newInstance();
        System.out.println(o.toString());
    }
}
