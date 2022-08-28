package bluedot.electrochemistry.test;

import bluedot.electrochemistry.cache.bloom.Filter;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author Senn
 * @create 2022/3/16 16:24
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")),1000000);
    }
    private void test() {
        Filter bloomFilter = new Filter(1100000);
        long addStart = System.currentTimeMillis();
        bloomFilter.add("爱");
        bloomFilter.add("意");
        bloomFilter.add("随");
        bloomFilter.add("风");
        bloomFilter.add("起");

        System.out.println(bloomFilter.addIfNoExit("风"));
        System.out.println(bloomFilter.addIfNoExit("起"));
        System.out.println(bloomFilter.addIfNoExit("意"));
        System.out.println(bloomFilter.addIfNoExit("难"));
        System.out.println(bloomFilter.addIfNoExit("平"));
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.add(String.valueOf(i));
        }
        System.out.println("存储元素用时：" + "" + (System.currentTimeMillis() - addStart));
        System.out.println("----------------over----------------");
//
//        System.out.println("bloomFileter占用内存：" + bloomFilter.bitSet.size() / 1024 +"KB");
//        System.out.println("bloomFileter占用内存：" + bloomFilter.bitSet.size() / (1024 * 1024) +"MB");
//        System.out.println("bloomFileter占用内存：" + bloomFilter.bitSet.size() / (1024 * 1024 * 1024) +"MB");

        System.out.println("------------------------------------");

        System.out.println(bloomFilter.addIfNoExit("难"));
        System.out.println(bloomFilter.addIfNoExit("平"));
    }
}
