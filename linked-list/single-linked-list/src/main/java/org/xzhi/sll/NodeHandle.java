package org.xzhi.sll;

import java.util.Stack;

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
     * 利用栈逆序打印，不改变单链表的结构
     */
    public void reversePrint() {
        PersonNode currentNode = head.next;
        Stack<PersonNode> nodeStack = new Stack<>();
        while (currentNode != null) {
            nodeStack.push(currentNode);
            currentNode = currentNode.next;
        }
        while (!nodeStack.isEmpty()) {
            log.info("[{}]", nodeStack.pop());
        }
    }

    /**
     * 单链表反转（头插法）
     */
    public void reverseList() {
        PersonNode currentNode = head.next;
        // 临时保存当前节点的下一个节点
        PersonNode curNextNode;
        PersonNode reversetHead = new PersonNode(null, null);
        while (currentNode != null) {
            curNextNode = currentNode.next;
            // 将当前的下个节点指向新头结点的下一个节点
            currentNode.next = reversetHead.next;
            reversetHead.next = currentNode;
            currentNode = curNextNode;
        }
        head.next = reversetHead.next;
    }

    /**
     * 查找单链表倒数第n个节点
     *
     * @param n
     * @return
     */
    public PersonNode findLastNode(int n) {
        PersonNode currentNode = head;
        if (n > size || n <= 0) {
            log.info("没有该节点");
            return null;
        }
        // 方式一
        for (int i = 0; i <= size - n; i++) {
            currentNode = currentNode.next;
        }

        // 方式二
        // int count = size - n + 1;
        // while (currentNode.next != null) {
        //     currentNode = currentNode.next;
        //     --count;
        //     if (count == 0) {
        //         break;
        //     }
        // }
        return currentNode;
    }

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
        PersonNode currentNode = head;
        headNextNullLog();
        while (currentNode.next != null) {
            PersonNode nextNode = currentNode.next;
            if (nextNode.name.equals(node.name) && nextNode.no.equals(node.no)) {
                currentNode.next = currentNode.next.next;
                --size;
                break;
            }
            currentNode = nextNode;
        }
    }

    /**
     * 从链表尾部添加节点
     *
     * @param node
     */
    public void tailAdd(PersonNode node) {
        // 方式一
        if (tail == null) {
            head.next = node;
        } else {
            tail.next = node;
        }
        tail = node;

        // 方式二
        // PersonNode currentNode = head;
        // while (currentNode.next != null) {
        //     currentNode = currentNode.next;
        // }
        // currentNode.next = node;
        ++size;
    }

    /**
     * 按no的升序插入链表，如果该no已存在，则添加失败
     */
    public void addOrderByNoAsc(PersonNode node) {
        PersonNode currentNode = head;
        if (headNextNull()) {
            head.next = node;
            ++size;
            return;
        }
        while (currentNode.next != null) {
            if (currentNode.next.no > node.no) {
                node.next = currentNode.next;
                currentNode.next = node;
                ++size;
                break;
            }
            if (currentNode.next.no.equals(node.no)) {
                log.info("该no[{}]已存在，插入失败", node.no);
                break;
            }

            currentNode = currentNode.next;
        }
    }

    /**
     * 打印所有节点
     */
    public void display() {
        PersonNode currentNode = head;
        headNextNullLog();
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            log.info("name[{}], no[{}]", currentNode.name, currentNode.no);
        }
        log.info("所有节点size[{}]", size);
    }

    public void displayPreLog(String msg) {
        log.info("[{}]", msg);
        display();
    }

    /**
     * 删除所有节点
     */
    public void delAll() {
        head.next = null;
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

    private void headNextNullLog() {
        if (headNextNull()) {
            log.info("节点为空");
        }
    }
}