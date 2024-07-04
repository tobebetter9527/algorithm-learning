package com.freedom.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object. void push(int val) pushes the element val onto the stack. void pop() removes
 * the element on the top of the stack. int top() gets the top element of the stack. int getMin() retrieves the minimum
 * element in the stack.
 *
 * @author tobebetter9527
 * @create 2022/06/10 23:14
 */
public class Problem155_MinStack {

    private static class MinStack {

        Deque<Integer> dataStack;
        Deque<Integer> minStack;

        public MinStack() {
            dataStack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(minStack.peek(), val));
            }
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
