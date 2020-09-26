package sampling.pool;

import java.util.Random;

/**
 * n 个样本随机抽取 1 个（n 未知）
 * 指定一个大小为1的蓄水池，被选中的元素会进入留在蓄水池
 * 规定第i个元素被选中的概率为 1/i，那么它最终留在池中的概率为 1/i * i/i+1 * ... * n-1/n = 1/n
 */
public class OneSampleUnkownN {
    private static int reservoirSampling(int[] input) {
        Random random = new Random();
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= input.length; i++) {
            if (random.nextInt(i) < 1) {
                result = i - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final int N = 10;
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = i;
        }
        System.out.println(reservoirSampling(input));
    }

}
