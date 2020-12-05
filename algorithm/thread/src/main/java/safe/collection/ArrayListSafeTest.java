package safe.collection;

import safe.collection.clazz.LockAddArrayList;

import java.util.List;

public class ArrayListSafeTest {
    public static void main(String[] args) throws InterruptedException {

        // 原生 ArrayList add 不安全
        // final List<Integer> list = new ArrayList<>();
        final List<Integer> list = new LockAddArrayList<>();
        // 线程A将1-1000添加到列表
        new Thread(() -> {
            for (int i = 1; i <= 1000; i++) {
                list.add(i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 线程B将1001-2000添加到列表
        new Thread(() -> {
            for (int i = 1001; i <= 2000; i++) {
                list.add(i);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 多线程状态下不可使用, 迭代器产生后，一旦其他线程对 list 进行了增删，就会使迭代器里的一个值与原List里的值不同
        // 此时，直接抛出 ConcurrentModificationException
        Runnable traversalByIterator = () ->  {
            System.out.println("now list size = " + list.size());
            for (Integer i : list) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        };
        Runnable traversalByForInt = () -> {
            System.out.println("now list size = " + list.size());
            for (int i = 0; i < list.size(); i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("第" + (i + 1) + "个元素为：" + list.get(i));
            }
        };
        new Thread(traversalByForInt).start();
    }
}
