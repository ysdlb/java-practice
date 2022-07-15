package reflect.loadClass;

import org.junit.jupiter.api.Test;

public class ClassLoaderAndForNameTest {
    @Test
    public void testLoadClass(){
        try {
            ClassLoader.getSystemClassLoader().loadClass("reflect.loadClass.ClassForName");
            System.out.println("#########-------------End character------------##########");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassForName(){
        try {
            Class.forName("reflect.loadClass.ClassForName");
            System.out.println("#########-------------End character------------##########");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassForNameNoInitialize(){
        try {
            // todo
            // ClassLoader classLoader = ClassLoader.getPlatformClassLoader();
            // 引申: 如何让自己的类通过 PlatformClassLoader 或者 BootClassLoader 加载
            // 用处: Agent 代理可能会在标准类库中插入自己写的类, 这些类会用上述类加载器加载
            // 进一步引申: 为什么这些类不用 AppClassLoader 加载
            // 可能的原因: Reflection.getCallerClass() 拿到前一个栈帧的类 (标准类库中) , 获得其类加载器后, 用这个类加载器加载当前类? (未证实, 留存)

            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class.forName("reflect.loadClass.ClassForName", false, classLoader);
            System.out.println("#########-------------End character------------##########");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassForNameInitialize(){
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            Class.forName("reflect.loadClass.ClassForName", false, classLoader);
            System.out.println("#########-------------End character------------##########");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
