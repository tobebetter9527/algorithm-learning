package com.freedom.sword_offer;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * @author tobebetter9527
 * @create 2022/12/25 10:05
 */
public class Offer09 {

    class CQueue {

        Stack<Integer> in;
        Stack<Integer> out;

        public CQueue() {
            in = new Stack<>();
            out = new Stack<>();
        }

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            if (out.isEmpty()) {
                return -1;
            }
            return out.pop();
        }
    }
}
