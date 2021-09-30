package semantic.feature.refer;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;

/**
 * 弱引用回收测试
 */
public class WeakReferenceDemo {

    /**
     * 普通对象的弱引用
     */
    @Test
    public void testOrdinaryObj() {
        WeakReference<String> weakReference =ordinaryObj();
        //可以输出hello值，此时两个弱引用扔持有对象，而且未进行gc
        System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference.get());
        //此时已无强一用执行"value"所在内存区域，gc时会回收弱引用
        System.gc();
        //此时输出都为nuill
        System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference.get());

    }

    /**
     * 常量对象的若引用
     */
    @Test
    public void testConstantObj() {
        WeakReference<String> weakReference = constantObj();

        //可以输出hello值，此时两个弱引用扔持有对象，而且未进行gc
        System.out.println("未进行gc时，只有弱引用指向value内存区域：" + weakReference.get());
        //此时已无强一用执行"value"所在内存区域，gc时会尝试回收弱引用，但常量无法回收
        System.gc();
        //此时输出都为nuill
        System.out.println("进行gc时，只有弱引用指向value内存区域：" + weakReference.get());

    }

    private WeakReference<String> ordinaryObj() {
        String hello = new String("value");
        WeakReference<String> weakReference = new WeakReference<>(hello);
        System.gc();
        //此时gc不会回收弱引用，因为字符串"value"仍然被hello对象强引用
        System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference.get());
        return weakReference;
    }

    private WeakReference<String> constantObj() {
        String hello = "value";
        WeakReference<String> weakReference = new WeakReference<>(hello);
        System.gc();
        //此时gc不会回收弱引用，因为字符串"value"仍然被hello对象强引用
        System.out.println("进行gc时，强引用与弱引用同时指向value内存区域：" + weakReference.get());
        return weakReference;
    }
}
