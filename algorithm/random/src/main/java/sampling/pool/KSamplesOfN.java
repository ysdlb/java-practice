package sampling.pool;

import java.util.Random;

/**
 * 蓄水池算法的开胃小菜:
 * N 个样本随机抽取 K 个不同样本（N 已知）
 * 只保证有序，不保证最后结果满足K个
 */
public class KSamplesOfN {
    public static final int N = 100;
    public static final int K = 5;

    public static void main(String[] args) {
        int n = N, k = K;

        Random random = new Random();

        for (int i = 0; i < n; i++) {
            if (random.nextInt(n) < k) {
                System.out.println(i);
                k --;
            }

        }
        System.out.println(random.nextInt(1));
    }

}
