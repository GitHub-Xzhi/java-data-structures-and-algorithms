package org.xzhi.recursion.maze;

import lombok.extern.slf4j.Slf4j;

/**
 * 迷宫
 *
 * @author Xzhi
 * @date 2021-08-08 10:46
 */
@Slf4j
public class Maze {
    public static void main(String[] args) {
        // 8行 7列
        int[][] map = new int[8][7];
        // 行的长度
        int rows = map.length;
        // 列的长度
        int columns = map[0].length;
        // 1表示墙，上下行两行设置为1
        for (int i = 0; i < columns; i++) {
            map[0][i] = 1;
            map[rows - 1][i] = 1;
        }
        // 左右两列设置为1
        for (int i = 0; i < rows; i++) {
            map[i][0] = 1;
            map[i][columns - 1] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        for (int[] ints : map) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
        setWay(map, 1, 1);
        log.info("");
        for (int[] ints : map) {
            for (int j = 0; j < columns; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归给小球找路
     * <p>
     * 说明<br>
     * 1.map[6][5]表示终点<br>
     * 2.i,j表示从地图的哪个位置开始出发(1,1)<br>
     * 3.如果小球碰到map[6][5]，说明通路找到了<br>
     * 4.约定：当map[i][j]为0表示改点没有走过；1表示墙；2表示通路可以走；3表示该点已经走过了，但是走不通<br>
     * 5.在走迷宫时，需要确定一个策略（方法）例如：下->右->上->左
     *
     * @param map 地图
     * @param i   从地图哪行开始找
     * @param j   从地图哪列开始找
     * @return true表示找到通路
     */
    private static boolean setWay(int[][] map, int i, int j) {
        // 先确定终点
        if (map[6][5] == 2) {
            // 通路已经找到了
            return true;
        } else {
            // 当前的路没有走过，那就按照策略来走
            if (map[i][j] == 0) {
                // 假设该点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 该点走不通
                    map[i][j] = 3;
                    return false;
                }
            }
        }
        return false;
    }
}