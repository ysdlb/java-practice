package ysdlb.foundation;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.platform.commons.util.StringUtils;

/**
 * @author zhwu
 * @date 2021/11/10 00:37
 */
public class CompareUtil {

    /**
     * 判断是否发生变化
     *
     * @param newValue
     * @param oldValue
     * @return
     */
    public static boolean isChanged(String newValue, String oldValue) {
        if (StringUtils.isBlank(newValue) && StringUtils.isBlank(oldValue)) {
            return Boolean.FALSE;
        } else if (newValue != null && oldValue != null) {
            return !newValue.equals(oldValue);
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * 比较两个差异
     *
     * @param oldObject 源
     * @param newObject 目标
     * @return true代表二者有差异，false代表二者无差异
     */
    public static Boolean compareObject(Object newObject, Object oldObject) {
        //如果source为null但是target不为空，则说明发生了变化
        if (oldObject == null) {
            if (newObject != null) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        return !oldObject.equals(newObject);
    }



    public static String[] split(String field) {
        return field.split("\\.");
    }

    public static String replaceArray(String field) {
        if (field.contains("[]")) {
            return field.replace("[]", "");
        }
        return field;
    }


    public static Boolean isSimpleObject(Object object) {
        if (isJsonObject(object)) {
            return false;
        } else if (isJsonArray(object)) {
            return false;
        } else {
            return true;
        }
    }

    public static Boolean isJsonObject(Object o) {
        if (o instanceof JSONObject) {
            return true;
        }
        return false;
    }

    public static Boolean isJsonArray(Object o) {
        if (o instanceof JSONArray) {
            return true;
        }
        return false;
    }


}
