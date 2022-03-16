package bluedot.electrochemistry.commons.time;

import bluedot.electrochemistry.cache.BloomFilter;
import bluedot.electrochemistry.cache.bloom.DefaultBloomFilter;
import bluedot.electrochemistry.commons.time.datasource.TimeWindowSlidingDataSource;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Senn
 * @create 2022/3/7 10:55
 */
public class TimeWindowSliding {

    /**
     * 队列的总长度
     */
    private final int timeSliceSize;

    /**
     * 每个时间片的时长，以毫秒为单位
     */
    private final int timeMillisPerSlice;

    /**
     * 当前所使用的时间片位置
     */
    private final AtomicInteger cursor = new AtomicInteger(0);

    /**
     * 在一个完整窗口期内允许通过的最大阈值
     */
    private final int threshold;

    /**
     * 最小每个时间片的时长，以毫秒为单位
     */
    private static final int MIN_TIME_MILLIS_PER_SLICE = 50;

    /**
     * 最小窗口数量
     */
    private static final int DEFAULT_WINDOW_SIZE = 5;

    /**
     * 数据存储
     */
    private final TimeWindowSlidingDataSource timeWindowSlidingDataSource;

    /**
     * BloomFilter 布隆过滤器
     */
    private final BloomFilter<Object> bloomFilter;

    public TimeWindowSliding(TimeWindowSlidingDataSource timeWindowSlidingDataSource, BloomFilter<Object> filter, int windowSize, int timeMillisPerSlice, int threshold) {
        this.timeWindowSlidingDataSource = timeWindowSlidingDataSource;
        this.bloomFilter = filter;
        this.timeMillisPerSlice = timeMillisPerSlice;
        this.threshold = threshold;
        /* 低于一定窗口个数会丢失精准度 */
        int windowSize1 = Math.max(windowSize, DEFAULT_WINDOW_SIZE);
        /* 保证每个时间窗至少有2个窗口存储 不会重叠 */
        this.timeSliceSize = windowSize1 * 2 + 1;
        /* 初始化参数校验 */
        this.verifier();
    }

    /**
     *  初始化参数校验
     */
    private void verifier() {
        if (Objects.isNull(timeWindowSlidingDataSource) || timeMillisPerSlice < MIN_TIME_MILLIS_PER_SLICE || threshold <= 0) {
            throw new RuntimeException("初始化异常，参数不正确");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TimeWindowSliding window = new TimeWindowSliding(TimeWindowSlidingDataSource.defaultDataSource(), new DefaultBloomFilter(), 5, 1000, 50);
        for (int i = 0; i < 51; i++) {
            Thread.sleep(15);
            boolean allow = window.allowLimitTimes("a1");
            System.out.println(allow);
        }
        long time = System.currentTimeMillis();
        System.out.println(time);
        System.out.println(time / 200);
        int index = (int) ((time / 200) % 5 );
        System.out.println(index);
    }

    /**
     * 判断是否允许进行访问，未超过阈值的话才会对某个时间片 + 1
     */
    public synchronized boolean allowLimitTimes(String key) {
        if (bloomFilter.contain(key)) return false;
        int index = locationIndex();
        int sum = 0;
        // cursor不等于index，将cursor设置为index
        int oldCursor = cursor.getAndSet(index);
        if (Math.abs(index - oldCursor) > 1) {
            // 清零，访问量不大时会有时间片跳跃的情况
            clearBetween(oldCursor, index);
        }
        for (int i = 1; i < timeSliceSize; i++) {
            sum += timeWindowSlidingDataSource.getAllocAdoptRecordTimes(i, key);
        }

        // 阈值判断
        if (sum < threshold) {
            // 未超过阈值才 + 1
            this.timeWindowSlidingDataSource.allocAdoptRecord(index, key);
            return true;
        } else {
            clearBetween(oldCursor, index);
            bloomFilter.add(key);
        }
        return false;
    }

    /**
     * 将fromIndex~toIndex之间的时间片计数都清零
     * 极端情况下，当循环队列已经走了超过1个timeSliceSize以上，这里的清零并不能如期望的进行
     */
    private void clearBetween(int fromIndex, int toIndex) {
        this.timeWindowSlidingDataSource.clearBetween(fromIndex, toIndex, timeSliceSize);
    }

    private int locationIndex() {
        long time = System.currentTimeMillis();
        return (int) ((time / timeMillisPerSlice) % timeSliceSize);
    }
}