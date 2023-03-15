package gqhan.learn.jdk8;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK10后实现了loop strip mining，优化了counted loop的safepoint轮巡问题
 *
 * 参考:
 * https://www.toutiao.com/article/7139742456324735491/?app=news_article&timestamp=1666955780&use_new_style=1&req_id=20221028191620010209148158070E0EE3&group_id=7139742456324735491&share_token=533F39F8-5C8B-4022-97F3-65DFB6ADB4B5&tt_from=copy_link&source=m_redirect&wid=1668392732689
 *
 * VM启动了GC，然后需要等待这个线程把循环跑完才能进入Safepoint，如果这个循环是个大循环，且循环内执行的比较慢，而且不存在其他函数调用产生其他Safepoint，这时就需要等待该线程跑完循环才能从其他位置进入Safepoint， 这句话说明了循环内 必须没有其他函数调用才会不进入安全点，那么图上的代码明显是有函数调用的为什么还会出现这种情况呢？？？？
 * otSpot会在所有方法的临返回之前，以及所有非counted loop的循环的回跳之前放置安全点。
 * 在循环内调用了别的函数也会进入安全点 为什么没有进入GC呢？//@Java探索者：otSpot会在所有方法的临返回之前，以及所有非counted loop的循环的回跳之前放置安全点。
 *
 * 这是假的吧, 复现不了
 *
 */
public class CountedLoop {

    // public static AtomicInteger num = new AtomicInteger(0);
    public static int num = 0;

    public static void countedLoop() throws InterruptedException {
        Runnable runnable=()->{
            for (int i = 0; i < 1000000000; i++) {
                for (int j = 0; j < i; j++)
                    num++;
            }
            System.out.println(Thread.currentThread().getName()+"执行结束!");
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println("num = " + num);
    }

    public static void main(String[] args) throws InterruptedException {
        countedLoop();
    }
}