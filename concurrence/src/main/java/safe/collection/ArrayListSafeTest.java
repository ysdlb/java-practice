package safe.collection;

import safe.collection.clazz.LockAddArrayList;

import java.util.List;

/**
 * 1. read size
 * 2. add size
 * 3. save size
 * 4. assign list[size]
 *
 * 少数据的原因: 两个线程都拿到了相同值的 size，虽然各自都把 size++，但最终的size值相较原来，只是+1，在这个过程中少了一个
 * 数据为 null 的原因：一个线程成功把 size 加一且保存后，还没来得及赋值，另一个线程又把 size 加了一，这样 size + 1对应的那个位置就不会被赋值了
 */
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
