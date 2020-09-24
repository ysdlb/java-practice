package ysdlb.foundation.generic;

public class CheckClass {
    public static <T> T creatInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        A a = creatInstance(A.class);
        B b = creatInstance(B.class);
        // 编译器就能通过类型检查检查出错误
        // B b = creatInstance(A.class);
    }

}
class A {}
class B {}
