package org.xzhi.postfixexpression;

import lombok.extern.slf4j.Slf4j;

/**
 * 后缀表达式（逆波兰表达式）演示
 *
 * @author Xzhi
 * @date 2021-08-06 19:01
 */
@Slf4j
public class PostfixExpressionDemo {
    public static void main(String[] args) {
        PostfixExpression postfixExpression = new PostfixExpression();
        // String expStr = "1+1*3*4-2*6-9+1";
        String expStr = "2*9+3-2*(10-3)/14";
        log.info("[{}]", postfixExpression.calculator(expStr));;
    }
}