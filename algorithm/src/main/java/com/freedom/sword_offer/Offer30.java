package com.freedom.sword_offer;

import java.util.Stack;

/**
 * @author tobebetter9527
 * @create 2023/02/27 20:20
 */
public class Offer30 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.min();
        System.out.println(min);
        minStack.pop();
        int top = minStack.top();
        System.out.println(top);
        int min1 = minStack.min();
        System.out.println(min1);
    }

    static class MinStack {

        Stack<Integer> stack;
        Stack<Integer> minStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                if (stack.pop().equals(minStack.peek())) {
                    minStack.pop();
                }
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
}
