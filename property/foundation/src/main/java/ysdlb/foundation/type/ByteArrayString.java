package ysdlb.foundation.type;

import java.util.ArrayList;
import java.util.List;

/**
 * 强转导致对范型擦除
 */
public class ByteArrayString {

    public static <T> List<T> convert() {
        List<byte[]> bytesList = new ArrayList<>();
        byte[] bytes = new byte[]{64,64,64};
        bytesList.add(bytes);
        return (List<T>) bytesList;
    }

    public static void main(String[] args) {
        List<String> strList = convert();
        for (String s: strList) {
            System.out.println(s);
        }
    }
}
