package org.xzhi.csll;

import lombok.extern.slf4j.Slf4j;

/**
 * 约瑟夫问题（约瑟夫环、丢手绢问题）
 *
 * @author Xzhi
 * @date 2021-08-04 16:59
 */
@Slf4j
public class JosephusDemo {
    public static void main(String[] args) {
        NodeHandle nodeHandle = new NodeHandle();
        // nodeHandle.add(5);
        // nodeHandle.display();
        // 约瑟夫问题
        nodeHandle.josephus(10, 20, 125);
    }
}