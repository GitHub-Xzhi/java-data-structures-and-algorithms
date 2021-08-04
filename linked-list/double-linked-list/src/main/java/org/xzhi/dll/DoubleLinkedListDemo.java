package org.xzhi.dll;

import lombok.extern.slf4j.Slf4j;

/**
 * DoubleLinkedList
 *
 * @author Xzhi
 * @date 2021-08-04 08:03
 */
@Slf4j
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        NodeHandle nodeHandle = new NodeHandle();
        // 头插法
        nodeHandle.tailAdd(new PersonNode("a", 1));
        nodeHandle.tailAdd(new PersonNode("b", 2));
        nodeHandle.tailAdd(new PersonNode("c", 3));
        nodeHandle.tailAdd(new PersonNode("d", 4));

        nodeHandle.display("删除前");
        nodeHandle.del(new PersonNode("b", 2));
        nodeHandle.display("删除后");

        nodeHandle.delAll();

        nodeHandle.headAdd(new PersonNode("a", 1));
        nodeHandle.headAdd(new PersonNode("b", 2));
        nodeHandle.headAdd(new PersonNode("c", 3));
        nodeHandle.headAdd(new PersonNode("d", 4));
        nodeHandle.display();

    }
}