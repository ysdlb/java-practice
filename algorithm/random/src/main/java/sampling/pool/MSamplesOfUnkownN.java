package sampling.pool;

import java.util.Arrays;
import java.util.Random;

/**
 * n 个样本随机抽取 m 个（n 未知）
 * 指定一个大小为m的蓄水池，被选中的元素会进入留在蓄水池
 * 当i <= m 时，进入蓄水池的概率为 1 ，留在蓄水池的概率为 1 * (m/m+1 * m-1/m + m+1-m/m+1) * ... * (m/n * m-1/m + n-m/n) = 1 * m/m+1 * ... * n-1/n = m/n
 * 当 i > m 时，进入蓄水池的概率为 m/i，留在蓄水池的概率为 m/i * (m/i+1 * m-1/m + i+1-m/i+1) * ... * (m/n * m-1/m + n-m/n) = m/i * i/i+1 * ... * n-1/n = m/n
 * 综上，每个元素留在蓄水池的概率为 m/n
 */
public class MSamplesOfUnkownN {
    /**
     * 如果 n == m 时，这个算法有问题，进入蓄水池的概率虽然为1，但留在蓄水池的概率不一定为1
     * 当 n > m时，这个算法也有问题，因为有概率存在 [0, m)之间一直没有随机到的数，使得这个位置的值一直为初始值。
     * 该问题可以通过直接拷贝池子容量的数据进入池子，循环从m+1开始来解决
     * @param input
     * @param result
     */
    private static void reservoirSampling(int[] input, int[] result) {
        int m = result.length;
        int n = input.length;
        Random random = new Random();

        int d = 0;
        /**
        for (int i = 1; i <= n; i++) {
            if ((d = random.nextInt(i)) < m) {
                result[d] = i - 1;
            }
        }
         */
        System.arraycopy(input, 0, result, 0, result.length);
        for (int i = m + 1; i < n; i ++) {
            if ((d = random.nextInt(i)) < m) {
                result[d] = i - 1;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 100000;
        final int M = 10;
        int[] input = new int[N];
        int[] result = new int[M];
        for (int i = 0; i < N; i++) {
            input[i] = i;
        }
        reservoirSampling(input, result);
        Arrays.stream(result).forEach(System.out::println);
    }
}
