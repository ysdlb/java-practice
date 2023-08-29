package deep_learn_jvm.lock_optimize;

import org.openjdk.jol.info.ClassLayout;
import utils.ColorOut;

/* 对偏向锁/轻量级锁造成影响的
 * wait() 方法是互斥量（重量级锁）独有的，一旦调用该方法，就会升级成重量级锁
 */
public class ThinLock_Wait {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ColorOut.rPrintln("未生成 hashcode，MarkWord 为：");
        ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

        ColorOut.rPrintln("生成 hashcode，确保直接使用轻量级锁，MarkWord 为：");
        System.identityHashCode(o);
        ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            ColorOut.rPrintln(("进入同步块，MarkWord 为："));
            ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

            ColorOut.rPrintln("wait 2s");
            o.wait(2000);

            ColorOut.rPrintln(("调用 wait 后，MarkWord 为："));
            ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());
        }
    }

}
