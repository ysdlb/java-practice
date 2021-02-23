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
        Random random = new Random();

        int low = 0, high = 0, all = 10000000;
        for (int i = 0; i < all; i++) {

            long uid = random.nextInt(80000000);
            long deviceId = random.nextLong();
            String str = String.valueOf(i) + (i) + "rec_trans";
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            long hash = crc32.getValue();

            long value = hash % 100;
            if (value < 50) {
                low ++;
            } else {
                high ++;
            }
        }

        System.out.println(String.format("low: %-10d, %f", low, ((double)low)/all));
        System.out.println(String.format("high: %-10d, %f", high, ((double)high)/all));
    }
}
