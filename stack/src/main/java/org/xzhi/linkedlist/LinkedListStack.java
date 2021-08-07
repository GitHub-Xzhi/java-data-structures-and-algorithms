package org.xzhi.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * LinkedListStack
 *
 * @author Xzhi
 * @date 2021-08-05 13:04
 */
@Slf4j
public class LinkedListStack<T> {

    private Node<T> head = new Node<>();
    private int size = 0;

    /**
     * 入栈
     *
     * @param n
     * @return
     */
    public T push(T n) {
        Node<T> node = new Node<>();
        node.setT(n);
        if (head.getNext() != null) {
            Node<T> next = head.getNext();
            node.setNext(next);
        }
        head.setNext(node);
        ++size;
        return n;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
        emptyStackException();
        --size;
        Node<T> node = head.getNext();
        head.setNext(node.getNext());
        return node.getT();
    }

    /**
     * 取栈顶值
     *
     * @return 取栈顶值
     */
    public T peek() {
        emptyStackException();
        return head.getNext().getT();
    }

    public boolean empty() {
        return size < 1;
    }

    private void emptyStackException() {
        if (empty()) {
            size = 0;
            throw new RuntimeException("栈空");
        }
    }

    public int size() {
        return size;
    }

    // @Setter
    // @Getter
    class Node<E> {
        private E t;
        private Node<E> next;

        public E getT() {
            return t;
        }

        public void setT(E t) {
            this.t = t;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}