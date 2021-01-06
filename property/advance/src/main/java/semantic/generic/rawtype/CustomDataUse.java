package semantic.generic.rawtype;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("DuplicatedCode")
public class CustomDataUse {

    /**
     * 不管是 List<?> 还是 List<Object>
     * 都调用{@link java.io.PrintStream#println(Object)}
     */
    @Test
    public void doReturnRawTypeTest() {
        CustomData stringDate = doReturnRawType("string");
        // String string = stringDate.getData();
        Object string = stringDate.getData();
        List<Integer> list = stringDate.getList();
        list.add(1);
        List<String> listWrong = stringDate.getList();
        listWrong.add("wrong");

        List<Object> listObject = stringDate.getList();
        listObject.forEach(System.out::println);

        List<?> listUnknown = stringDate.getList();
        listUnknown.forEach(System.out::println);


        System.out.println(doReturnRawType("integer"));
        System.out.println(doReturnRawType("localDate"));
        System.out.println(doReturnRawType("unknown"));
    }

    @Test
    public void doReturnRawTypeTestFix() {
        CustomData<?> stringDate = doReturnRawType("string");
        // String string = stringDate.getData();    // String 类型任然丢失
        Object string = stringDate.getData();
        List<Integer> list = stringDate.getList();
        list.add(1);
        // 无法通过编译
        // List<String> listWrong = stringDate.getList();
        // listWrong.add("wrong");

        // List<Object> listObject = stringDate.getList();
        // listObject.forEach(System.out::println);

        List<?> listUnknown = stringDate.getList();
        listUnknown.forEach(System.out::println);

    }

    /**
     * 非相关的参数化类型擦除的相当彻底, Set<? extends Number> 的 Number 也被擦除了
     */
    @Test
    public void doRawType1() {
        CustomData stringDate = doReturnRawType("string");
        Set<Number> set = stringDate.getSet();
        Set<String> set1 = stringDate.getSet();
        List<Integer> list = stringDate.getList();
        list.add(1);
        List<String> listWrong = stringDate.getList();
        listWrong.add("wrong");
        List<String> listUnknown = stringDate.getList();
        listUnknown.forEach(System.out::println);
    }

    @Test
    public void doRawType2() {
        CustomData stringDate = doReturnRawType("string");
        List<Integer> list = stringDate.getList();
        list.add(1);
        List<String> listWrong = stringDate.getList();
        listWrong.add("wrong");
        List<Integer> listUnknown = stringDate.getList();
        listUnknown.forEach(System.out::println);
    }


    private CustomData doReturnRawType(String selector) {
        switch (selector) {
            case "string":
                return new CustomData("A String");
            case "integer":
                return new CustomData(1);
            case "localDate":
                return new CustomData(LocalDate.now());
            default:
                return new CustomData(null);
        }
    }

    private CustomData<?> doReturnParameterize(String selector) {
        switch (selector) {
            case "string":
                return new CustomData<>("A String");
            case "integer":
                return new CustomData<>(1);
            case "localDate":
                return new CustomData<>(LocalDate.now());
            default:
                return new CustomData<>(null);
        }
    }

    @Test
    public void doReturnParameterizeTest() {
        CustomData stringDate = doReturnParameterize("string");
        // 使用上与 doReturnRawType 一致
        // String string = stringDate.getData();
        Object string = stringDate.getData();
        List<Integer> list = stringDate.getList();
        list.add(1);
        List<String> listWrong = stringDate.getList();
        listWrong.add("wrong");

        List<Object> listObject = stringDate.getList();
        listObject.forEach(System.out::println);

        List<?> listUnknown = stringDate.getList();
        listUnknown.forEach(System.out::println);


        System.out.println(doReturnParameterize("integer"));
        System.out.println(doReturnParameterize("localDate"));
        System.out.println(doReturnParameterize("unknown"));
    }

}
