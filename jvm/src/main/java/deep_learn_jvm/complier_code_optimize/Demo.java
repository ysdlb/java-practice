package deep_learn_jvm.complier_code_optimize;

import java.util.concurrent.atomic.AtomicInteger;

/* 启动参数:
     java \
         -XX:+PrintCompilation \
         -XX:CompileOnly=deep_learn_jvm/complier_code_optimize/Demo::workload \
         deep_learn_jvm/complier_code_optimize/Demo
 *
 * graal 启动:
     java \
         --module-path=~/work/lang/graal/sdk/mxbuild/dists/graal-sdk.jar \
         --upgrade-module-path=~/work/lang/graal/compiler/mxbuild/jdk17/dists/jdk17/jdk.internal.vm.compiler.jmod \
         -XX:+UnlockExperimentalVMOptions \
         -XX:+EnableJVMCI \
         -XX:+UseJVMCICompiler \
         -XX:-TieredCompilation \
         -XX:+PrintCompilation \
         -XX:CompileOnly=deep_learn_jvm/complier_code_optimize/Demo::workload \
         deep_learn_jvm/complier_code_optimize/Demo
 *
 */
public class Demo {
    public static void main(String[] args) {
        while (true) {
            workload(4, 2);
        }
    }

    private static int workload(int a, int b) {
        return a + b;
    }
}
