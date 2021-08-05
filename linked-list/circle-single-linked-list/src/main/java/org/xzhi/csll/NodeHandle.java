package org.xzhi.csll;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xzhi
 * @date 2021-08-04 17:01
 */
@Slf4j
public class NodeHandle {
    /**
     * 创建第一个节点
     */
    private BoyNode first;
    private int size;

    /**
     * 约瑟夫问题
     *
     * @param startNo  从第几个小孩开始数
     * @param countNum 数几下
     * @param nums     开始创建多少个小孩
     */
    public void josephus(int startNo, int countNum, int nums) {
        if (startNo < 1 || startNo > nums) {
            log.info("{}", "输入有误");
            return;
        }
        add(nums);
        int count = 0;
        BoyNode cur = first;
        BoyNode pre = null;
        for (int i = 1; i < startNo; i++) {
            cur = cur.getNext();
        }
        while (first.getNext() != first) {
            ++count;
            if (count == countNum - 1) {
                pre = cur;
            }
            if (count == countNum) {
                count = 0;
                if (pre != null) {
                    pre.setNext(cur.getNext());
                }
                first = first.getNext();
                log.info("第[{}]号出圈", cur.getNo());
            }
            cur = cur.getNext();
        }
        log.info("最后留在圈中[{}]", first.getNo());
    }

    /**
     * 节点添加，构建环形单向链表
     *
     * @param nums
     */
    public void add(int nums) {
        if (nums < 1) {
            log.info("nums输入有误");
            return;
        }
        BoyNode curNode = null;
        for (int i = 1; i <= nums; i++) {
            BoyNode boyNode = new BoyNode(i);
            if (i == 1) {
                first = boyNode;
                first.setNext(first);
                // 让curNode指向第一个小孩
                curNode = first;
            } else {
                curNode.setNext(boyNode);
                boyNode.setNext(first);
                curNode = boyNode;
            }
        }
        size = nums;
    }

    public void display() {
        if (first == null) {
            log.info("节点为空");
            return;
        }
        BoyNode node = first;
        while (true) {
            log.info("no[{}]", node.getNo());
            if (node.getNext() == first) {
                break;
            }
            node = node.getNext();
        }
    }

}