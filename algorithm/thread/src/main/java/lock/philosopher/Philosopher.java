package lock.philosopher;

public interface Philosopher {
    void think();
    void eat();

    // 为什么这个地方被自动实现了
    void start();
}
