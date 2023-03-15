package org.ysdlb.bytecode.bootstrap;

/**
 * 注册 ClassFileTransformer 的主要逻辑，由字节码工具自己决定如何实现
 */
public interface AgentStarter {
    void start();

    /**
     * 不同位置，不同的类加载器
     */
    ClassLoader getExtensionClassLoader();
}
