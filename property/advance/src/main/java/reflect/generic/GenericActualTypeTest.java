package reflect.generic;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic && ParameterizedType
 */
public class GenericActualTypeTest {

    public static class CoCo<T> {
        T data;

        CoCo(T data) {
            this.data = data;
        }

        /**
         * 范型类本省使用这个方法只会拿到一个空数据
         * @return
         */
        Type getType() {
            return ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
    }

    public static class MapCoCo extends CoCo<Map<String, List<String>>> {
        MapCoCo(Map<String, List<String>> data) {
            super(data);
        }
    }

    @Test
    public void parameterizedType() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("11", "22"));
        MapCoCo container = new MapCoCo(map);

        String s = JSON.toJSONString(map);
        System.out.println(s);

        Type type = container.getType();
        Object object = JSON.parseObject(s, type);
        System.out.println(object);
    }

    @Test
    public void onlyGeneric() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("11", "22"));
        CoCo<Map<String, List<String>>> container = new CoCo<>(map);

        String s = JSON.toJSONString(map);
        System.out.println(s);

        Type type = container.getType();
        Object object = JSON.parseObject(s, type);
        System.out.println(object);
    }
}
