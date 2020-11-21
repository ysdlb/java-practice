package ysdlb.property.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetMax {
    public static void main(String[] args) {
        List<Float> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(Float.valueOf(i));
        }
        Optional<Float> optionalFloat = list.stream().max(Float::compareTo);
        optionalFloat.ifPresent(System.out::println);
//        list.stream().forEach(System.out::println);
        float min = list.stream().min(Float::compareTo).get();
        System.out.println(min);
    }
}
