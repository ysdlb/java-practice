package org.ysdlb.bytebuddy.dep;

import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;
import net.bytebuddy.matcher.ElementMatchers;
import org.ysdlb.bytebuddy.dep.advice.Cooker;

import java.lang.instrument.ClassFileTransformer;

public class LocalAdviceTest {
    public static LocalAdviceTest INSTANCE = new LocalAdviceTest();

    ClassLoader classLoader;


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
        Class<Cooker> cookerType = (Class<Cooker>) classLoader.loadClass(Cooker.class.getName());
        // Ex: java.lang.ClassCastException: class org.ysdlb.bytebuddy.dep.advice.Cooker cannot be cast
        // to class org.ysdlb.bytebuddy.dep.advice.Cooker (org.ysdlb.bytebuddy.dep.advice.Cooker is
        // in unnamed module of loader net.bytebuddy.dynamic.loading.ByteArrayClassLoader$ChildFirst @7a7b0070;
        // org.ysdlb.bytebuddy.dep.advice.Cooker is in unnamed module of loader 'app')
        // Cooker cooker = cookerType.getDeclaredConstructor().newInstance();
        // cooker.hello();
        cookerType.getDeclaredConstructor().newInstance().hello();
        Cooker.taste("proto");
        cookerType.getDeclaredMethod("taste", String.class).invoke(null, "pototo");
        // cookerType.getMethod("makeFood", String.class, int.class, Double[].class).invoke(cooker, "pototo", 1, new Double[]{1.0, 2.0});
    }

    public static void main(String[] args) throws Exception {

        INSTANCE.initClassLoader();

        INSTANCE.modifyTarget();
        INSTANCE.print();
    }
}

