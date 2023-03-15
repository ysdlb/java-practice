package org.ysdlb.bytebuddy.dep.helloworld;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    void test() {
    }

    @Advice.OnMethodEnter
    public static void onEnter() {
    }

    private static class Raw {
        public String printSome() {
            return "";
        }
    }

}
