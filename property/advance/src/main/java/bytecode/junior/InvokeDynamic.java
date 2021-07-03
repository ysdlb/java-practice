package bytecode.junior;

import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class InvokeDynamic {

    public String testConstantCallSite() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, int.class, int.class);
        MethodHandle mh = lookup.findVirtual(String.class, "substring", type);
        ConstantCallSite callSite = new ConstantCallSite(mh);
        MethodHandle invoker = callSite.dynamicInvoker();
        return (String) invoker.invoke("Hello", 2, 3);
    }

    public static void main(String[] args) {
        try {
            String str = new InvokeDynamic().testConstantCallSite();
            System.out.println(str);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
