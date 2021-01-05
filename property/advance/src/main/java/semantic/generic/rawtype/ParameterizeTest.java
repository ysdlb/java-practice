package semantic.generic.rawtype;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ParameterizeTest {
    /**
     * 1. 参数化类型为 ? 的泛型引用无法 set/add 任何与泛型参数相关的值
     * 1. 参数化类型为 Object 的泛型引用可以 set/add 任何与泛型参数相关的值
     * 3. 任何泛型引用都可以使用 <b>参数化类型为 ? 的泛型引用</b> 来持有，准确的说是: 任何泛型都可以转化为参数化类型为 ? 的泛型
     *    只不过会丢失泛型信息，泛型相关值为 <b>capture of ?</b>，使用时视为 Object
     * 4. 参数化类型为 Object 泛型之外的任何泛型都不能转化为 Object 泛型，包括 ? 泛型。（或称被 Object 泛型引用持有）
     */
    @Test
    public void doParameterize1() {
        CustomData<?> customData = new CustomData<>();
        // customData.setData("string"); // compile error

        List<?> list = new ArrayList<>();
        // list.add(1);                  // compile error

        CustomData<String> stringCustomData = new CustomData<>();
        System.out.println(stringCustomData.getData()); // invoke println(String)
        CustomData<?> unKnownCustomData = stringCustomData;
        System.out.println(unKnownCustomData.getData());      // invoke println(Object)
        // unKnownCustomData.setData("string");
        // unKnownCustomData.setData(new Object());

        // CustomData<Object> objectCustomData = stringCustomData;
        // CustomData<Object> objectCustomData1 = customData;
    }
}
