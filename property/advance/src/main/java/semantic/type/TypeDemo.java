package semantic.type;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class TypeDemo<T> {
    /**
     * GenericArrayType：组件类型为类型变量的数组
     */
    public T[] a;

    /**
     * GenericArrayType：组件类型为参数化类型的数组
     */
    public List<?>[] b;

    /**
     * ParameterizedType：参数化类型
     * List<? extends Object>携带的 "? extends Object"
     * 即通配符表达式，也就是WildcardType
     */
    public List<? extends Object> c;

    /**
     * Class：普通类型
     */
    public List d;
    /**
     * TypeVariable：类型变量
     */
    public T e;

    public static void main(String[] args) throws NoSuchFieldException {
        Field field;
        Type type;

        field = TypeDemo.class.getDeclaredField("a");
        type = field.getGenericType();
        System.out.println("type of a: " + type.getClass());
        System.out.println("typename of a: " + type.getTypeName());
        if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            Type t = genericArrayType.getGenericComponentType();
            System.out.println("type of a's component: " + t.getClass());
        }
        System.out.println("");

        field = TypeDemo.class.getDeclaredField("b");
        type = field.getGenericType();
        System.out.println("type of b: " + type.getClass());
        System.out.println("typename of b: " + type.getTypeName());
        if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            Type t = genericArrayType.getGenericComponentType();
            System.out.println("type of b's component: " + t.getClass());
        }
        System.out.println("");

        field = TypeDemo.class.getDeclaredField("c");
        type = field.getGenericType();
        System.out.println("type of c: " + type.getClass());
        System.out.println("typename of c: " + type.getTypeName());
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (Type actualTypeArg : actualTypeArguments) {
                System.out.println("type of c's component: " + actualTypeArg.getClass());
            }
        }
        System.out.println("");

        field = TypeDemo.class.getDeclaredField("d");
        type = field.getGenericType();
        System.out.println("type of d: " + type.getClass());
        System.out.println("typename of d: " + type.getTypeName());
        System.out.println("");

        field = TypeDemo.class.getDeclaredField("e");
        type = field.getGenericType();
        System.out.println("type of e: " + type.getClass());
        System.out.println("typename of e: " + type.getTypeName());

    }
}
