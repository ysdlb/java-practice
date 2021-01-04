package semantic.generic;

import java.lang.reflect.TypeVariable;

/**
 * https://www.cnblogs.com/afraidToForget/p/10727014.html
 */
public class ExampleBounds {

    class TypeBounds<T extends Number>{

    }

    public static void main(String[] args){
        TypeVariable<?>[] typeVariables= TypeBounds.class.getTypeParameters();
        TypeVariable<?> typeVariable = typeVariables[0];
        System.out.println("类型变量的符号是："+typeVariable.getName());
        System.out.println("类型变量的上边界是"+typeVariable.getBounds()[0]);
    }
}
