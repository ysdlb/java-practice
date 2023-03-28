package org.ysdlb.bytebuddy.dep;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import net.bytebuddy.matcher.ElementMatchers;
import org.ysdlb.bytebuddy.dep.advice.Cooker;
import org.ysdlb.io.ColorOut;

import java.lang.instrument.ClassFileTransformer;

public class LocalAdviceTest {
    private static LocalAdviceTest INSTANCE = new LocalAdviceTest();

    private ClassLoader classLoader;


    /**
     * 加载未被修改的类
     */
    public void initClassLoader() throws Exception {
        classLoader = new ByteArrayClassLoader.ChildFirst(getClass().getClassLoader(),
                ClassFileLocator.ForClassLoader.readToNames(Cooker.class),
                ByteArrayClassLoader.PersistenceHandler.MANIFEST);
    }


    /**
     * modify
     */
    public void modifyTarget() throws Exception {
        ByteBuddyAgent.install();
        ClassFileTransformer classFileTransformer = new AgentBuilder.Default()
                .with(AgentBuilder.PoolStrategy.Default.EXTENDED)
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                //.ignore()
                // 设定匹配范围
                .type(ElementMatchers.is(Cooker.class), ElementMatchers.is(classLoader))
                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
                    // 对比 enter 设置为 @Advice.This(optional = false)
                    //      exit 设置为 @Advice.This(optional = true )
                    //return builder.visit(Advice.to(AdviceLogic.class).on(ElementMatchers.any()));
                    return builder.visit(Advice.to(AdviceLogic.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.not(ElementMatchers.isStatic())))
                    );

                })
                .installOnByteBuddyAgent();

    }



    public void print() throws Exception {
        // 非增强的
        {
            Cooker cooker = new Cooker();

            ColorOut.rPrintln("hello:");
            cooker.hello();

            ColorOut.rPrintln("static taste:");
            Cooker.taste("proto");

            ColorOut.rPrintln("makeFood:");
            cooker.makeFood("proto", 1, new Double[]{1D, 2D});

            ColorOut.rPrintln("classloader:");
            System.out.println(cooker.getClass().getClassLoader());
        }

        System.out.println("\033[38;5;118m ======增强======== \033[0m");


        // 增强
        Class<Cooker> cookerType = (Class<Cooker>) classLoader.loadClass(Cooker.class.getName());
        {
            Object cooker = cookerType.getDeclaredConstructor().newInstance();

            ColorOut.rPrintln("hello:");
            cookerType.getDeclaredMethod("hello").invoke(cooker);

            ColorOut.rPrintln("static taste:");
            cookerType.getDeclaredMethod("taste", String.class).invoke(null, "pototo");

            ColorOut.rPrintln("makeFood:");
            cookerType.getMethod("makeFood", String.class, int.class, Double[].class).invoke(cooker, "pototo", 1, new Double[]{1.0, 2.0});

            ColorOut.rPrintln("classloader:");
            System.out.println(cooker.getClass().getClassLoader());
        }

        System.out.println("\033[38;5;118m ======同类型不同 classLoader 发生隐式转换 ======== \033[0m");
        try {
            // Ex: java.lang.ClassCastException: class org.ysdlb.bytebuddy.dep.advice.Cooker cannot be cast
            // to class org.ysdlb.bytebuddy.dep.advice.Cooker (org.ysdlb.bytebuddy.dep.advice.Cooker is
            // in unnamed module of loader net.bytebuddy.dynamic.loading.ByteArrayClassLoader$ChildFirst @7a7b0070;
            //
            // org.ysdlb.bytebuddy.dep.advice.Cooker is in unnamed module of loader 'app')
            Cooker cooker = cookerType.getDeclaredConstructor().newInstance();
            cooker.hello();
            //
            // same as: cookerType.getDeclaredConstructor().newInstance().hello()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        INSTANCE.initClassLoader();

        INSTANCE.modifyTarget();
        INSTANCE.print();
    }
}

