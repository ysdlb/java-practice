package ysdlb.foundation;

//import com.sun.xml.internal.stream.util.ReadOnlyIterator;
import ysdlb.foundation.component.Animal;

import java.util.Iterator;
import java.util.Set;

public final class ImmutableDemo implements Iterable<Animal> {
    // 列表
    private final Set<Animal> animals;

    public final int total;

    public ImmutableDemo(Set<Animal> animals) {
        this.animals = animals;
        this.total = animals.size();
    }

    @Override
    public final Iterator<Animal> iterator() {
//        return new ReadOnlyIterator(animals.iterator());
        return null;
    }
}
