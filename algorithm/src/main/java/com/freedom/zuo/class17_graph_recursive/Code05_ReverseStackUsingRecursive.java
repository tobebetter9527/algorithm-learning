package com.freedom.zuo.class17_graph_recursive;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。 如何实现?
 *
 * @author tobebetter9527
 * @create 2022/07/14 20:17
 */
public class Code05_ReverseStackUsingRecursive {

  /**
   * 翻转栈
   *
   * @param stack
   */
  public static void reverseStack(Stack<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }
    // 获取最底的元素
    Integer value = getBottomValue(stack);
    // 翻转栈
    reverseStack(stack);
    // 之前获得最底的元素压入栈顶
    stack.push(value);
  }

  private static Integer getBottomValue(Stack<Integer> stack) {
    Integer result = stack.pop();
    if (stack.isEmpty()) {
      return result;
    } else {
      Integer last = getBottomValue(stack);
      stack.push(result);
      return last;
    }
  }


  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    reverseStack(stack);
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }


}
