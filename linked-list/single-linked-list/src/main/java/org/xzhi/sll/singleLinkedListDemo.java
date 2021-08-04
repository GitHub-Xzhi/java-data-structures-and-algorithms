package org.xzhi.sll;

import lombok.extern.slf4j.Slf4j;

/**
 * singleLinkedListDemo
 *
 * @author Xzhi
 * @date 2021-08-02 16:27
 */
@Slf4j
public class singleLinkedListDemo {

    public static void main(String[] args) {
        NodeHandle nodeHandle = new NodeHandle();
        // nodeHandle.add(new PersonNode("a", 1));
        // nodeHandle.add(new PersonNode("b", 3));
        // nodeHandle.add(new PersonNode("c", 6));
        // nodeHandle.add(new PersonNode("c1", 7));
        // nodeHandle.list();
        // PersonNode c = new PersonNode("c", 5);
        // nodeHandle.addOrderByNoAsc(c);
        // nodeHandle.addOrderByNoAsc(new PersonNode("c", 1));
        // nodeHandle.list();
        // nodeHandle.del(c);
        // nodeHandle.updateByNo(new PersonNode("修改", 6));
        // nodeHandle.list();

        // // 查找单链表倒数第n个节点
        // PersonNode lastNode = nodeHandle.findLastNode(2);
        // log.info("lastNode[{}]", lastNode);

        // // 删除所有节点
        // nodeHandle.delAll();

        // 单链表反转
        nodeHandle.tailAdd(new PersonNode("a", 1));
        nodeHandle.tailAdd(new PersonNode("b", 2));
        nodeHandle.tailAdd(new PersonNode("c", 3));
        nodeHandle.tailAdd(new PersonNode("d", 4));
        nodeHandle.displayPreLog("反转前");
        nodeHandle.reverseList();
        nodeHandle.displayPreLog("反转后");

        // 利用栈逆序打印，不改变单链表的结构
        // nodeHandle.reversePrint();
    }
}