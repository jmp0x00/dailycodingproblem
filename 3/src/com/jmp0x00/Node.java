package com.jmp0x00;

public class Node {

    public String val;
    public Node left;
    public Node right;

    public Node(String val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Node(String val, Node left) {
        this(val, left, null);
    }

    public Node(String val) {
        this(val, null, null);
    }
}
