package hash.crc;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.zip.CRC32;

public class CRC32Test {

    /**
     * 测试 crc32 后缀相同时候的分布情况
     */
    @Test
    void crc32SuffixSame() {

        int[] distribution = new int[100];
        int all = 10000000, len = distribution.length;

        for (int i = 0, uid = 700000, deviceId = 50000000;
             i < all;
             i++, uid += 2, deviceId += 3) {

            String str = String.valueOf(i) + (i) + "test_trans";
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            long hash = crc32.getValue();

            long value = hash % len;
            distribution[(int)value] ++;
        }

        for (int i = 0; i < len; i++) {
            int num = distribution[i];
            System.out.printf("bucket: %-10d, %f%n", i, ((double)num)/all);
        }
    }
}
