package org.xzhi.shellsort;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序，也是插入排序的一种，其效率更高。
 *
 * @author Xzhi
 * @date 2021-08-10 19:00
 */
@Slf4j
public class ShellSortDemo {
    private static int count = 0;

    public static void main(String[] args) {
        // int[] a = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] a = {7, 3, 1, 9, 5, 4, 2, 8, 6};
        sort(a);
        log.info("a{}，总次数[{}]", a, count);

        pressureTest(100000, false);
    }

    public static void sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int tmp = array[j];
                while (j - gap >= 0 && tmp < array[j - gap]) {
                    array[j] = array[j - gap];
                    j -= gap;
                }
                array[j] = tmp;
            }
        }
    }

    public static void sort2(int[] array) {
        // 希尔增量
        int d = array.length;
        while (d > 1) {
            d /= 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i += d) {
                    int tmp = array[i];
                    int j;
                    for (j = i - d; (j >= 0) && (array[j] > tmp); j -= d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = tmp;
                }
            }
        }
    }

    /**
     * 分组测试
     *
     * @param array
     */
    private static void group(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            log.info("[{}]组", gap);
            for (int i = 0; i < gap; i++) {
                List<Integer> list = new ArrayList<>();
                int index = i;
                while (index < array.length) {
                    // 每个元素之间的间距为gap的为一组
                    list.add(array[index]);
                    index += gap;
                }
                log.info("list{}", list);
            }

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
        // sort2(pressure);
        stopwatch.stop();
        if (printEle) {
            log.info("{}", pressure);
        }
        log.info("[{}]", stopwatch.toString());
    }
}