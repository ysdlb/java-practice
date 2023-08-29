package deep_learn_jvm.lock_optimize;

import org.openjdk.jol.info.ClassLayout;
import utils.ColorOut;

public class BiasedLock_Race {

    /* 需要 jdk version < 15
     * 锁对象有具体偏向的线程，如果新的线程过来执行同步块会偏向新的线程吗？
     * 参考: https://juejin.cn/post/7004146648372740132
     *      https://tobebetterjavaer.com/thread/pianxiangsuo.html#%E5%9C%BA%E6%99%AF3
     *
     * 从这样的运行结果上来看，偏向锁像是“一锤子买卖”，
     * 只要偏向了某个线程，后续其他线程尝试获取锁，都会变为轻量级锁，这样的偏向非常有局限性。
     * 事实上并不是这样，详见：BiasWithdraw
     *
     * 因为维护成本太高，JDK 15 之前，偏向锁默认是 enabled，从 15 开始，默认就是 disabled，除非显示的通过 UseBiasedLocking 开启
     * 现在已经 deprecated 了
     */

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ColorOut.rPrintln("未进入同步块，MarkWord 为：");
        ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

        Thread t2 = new Thread(() -> {
            synchronized (o) {
                ColorOut.rPrintln("新线程获取锁，MarkWord为：");
                ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                ColorOut.rPrintln("新线程竞争后，MarkWord为：");
                ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());
            }
        });
        t2.start();
        Thread.sleep(2000);

        synchronized (o){
            ColorOut.rPrintln(("进入同步块，MarkWord 为："));
            ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());
        }
        ColorOut.rPrintln(("离开同步块，MarkWord 为："));
        ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

        ColorOut.rPrintln("主线程再次查看锁对象，MarkWord为：");
        ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            ColorOut.rPrintln(("主线程再次进入同步块，MarkWord 为："));
            ColorOut.yPrintln(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
