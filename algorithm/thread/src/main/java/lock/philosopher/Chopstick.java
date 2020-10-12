package lock.philosopher;

/**
 * 哲学家吃饭的筷子
 * 一根
 */
public class Chopstick {

    public final int id;
    private Status status = Status.PUT_DOWN;

    /**
     * 筷子的两种状态，拿起和放下
     */
    public static enum Status {
        PICKED_UP,
        PUT_DOWN
    }


    public Chopstick(int id) {
        this.id = id;
    }

    public void pickUp() {
        status = Status.PICKED_UP;
    }

    public void putDown() {
        status = Status.PUT_DOWN;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "chopstick-" + id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
