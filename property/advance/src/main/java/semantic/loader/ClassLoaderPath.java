package semantic.loader;

import org.junit.jupiter.api.Test;

public class ClassLoaderPath {

    @Test
    void extensionClassLoader() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        while (classLoader != null) {
            System.out.println("name: " + classLoader.getName());
            classLoader = classLoader.getParent();
        }
    }

    void bootStrapClassLoader() {
    }
}