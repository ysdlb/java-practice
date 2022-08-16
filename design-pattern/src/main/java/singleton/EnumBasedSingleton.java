package singleton;

/**
 * 基于枚举的单例模式实现
 * 唯一实例在 Singleton.INSTANCE 初次被引用时才被初始化
 */
public class EnumBasedSingleton {
    public static Singleton getInstance() {
        System.out.println("getInstance invoked...");
        return Singleton.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.class.getName());
        EnumBasedSingleton.getInstance().someService();
    }
}

enum Singleton {
    INSTANCE;

    // 私有构造器
    Singleton() {
        System.out.println("Singleton init...");
    }
    public void someService() {
        System.out.println("do someService...");
    }
}

