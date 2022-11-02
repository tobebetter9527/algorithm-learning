package com.freedom.leetcode.stack;

import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
 * functions of a normal queue (push, peek, pop, and empty).
 * <p>
 * Implement the MyQueue class:
 * <p>
 * void push(int x) Pushes element x to the back of the queue. int pop() Removes the element from the front of the queue
 * and returns it. int peek() Returns the element at the front of the queue. boolean empty() Returns true if the queue
 * is empty, false otherwise. Notes:
 * <p>
 * You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty
 * operations are valid. Depending on your language, the stack may not be supported natively. You may simulate a stack
 * using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 *
 * @author tobebetter9527
 * @create 2022/06/11 8:55
 */
public class Problem232_implementQueueUsingStacks {

  /**
   * 复杂度分析
   * <p>
   * 时间复杂度：\texttt{push}push 和 \texttt{empty}empty 为 O(1)O(1)，\texttt{pop}pop 和 \texttt{peek}peek 为均摊
   * O(1)O(1)。对于每个元素，至多入栈和出栈各两次，故均摊复杂度为 O(1)O(1)。
   * <p>
   * 空间复杂度：O(n)O(n)。其中 nn 是操作总数。对于有 nn 次 \texttt{push}push 操作的情况，队列中会有 nn 个元素，故空间复杂度为 O(n)O(n)。
   */
  private static class MyQueue {

    Stack<Integer> input;
    Stack<Integer> output;

    public MyQueue() {
      input = new Stack<>();
      output = new Stack<>();
    }

    public void push(int x) {
      input.push(x);
    }

    public int pop() {
      if (output.isEmpty()) {
        while (!input.isEmpty()) {
          output.push(input.pop());
        }
      }
      return output.pop();
    }

    public int peek() {
      if (output.isEmpty()) {
        while (!input.isEmpty()) {
          output.push(input.pop());
        }
      }
      return output.peek();
    }

    public boolean empty() {
      return input.isEmpty() && output.isEmpty();
    }
  }

}
