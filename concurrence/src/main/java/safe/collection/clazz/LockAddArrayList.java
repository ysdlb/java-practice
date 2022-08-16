package safe.collection.clazz;

import java.util.ArrayList;

public class LockAddArrayList<E> extends ArrayList<E> {
    @Override
    public synchronized boolean add(E e) {
        return super.add(e);
    }
}
