package proxy.dynamic.target;

public interface Car {
    int i = 100;

    void run();

    default int get() {
        return i;
    }
}
