package ysdlb.foundation.utils;

import java.util.ArrayList;
import java.util.List;

public class SubListUtil {
    /**
     * 按照 length 将 list 分割为多个小 list
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> subList(List<T> list, int length) {
        int size = list.size();
        int round = size / length + 1;
        // 是否整除
        boolean divisible = size % length == 0;

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            if (i == round - 1) {
                if (divisible) {
                    break;
                }
                result.add(list.subList(i * length, size));
            } else {
                result.add(list.subList(i * length, (i + 1) * length));
            }
        }
        return result;
    }

    public static <T> List<List<T>> subList2(List<T> list, int length) {
        int size = list.size();
        int round = size / length + 1;
        // 是否整除
        if (size % length == 0)
            round --;

        List<List<T>> result = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            if (i == round - 1) {
                result.add(list.subList(i * length, size));
            } else {
                result.add(list.subList(i * length, (i + 1) * length));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        List<List<String>> subLists = subList2(list, 5);
        System.out.println(subLists.size());
        System.out.println(subLists.isEmpty());

        list.add("a");
        subLists = subList2(list, 5);
        System.out.println(subLists.size());
        System.out.println(subLists.isEmpty());
    }
}
