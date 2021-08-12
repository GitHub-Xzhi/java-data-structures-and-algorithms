package org.xzhi.ss;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序（直接选择排序）
 *
 * @author Xzhi
 * @date 2021-08-10 10:32
 */
@Slf4j
public class SelectSortDemo {
    public static void main(String[] args) {
        int[] a = {5, -1, 2, 3, 2, 1, 4, 1, 8};
        log.info("排序前{}", a);
        sort(a);
        log.info("排序后{}", a);

        pressureTest(100000, false);
    }

    /**
     * 从小到大排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;
            int tmp = array[i];
            for (int j = i + 1; j < array.length; j++) {
                // 从小到大。如果是从大到小，只需将>改成<即可
                if (tmp > array[j]) {
                    tmp = array[j];
                    index = j;
                }
            }
            if (index != i) {
                array[index] = array[i];
                array[i] = tmp;
            }
            // log.info("第[{}]轮排序{}", i + 1, array);
        }
    }

    /**
     * 压测
     *
     * @param maxLength 数组最大长度
     * @param printEle  是否打印元素
     */
    private static void pressureTest(int maxLength, boolean printEle) {
        int[] pressure = new int[maxLength];
        for (int i = 0; i < pressure.length; i++) {
            pressure[i] = (int) (Math.random() * maxLength);
        }
        Stopwatch stopwatch = Stopwatch.createStarted();
        sort(pressure);
        stopwatch.stop();
        if (printEle) {
            log.info("{}", pressure);
        }
        log.info("[{}]", stopwatch.toString());
    }
}