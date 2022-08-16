package aqs.clh;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock {

    // 共享的尾节点
    private final AtomicReference<QNode> tail;

    // 线程独有的前驱节点
    private final ThreadLocal<QNode> myPred;

    // 当前节点，表示自己。线程独有
    private final ThreadLocal<QNode> myNode;

    public CLHLock() {
        this.tail = new AtomicReference<>(new QNode());
        this.myPred = new ThreadLocal<>();
        this.myNode = ThreadLocal.withInitial(QNode::new);
    }

    @Override
    public void lock() {
        // 获取当前线程代表的节点
        QNode node = myNode.get();

        // 将自己的状态设置为 true 表示获取锁
        node.locked = true;

        // 将自己放在队尾，并拿到以前的队尾节点
        QNode prev = tail.getAndSet(node);

        // 把旧的节点放入前驱节点
        myPred.set(prev);

        // 自旋等待前驱节点的 locked 字段变为 false
        while (prev.locked) {
        }

    }

    @Override
    public void unlock() {
        // 获取自己的 Node，把自己的 locked 设置为 false
        QNode node = myNode.get();
        node.locked = false;

        myNode.set(myPred.get());
    }
}
