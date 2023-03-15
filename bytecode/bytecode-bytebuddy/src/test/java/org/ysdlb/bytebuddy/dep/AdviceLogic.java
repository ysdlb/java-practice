package org.ysdlb.bytebuddy.dep;

import net.bytebuddy.asm.Advice;

@SuppressWarnings("unused")
public class AdviceLogic {

    @Advice.OnMethodEnter
    public static void enter() {
        System.out.println("byte_buddy enter");
    }

    @Advice.OnMethodExit
    public static void exit() {
        System.out.println("byte_buddy exit");
    }
}
