package org.xzhi.postfixexpression;

import java.util.Stack;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 后缀表达式（逆波兰表达式）
 *
 * @author Xzhi
 * @date 2021-08-07 09:16
 */
@Slf4j
public class PostfixExpression {

    /**
     * 加、减等级
     */
    private static final int ADD_SUB = 1;
    /**
     * 乘、除等级
     */
    private static final int MUL_DIV = 2;
    /**
     * 不是运算符
     */
    private static final int NO_OPERATOR = -1;
    private static final String ADD_STR = "+";
    private static final String SUB_STR = "-";
    private static final String MUL_STR = "*";
    private static final String DIV_STR = "/";

    private Stack<Object> postfixExpStack;
    private Stack<String> symbolStack;

    /**
     * 模拟计算器
     *
     * @param expStr
     * @return
     */
    public int calculator(String expStr) {
        getPostfixExpression(expStr);

        Object[] peArray = new Object[postfixExpStack.size()];
        // 依次弹出postfixExpStack中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        for (int i = peArray.length - 1; i > -1; i--) {
            peArray[i] = postfixExpStack.pop();
        }

        /*
        从左往右扫描表达式，遇到数字时，将数字压入堆栈
        遇到运算符时，弹出栈顶的两个数，计算（注意顺序：次顶元素 -处理->栈顶元素）
        并将结果入栈，重复上述过程直到表达式最右端
        最后结果即为计算结果
         */
        for (Object item : peArray) {
            if (item instanceof Integer) {
                postfixExpStack.push(item);
            } else {
                int first = (int) postfixExpStack.pop();
                int second = (int) postfixExpStack.pop();
                int compute = compute(second, first, String.valueOf(item));
                postfixExpStack.push(compute);
            }
        }
        return (int) postfixExpStack.pop();
    }

    /**
     * 中缀表达式转后缀表达式步骤：
     * <p>
     * (1) 初始化两个栈：运算符栈symbolStack和后缀栈postfixExpStack；
     * <p>
     * (2) 从左至右扫描中缀表达式；
     * <p>
     * (3) 遇到操作数时，将其压入postfixExpStack；
     * <p>
     * (4) 遇到运算符时，比较其与symbolStack栈顶运算符的优先级：
     * <p>
     * (4-1) 如果symbolStack为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     * <p>
     * (4-2) 比栈顶高，也将运算符压入symbolStack（注意转换为前缀表达式时是优先级较高或相同，而这里则不包括相同的情况）；
     * <p>
     * (4-3) 比栈顶低或相同，将symbolStack栈顶的运算符弹出并压入到postfixExpStack中，再次转到(4-1)与symbolStack中新的栈顶运算符相比较；
     * <p>
     * (5) 遇到括号时：
     * <p>
     * (5-1) 如果是左括号“(”，则直接压入symbolStack；
     * <p>
     * (5-2) 如果是右括号“)”，则依次弹出symbolStack栈顶的运算符，并压入postfixExpStack，直到遇到左括号为止，此时将这一对括号丢弃；
     * <p>
     * (6) 重复步骤(2)至(5)，直到表达式的最右边；
     * <p>
     * (7) 将symbolStack中剩余的运算符依次弹出并压入postfixExpStack；
     * <p>
     * (8) 依次弹出postfixExpStack中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     *
     * @param expStr
     */
    private void getPostfixExpression(String expStr) {
        symbolStack = new Stack<>();
        postfixExpStack = new Stack<>();
        StringBuilder numStr = new StringBuilder();
        for (int i = 0; i < expStr.length(); i++) {
            String s = String.valueOf(expStr.charAt(i));
            if (NumberUtil.isInteger(s)) {
                numStr.append(s);
            } else {
                String num = numStr.toString();
                if (StrUtil.isNotEmpty(numStr)) {
                    postfixExpStack.push(Integer.valueOf(num));
                    numStr.setLength(0);
                }
                priorityDeal(s, peekNullDeal());
            }
        }
        if (numStr.length() != 0) {
            postfixExpStack.push(Integer.valueOf(numStr.toString()));
        }
        /*
        剩余的运算符依次弹出并压入
         */
        while (!symbolStack.isEmpty()) {
            postfixExpStack.push(symbolStack.pop());
        }
    }

    /**
     * 符号操作
     *
     * @param a         数字a
     * @param b         数字b
     * @param symbolStr 符号字符串
     * @return 计算的结果
     */
    private int compute(int a, int b, String symbolStr) {
        switch (symbolStr) {
            case ADD_STR:
                return a + b;
            case SUB_STR:
                return a - b;
            case MUL_STR:
                return a * b;
            case DIV_STR:
                return a / b;
            default:
                throw new RuntimeException(symbolStr + "该符号不在范围");
        }
    }

    /**
     * 符号优先级处理
     *
     * @param s
     * @param peekEle
     * @return
     */
    private void priorityDeal(String s, String peekEle) {
        int curSymbolConvert = symbolPriorityConvert(s);
        int peekSymbolConvert = symbolPriorityConvert(peekEle);
        if (StrUtil.isEmpty(peekEle)) {
            symbolStack.push(s);
        } else if (curSymbolConvert == NO_OPERATOR) {
            bracketDeal(s, peekEle);
        } else if (curSymbolConvert > peekSymbolConvert) {
            symbolStack.push(s);
        } else {
            postfixExpStack.push(symbolStack.pop());
            priorityDeal(s, peekNullDeal());
        }
    }

    /**
     * 括号处理
     *
     * @param s
     * @param peekEle
     */
    private void bracketDeal(String s, String peekEle) {
        if ("(".equals(s)) {
            symbolStack.push(s);
        } else if (")".equals(s)) {
            while (!symbolStack.peek().equals("(")) {
                postfixExpStack.push(symbolStack.pop());
            }
            // 丢弃左括号“)”
            symbolStack.pop();
        }
    }

    /**
     * 操作符优先级转数字
     *
     * @param symbol
     * @return
     */
    private int symbolPriorityConvert(String symbol) {
        if (ADD_STR.equals(symbol) || SUB_STR.equals(symbol)) {
            return ADD_SUB;
        } else if (MUL_STR.equals(symbol) || DIV_STR.equals(symbol)) {
            return MUL_DIV;
        }
        return NO_OPERATOR;
    }

    /**
     * 栈空异常处理
     *
     * @return
     */
    private String peekNullDeal() {
        try {
            return symbolStack.peek();
        } catch (Exception e) {
            return null;
        }
    }
}