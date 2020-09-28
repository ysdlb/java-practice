package singleton;

/**
 * 基于双重校验加锁的单例模式实现
 * 问题①：为什么要双重校验
 * 问题②：为什么要用 volatile
 */
public class DCLSingleton {
    private static volatile DCLSingleton instance;

    /**
     * 只有该方法被调用时，该类的唯一实例才会被创建
     * @return
     */
    public static DCLSingleton getInstance() {
        if (null == instance) {
            synchronized (DCLSingleton.class) {
                if (null == instance) {
                    instance = new DCLSingleton();
                }
            }

        }
        return instance;
    }
}
