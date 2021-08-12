package org.xzhi.bs;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

/**
 * BubbleSortDemo
 *
 * @author Xzhi
 * @date 2021-08-05 21:19
 */
@Slf4j
public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] a = {5, -1, 2, 3, 2, 1, 4, 8, 1};
        pressureTest(100000, false);
    }

    /**
     * 优化
     *
     * @param array
     */
    public static void sortOptimize(int[] array) {
        int tmp;
        // 标识变量，表示是否进行交换过
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    flag = true;
                    tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
            if (!flag) {
                // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false;
            }
            // log.info("第[{}]趟排序后的数组{}", i + 1, array);
        }
    }

    public static void sort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
            log.info("第[{}]趟排序后的数组{}", i + 1, array);
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
        sortOptimize(pressure);
        stopwatch.stop();
        if (printEle) {
            log.info("{}", pressure);
        }
        log.info("[{}]", stopwatch.toString());
    }
}