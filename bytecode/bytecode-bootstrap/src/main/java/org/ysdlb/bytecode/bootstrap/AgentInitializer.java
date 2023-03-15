package org.ysdlb.bytecode.bootstrap;

import java.lang.instrument.Instrumentation;

/**
 * 启动探针的实现逻辑 (将 transform 注入 inst)
 * <p>
 * 重点是 transformer 的三方实现；以及当前 jar 包的加载器
 * <p>
 * Question:
 *  启动 jar 包 (bootstrap) 是一个单独的 jar 包；
 *  transformer 具体实现是其他的 jar 包；
 *  各个增强模块又是另外的 jar 包
 *  此时各个类加载器的作用，以及它们应该怎么设计
 */
public final class AgentInitializer {
    private static ClassLoader agentClassLoader = null;

    private static AgentStarter agentStarter = null;

    /**
     * 启动 jar 包的启动入口
     * 参数随便填，能满足各种 transformer 的实现要求就行；
     */
    public static void initialize(Instrumentation inst) {
    }
}
