package ysdlb.foundation.generic;

import java.util.ArrayList;
import java.util.List;

public class TInnerUsage {

    public static void main(String[] args) {

        TInnerUsage innerUsage = new TInnerUsage();

        //获取string类型
        List<String> array = new ArrayList<String>();
        array.add("test");
        array.add("doub");
        String str = innerUsage.getListFisrt(array);
        System.out.println(str);

        //获取nums类型
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(12);
        nums.add(13);

        Integer num = innerUsage.getListFisrt(nums);
        System.out.println(num);
    }

    /**
     * 这个<T> T 可以传入任何类型的List
     * 参数T
     *     第一个 表示是泛型
     *     第二个 表示返回的是T类型的数据
     *     第三个 限制参数类型为T
     * @param data
     * @return
     */
    private <T> T getListFisrt(List<T> data) {
        if (data == null || data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

}
