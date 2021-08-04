package org.xzhi.dll;

/**
 *
 * @author Xzhi
 * @date 2021-08-02 15:27
 */
public class PersonNode {
    String name;
    Integer no;
    PersonNode next;
    PersonNode pre;

    public PersonNode(String name, Integer no) {
        this.name = name;
        this.no = no;
    }

    public String display() {
        return "PersonNode{" +
                "name='" + name + '\'' +
                ", no=" + no +
                ", next=" + next +
                ", pre=" + pre +
                '}';
    }
}