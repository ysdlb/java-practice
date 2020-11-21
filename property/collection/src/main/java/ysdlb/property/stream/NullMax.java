package ysdlb.property.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NullMax {
    public static void main(String[] args) {
        List<Float> list = new ArrayList<>();
        Optional<Float> max = list.stream().max(Float::compareTo);
        System.out.println(max.orElse(0.7f));

        // 看看 空集合 sort 会不会报错
        list.sort(Float::compareTo);

        list.add(0.3f);
        list.add(0.3f);
        list.add(0.2f);
        list.add(0.4f);
        max = list.stream().max(Float::compare);
        System.out.println(max.orElse(0.7f));
    }
}
