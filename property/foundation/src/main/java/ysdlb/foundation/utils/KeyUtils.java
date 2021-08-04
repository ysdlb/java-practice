package ysdlb.foundation.utils;

public class KeyUtils {
    private static String generateKey(Object... objects) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            buffer.append(obj);
            if (i < objects.length - 1) {
                buffer.append("-");
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        Integer i = Integer.valueOf(3);
        String[] other = new String[]{"a", "b", "c"};

        String key = generateKey(i, other);
        System.out.println(key);

        key = generateKey(other);
        System.out.println(key);
    }
}
