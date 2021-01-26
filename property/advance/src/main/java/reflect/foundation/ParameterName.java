package reflect.foundation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 如果不设置编译时参数 --parameters, 默认的参数名是 arg0, arg1 ....
 */
public class ParameterName {

    private static final String  fmt = "%24s: %s%n";


    public static void printClassConstructors(Class<?> c) {
        Constructor<?>[] allConstructors = c.getConstructors();
        System.out.format(fmt, "Number of constructors", allConstructors.length);
        for (Constructor<?> currentConstructor : allConstructors) {
            printConstructor(currentConstructor);
        }
        Constructor<?>[] allDeclConst = c.getDeclaredConstructors();
        System.out.format(fmt, "Number of declared constructors",
                allDeclConst.length);
        for (Constructor<?> currentDeclConst : allDeclConst) {
            printConstructor(currentDeclConst);
        }
    }

    public static void printClassMethods(Class<?> c) {
        Method[] allMethods = c.getDeclaredMethods();
        System.out.format(fmt, "Number of methods", allMethods.length);
        for (Method m : allMethods) {
            printMethod(m);
        }
    }

    public static void printConstructor(Constructor<?> c) {
        System.out.format("%s%n", c.toGenericString());
        Parameter[] params = c.getParameters();
        System.out.format(fmt, "Number of parameters", params.length);
        for (Parameter param : params) {
            printParameter(param);
        }
    }

    public static void printMethod(Method m) {
        System.out.format("%s%n", m.toGenericString());
        System.out.format(fmt, "Return type", m.getReturnType());
        System.out.format(fmt, "Generic return type", m.getGenericReturnType());

        Parameter[] params = m.getParameters();
        for (Parameter param : params) {
            printParameter(param);
        }
    }

    public static void printParameter(Parameter p) {
        System.out.format(fmt, "Parameter class", p.getType());
        System.out.format(fmt, "Parameter name", p.getName());
        System.out.format(fmt, "Modifiers", p.getModifiers());
        System.out.format(fmt, "Is implicit?", p.isImplicit());
        System.out.format(fmt, "Is name present?", p.isNamePresent());
        System.out.format(fmt, "Is synthetic?", p.isSynthetic());
    }

    public static void main(String... args) {

        printClassConstructors(ExampleMethods.class);
        printClassMethods(ExampleMethods.class);
    }
}
