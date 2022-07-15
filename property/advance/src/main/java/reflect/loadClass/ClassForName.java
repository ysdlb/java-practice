package reflect.loadClass;

public class ClassForName {

    //Static Code Block
    static {
        System.out.println("Executed static code block");
    }
    //Static variable
    private static final String staticFiled = staticMethod();

    //Static methods for assigning static variables
    public static String staticMethod(){
        System.out.println("Executed static method");
        return "Assigned static fields";
    }

}