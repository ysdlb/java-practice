package ysdlb.learn.jmm;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class ObjectHeader {

    @Test
    void vm() {
        System.out.println(VM.current().details());
    }

    @Test
    void instanceObject() {
        Object o = new Object();
        String printable = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(printable);
    }

    @Test
    void arrayEmptyInstance() {
        int[] o = new int[]{};
        String printable = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(printable);
    }

    @Test
    void arrayInstance() {
        int[] o = new int[]{1, 2};
        String printable = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(printable);
    }
}
