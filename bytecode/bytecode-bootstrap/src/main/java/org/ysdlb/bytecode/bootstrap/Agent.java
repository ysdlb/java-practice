package org.ysdlb.bytecode.bootstrap;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public final class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
    }

    private static synchronized File installBootstrapJar(Instrumentation inst)
            throws IOException, URISyntaxException {

        CodeSource codeSource = Agent.class.getProtectionDomain().getCodeSource();

        if (codeSource == null) {
            throw new IllegalStateException("could not get agent jar location");
        }

        File javaagentFile = new File(codeSource.getLocation().toURI());

        if (!javaagentFile.isFile()) {
            throw new IllegalStateException(
                    "agent jar location doesn't appear to be a file: " + javaagentFile.getAbsolutePath());
        }

        // passing verify false for vendors who sign the agent jar, because jar file signature
        // verification is very slow before the JIT compiler starts up, which on Java 8 is not until
        // after premain execution completes
        JarFile agentJar = new JarFile(javaagentFile, false);
        verifyJarManifestMainClassIsThis(javaagentFile, agentJar);
        inst.appendToBootstrapClassLoaderSearch(agentJar);
        return javaagentFile;
    }

    // 防止用户误将探针的 agent.jar 加入应用的 app.jar
    //
    // 在使用该探针的时候，我们总是将 agent.jar 加入 java bootstrap 加载器的扫描路径中

    // 如果误将 agent.jar 加入 app.jar，会将整个应用的 jar 包路径加入到 bootstrap 加载器的扫描路径中.
    // 由于存在很多应用和库的代码并没有处理 getClassLoader() 方法返回 null 的情况，所以一些应用会报错
    // (e.g. https://github.com/qos-ch/logback/pull/291)
    private static void verifyJarManifestMainClassIsThis(File jarFile, JarFile agentJar)
            throws IOException {
        Manifest manifest = agentJar.getManifest();
        if (manifest.getMainAttributes().getValue("Premain-Class") == null) {
            throw new IllegalStateException(
                    "The agent was not installed, because the agent was found in '"
                            + jarFile
                            + "', which doesn't contain a Premain-Class manifest attribute. Make sure that you"
                            + " haven't included the agent jar file inside of an application uber jar.");
        }
    }
}
