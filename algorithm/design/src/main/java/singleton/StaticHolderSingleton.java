package singleton;

/**
 * 基于静态内部类的单例模式实现
 * 该方式也实现了延迟加载
 * 利用的是类的静态变量被初次访问会触发Java虚拟机对该类进行初始化，即该类静态变量的值会变为初始值，而不是默认值。
 * 因此，静态方法 getInstance() 被调用的时候，Java虚拟机会初始化这个方法所访问的内部静态类 InstanceHolder 。
 * 这使得 InstanceHolder 的静态变量 INSTANCE 会被初始化。
 *
 * 静态变量只会被初始化一次
 */
public class StaticHolderSingleton {
    // 私有构造器
    private StaticHolderSingleton() {
        System.out.println("StaticHolderSingleton init...");
    }

    private static class InstanceHolder {
        // 保存外部类的唯一实例
        // static 关键字仅保证读线程能够读取到相应字段的初始值，而不是相对新值
        // final 保证 final 修饰的对象能够安全发布，final 保证的是有序性，即保障一个对象对外可见的时候，该对象的final字段必然是初始化完毕的
        // final 并不保障对象引用本身对外的可见性
        final static StaticHolderSingleton INSTANCE = new StaticHolderSingleton();
    }

    public static StaticHolderSingleton getInstance() {
        System.out.println("getInstance() invoked...");
        return InstanceHolder.INSTANCE;
    }
    public void someService() {
        System.out.println("do someService...");
    }

    public static void main(String[] args) {
        System.out.println(StaticHolderSingleton.class.getName());
        StaticHolderSingleton.getInstance().someService();
    }
}
