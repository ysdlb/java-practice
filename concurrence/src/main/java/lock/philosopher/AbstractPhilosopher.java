package lock.philosopher;

import java.util.Random;

/**
 * 哲学家就餐问题
 * 哲学家抽象类
 */
public abstract class AbstractPhilosopher extends Thread implements Philosopher{
    protected final int id;
    protected final Chopstick left;
    protected final Chopstick right;

    private int count;

    public AbstractPhilosopher(int id, Chopstick left, Chopstick right) {
        super("philosopher-" + id);
        this.id = id;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        for(;;) {
            think();
            eat();
        }
    }

    @Override
    public abstract void eat();

    @Override
    public void think() {
        System.out.println(String.format("%s is thinking...", this));
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void doEat() {
        System.out.println(String.format("%s is eating... No.%d", this, ++count));
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(50));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "philosopher-" + id;
    }
}
