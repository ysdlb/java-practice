package foundation;

public class Visibility {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask task = new TimeConsumingTask();
        Thread thread = new Thread(task);

        thread.start();

        Thread.sleep(1000);

        task.cancel();
    }
}
class TimeConsumingTask implements Runnable {
    private boolean toCancel = false;

    @Override
    public void run() {
        while (!toCancel) {
            if (doExecute()) {
                break;
            }
        }
        /**
         * 上面代码被优化成了这样
        if (!toCancel) {
            while (true) {
                if (doExecute()) {
                    break;
                }
            }
        }
         * java8 已经不存在这个问题了
         */
        if (toCancel) {
            System.out.println("Task was canceled.");
        } else {
            System.out.println("Task done.");
        }
    }

    private boolean doExecute() {
        boolean isDone = false;
        System.out.println("executing ...");

        // 模拟实际操作的时间消耗
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {

        }

        return isDone;
    }

    public void cancel() {
        toCancel = true;
        System.out.println(this + " canceled");
    }
}
