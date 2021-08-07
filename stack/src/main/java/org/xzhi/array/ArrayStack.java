package org.xzhi.array;

import lombok.extern.slf4j.Slf4j;

/**
 * ArrayStack
 *
 * @author Xzhi
 * @date 2021-08-05 11:08
 */
@Slf4j
public class ArrayStack {

    private int maxSize;
    private Object[] arrays;
    private int index = -1;
    private int size;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arrays = new Object[maxSize];
    }

    /**
     * 入栈
     *
     * @param n
     * @return
     */
    public Object push(Object n) {
        if (size > maxSize - 1) {
            throw new RuntimeException("栈溢出");
        }
        ++index;
        ++size;
        arrays[index] = n;
        return n;
    }

    /**
     * 出栈
     *
     * @return
     */
    public Object pop() {
        emptyStackException();
        Object array = arrays[index];
        --size;
        --index;
        return array;
    }

    /**
     * 取栈顶值
     *
     * @return 取栈顶值
     */
    public Object peek() {
        emptyStackException();
        return arrays[index];
    }

    public boolean empty() {
        return size < 1;
    }

    private void emptyStackException() {
        if (empty()) {
            index = -1;
            throw new RuntimeException("栈空");
        }
    }

    public int size() {
        return size;
    }
}