package org.xzhi.dll;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xzhi
 * @date 2021-08-02 16:28
 */
@Slf4j
public class NodeHandle {

    private PersonNode head = new PersonNode(null, null);
    private PersonNode tail;
    private int size;

    /**
     * 根据no修改节点信息
     *
     * @param node
     */
    public void updateByNo(PersonNode node) {
        PersonNode currentNode = head;
        headNextNullLog();
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            if (currentNode.no.equals(node.no)) {
                currentNode.name = node.name;
                break;
            }
        }
    }

    /**
     * 删除指定节点
     *
     * @param node
     */
    public void del(PersonNode node) {
        PersonNode currentNode = head.next;
        headNextNullLog();
        while (currentNode != null) {
            if (currentNode.name.equals(node.name) && currentNode.no.equals(node.no)) {
                currentNode.pre.next = currentNode.next;
                currentNode.next.pre = currentNode.pre;
                --size;
                break;
            }
            currentNode = currentNode.next;
        }
    }

    /**
     * 尾插法
     *
     * @param node
     */
    public void tailAdd(PersonNode node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
        } else {
            tail.next = node;
            node.pre = tail;
        }
        // 把最后个节点设置为最新的节点
        tail = node;
        ++size;
    }

    /**
     * 头插法
     *
     * @param node
     */
    public void headAdd(PersonNode node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
            node.next = tail;
        } else {
            PersonNode currentNode = head.next;
            currentNode.pre = node;
            node.next = currentNode;
            head.next = node;
            node.pre = head;
        }
        ++size;
    }
    // /**
    //  * 头插法
    //  *
    //  * @param node
    //  */
    // public void headAdd(PersonNode node) {
    //     if (head.pre == null) {
    //         head.pre = node;
    //         node.next = head;
    //     } else {
    //         tail.pre = node;
    //         node.next = tail;
    //     }
    //     // 把最后个节点设置为最新的节点
    //     tail = node;
    //     ++size;
    // }

    /**
     * 打印所有节点
     */
    public void display() {
        PersonNode currentNode = head;
        boolean tailAddFlag = false;
        headNextNullLog();
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            log.info("name[{}], no[{}]", currentNode.name, currentNode.no);
        }
        log.info("所有节点size[{}]", size);
    }

    public void display(String msg) {
        log.info("[{}]", msg);
        display();
    }

    /**
     * 删除所有节点
     */
    public void delAll() {
        head = new PersonNode(null, null);
        tail = null;
        size = 0;
    }

    /**
     * 判断头节点的下个节点是否为空
     *
     * @return
     */
    private boolean headNextNull() {
        return head.next == null;
    }

    private boolean headNextNullLog() {
        if (headNextNull()) {
            log.info("节点为空");
        }
        return headNextNull();
    }
}