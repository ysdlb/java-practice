package reflect.foundation;

/**
 * <a href = https://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav>
 *     What is the difference between canonical name, simple name and class name in Java Class?
 * </a>
 *
 * <ol>
 *     <li>getName(): Class.forName() 用的</li>
 *     <li>getCanonicalName: import 用的</li>
 *     <li>getSimpleName: 没有路径, 简单名字</li>
 *     <li>getTypeName: Type 类型的纯粹信息</li>
 * </ol>
 */
public class ClassNameTest {

    public static void main(final String... arguments) {
        printNamesForClass(
            int.class,
            "int.class (primitive)");
        printNamesForClass(
            String.class,
            "String.class (ordinary class)");
        printNamesForClass(
                String[].class,
                "String[].class (array class)");
        printNamesForClass(
            java.util.HashMap.SimpleEntry.class,
            "java.util.HashMap.SimpleEntry.class (nested class)");
        printNamesForClass(
            new java.io.Serializable(){}.getClass(),
            "new java.io.Serializable(){}.getClass() (anonymous inner class)");
    }

    private static void printNamesForClass(final Class<?> clazz, final String label) {
        System.out.println(label + ":");
        System.out.println("    getName():          " + clazz.getName());
        System.out.println("    getCanonicalName(): " + clazz.getCanonicalName());
        System.out.println("    getSimpleName():    " + clazz.getSimpleName());
        System.out.println("    getTypeName():      " + clazz.getTypeName()); // added in Java 8
        System.out.println();
    }
}
