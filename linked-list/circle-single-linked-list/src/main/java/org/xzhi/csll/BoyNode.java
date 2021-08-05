package org.xzhi.csll;

import lombok.Getter;
import lombok.Setter;

/**
 * BoyNode
 *
 * @author Xzhi
 * @date 2021-08-04 16:56
 */
@Getter
@Setter
public class BoyNode {
    private int no;
    private BoyNode next;

    public BoyNode(int no) {
        this.no = no;
    }
}