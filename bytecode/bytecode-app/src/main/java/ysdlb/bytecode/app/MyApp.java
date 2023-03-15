package ysdlb.bytecode.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp {
    private static final Logger logger = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {
        int number;
        do {
            Scanner reader = new Scanner(System.in);
            number = reader.nextInt();
            switch (number) {
                case 1 -> myServiceTest();
                case 2 -> threadPoolTest();
                case 3 -> classLoaderTest();
                default -> {}
            }
        } while (number != 0);
    }

    private static void myServiceTest() {
        Thread thread = Thread.currentThread();
        System.out.format("%s%s:%s%s -> main start\n",
                "\033[38;5;118m", thread.getThreadGroup().getName(), thread.getName(), "\033[0m");
        System.out.println("########### enter in 1 do something ############");

        int number;
        do {
            Scanner reader = new Scanner(System.in);
            number = reader.nextInt();
            MyService myService = new MyService();
            String ret = myService.doSomething();
            System.out.println(ret);
        } while (number == 1);
    }

    private static void threadPoolTest() {
        int size = 2;
        ExecutorService executorPools = Executors.newFixedThreadPool(size);
        for (int i = 0; i < size; i++) {
            String taskName = "taskName" + "-" + i;
            executorPools.submit(() -> {
                logger.info("{} submit", taskName);
                executorPools.submit(() -> {
                    logger.info("{} child submit", taskName);
                    executorPools.submit(() -> {
                        logger.info("{} grandSon submit", taskName);
                    });
                });
            });
        }
    }

    public static void classLoaderTest() {
        ClassLoader appClassLoader = MyApp.class.getClassLoader();
        ClassLoader strClassLoader = String.class.getClassLoader();

        // 不设置启动参数
        // app -> jdk.internal.loader.ClassLoaders$AppClassLoader@531d72ca
        // str -> null
        // 设置启动参数
        // 除了 inst，jdk9 以上目前不知道怎么设置
        System.out.println("app -> " + appClassLoader);
        System.out.println("str -> " + strClassLoader);
    }
}
