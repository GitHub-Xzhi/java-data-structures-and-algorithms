package org.xzhi.is;

import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

/**
 * 插入排序（直接插入排序）
 *
 * @author Xzhi
 * @date 2021-08-10 15:36
 */
@Slf4j
public class InsertSortDemo {
    private static int count = 0;

    public static void main(String[] args) {
        int[] a = {10, 7, 9, 6, 8, 2, 5, 11};
        // sort(a);
        optimize(a);
        log.info("a{}，总次数[{}]", a, count);

        pressureTest(100000, false);
    }

    /**
     * 优化版
     *
     * @param array
     */
    public static void optimize(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 待插入值的前面的下标
            int index = i - 1;
            // 待插入值
            int insertVal = array[i];
            while (index >= 0 && insertVal < array[index]) {
                ++count;
                array[index + 1] = array[index];
                --index;
            }
            array[index + 1] = insertVal;
            // log.info("第[{}]轮排序{}", i, array);
        }
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 待插入值的前面的下标
            int index = -1;
            // 待插入值
            int insertVal = array[i];
            // 和前面的数比较
            for (int j = i - 1; j >= 0; j--) {
                ++count;
                /*
                如果前一个的值比要插入的值还大，即还没有找到要插入的位置，
                就将其向后移动一位，
                并记录该下标
                 */
                if (insertVal < array[j]) {
                    array[j + 1] = array[j];
                    index = j;
                }
            }
            if (index != -1) {
                array[index] = insertVal;
            }
            // log.info("第[{}]轮排序{}", i, array);
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
        optimize(pressure);
        // sort(pressure);
        stopwatch.stop();
        if (printEle) {
            log.info("{}", pressure);
        }
        log.info("[{}]", stopwatch.toString());
    }

}