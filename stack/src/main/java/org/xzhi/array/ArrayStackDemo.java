package org.xzhi.array;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * ArrayStackDemo
 *
 * @author Xzhi
 * @date 2021-08-05 11:07
 */
@Slf4j
public class ArrayStackDemo {
    private static final String ADD_STR = "+";
    private static final String SUBTRACT_STR = "-";
    private static final String MULTIPLY_STR = "*";
    private static final String DIVIDE_STR = "/";


    public static void main(String[] args) {
        // ArrayStack arrayStack = new ArrayStack(3);
        // arrayStack.push(1);
        // arrayStack.push(2);
        // arrayStack.push(3);
        // log.info("size[{}]", arrayStack.size());
        // log.info("pop[{}]", arrayStack.pop());
        // log.info("peek[{}]", arrayStack.peek());
        // log.info("size[{}]", arrayStack.size());
        // log.info("pop[{}]", arrayStack.pop());
        // log.info("peek[{}]", arrayStack.peek());
        // log.info("pop[{}]", arrayStack.pop());
        // log.info("size[{}]", arrayStack.size());
        // log.info("peek[{}]", arrayStack.peek());

        /*
        计算器有些bug
         */
        System.out.println(calculator("1+1*3*4-2*6-9+1"));
    }

    /**
     * 模拟计算器
     *
     * @param expStr 表达式
     * @return 计算后的结果
     */
    public static int calculator(String expStr) {
        ArrayStack numStack = new ArrayStack(100);
        ArrayStack symbolStack = new ArrayStack(100);
        for (int i = 0; i < expStr.length(); i++) {
            char c = expStr.charAt(i);
            String s = String.valueOf(c);
            if (NumberUtil.isInteger(s)) {
                numStack.push(Integer.valueOf(s));
            } else {
                if (!symbolStack.empty()) {
                    String pop = (String) symbolStack.pop();
                    if (symbolLowPriority(s, pop)) {
                        int numA = (int) numStack.pop();
                        int numB = (int) numStack.pop();
                        int result = symbolOperate(numB, numA, pop);
                        numStack.push(result);
                    } else {
                        symbolStack.push(pop);
                    }
                }
                symbolStack.push(s);
            }
        }
        while (!numStack.empty()) {
            int a = (int) numStack.pop();
            int b = (int) numStack.pop();
            String symbolStr = (String) symbolStack.pop();
            int result = symbolOperate(b, a, symbolStr);
            numStack.push(result);
            if (symbolStack.empty()) {
                return (int) numStack.peek();
            }
        }
        return 0;
    }


    public static boolean symbolLowPriority(String cur, String b) {
        if (((cur.equals(ADD_STR) || cur.equals(SUBTRACT_STR)) && (b.equals(MULTIPLY_STR) || b.equals(DIVIDE_STR))) ||
                ((cur.equals(MULTIPLY_STR) || cur.equals(DIVIDE_STR)) && (b.equals(MULTIPLY_STR) || b.equals(DIVIDE_STR)))) {
            return true;
        }
        return false;
    }

    /**
     * 符号操作
     *
     * @param a         数字a
     * @param b         数字b
     * @param symbolStr 符号字符串
     * @return 计算的结果
     */
    public static int symbolOperate(int a, int b, String symbolStr) {
        switch (symbolStr) {
            case ADD_STR:
                return a + b;
            case SUBTRACT_STR:
                return a - b;
            case MULTIPLY_STR:
                return a * b;
            case DIVIDE_STR:
                return a / b;
            default:
                throw new RuntimeException(symbolStr + "该符号不在范围");
        }
    }
}