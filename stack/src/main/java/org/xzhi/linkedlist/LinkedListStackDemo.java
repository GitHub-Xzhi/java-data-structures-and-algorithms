package org.xzhi.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * LinkedListStackDemo
 *
 * @author Xzhi
 * @date 2021-08-05 13:04
 */
@Slf4j
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        linkedListStack.push(1);
        linkedListStack.push(2);
        linkedListStack.push(3);
        log.info("peek[{}]", linkedListStack.peek());
        log.info("出栈[{}]", linkedListStack.pop());
        log.info("peek[{}]", linkedListStack.peek());
        log.info("出栈[{}]", linkedListStack.pop());
        log.info("peek[{}]", linkedListStack.peek());
        log.info("出栈[{}]", linkedListStack.pop());
        log.info("peek[{}]", linkedListStack.peek());
    }
}