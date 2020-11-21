package ysdlb.property.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 在类属性中，基本类型的默认值为0，包装类类型的默认值是 null，包装类属性不初始化直接比较会 NPE
 * 只有一个元素也可以用 stream.max + Comparator 来获取最大值
 */
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

        System.out.println("==============temp==================");
        List<Temp> temps = new ArrayList<>();
        Integer integer = temps.stream().map(Temp::getI).max(Integer::compareTo).orElse(10);
        System.out.println(integer);

        temps.add(new Temp());
        integer = temps.stream().map(Temp::getI).max(Integer::compareTo).orElse(10);
        System.out.println(integer);

    }
    static class Temp {
        int i;

        public Integer getI() {
            return i;
        }

        public void setI(Integer i) {
            this.i = i;
        }
    }
}
