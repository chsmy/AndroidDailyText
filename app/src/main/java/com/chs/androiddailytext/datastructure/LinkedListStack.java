package com.chs.androiddailytext.datastructure;

/**
 * 作者：chs
 * 时间：2019-01-08 09:53
 * 描述：
 */
public class LinkedListStack {
    private Node top = null;

    public void push(int value) {
        Node newNode = new Node(value, null);
        // 判断是否栈空
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * -1表示栈中没有数据。
     */
    public int pop() {
        if (top == null) return -1;
        int value = top.data;
        top = top.next;
        return value;
    }


    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}
