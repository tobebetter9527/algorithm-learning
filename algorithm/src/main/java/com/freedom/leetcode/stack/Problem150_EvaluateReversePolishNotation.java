package com.freedom.leetcode.stack;

/**
 * 150. Evaluate Reverse Polish Notation
 *
 * @author tobebetter9527
 * @create 2022/11/03 21:33
 */
public class Problem150_EvaluateReversePolishNotation {

  /**
   * time comlexity is O(n), space complexity is O(n).
   * @param tokens
   * @return
   */
  public static int evalRPN(String[] tokens) {
    int n = tokens.length;
    int[] stack = new int[n];
    int index = -1;
    for (int i = 0; i < n; i++) {
      String token = tokens[i];
      if (token.equals("+")) {
        int a = stack[index--];
        int b = stack[index--];
        stack[++index] = a + b;
      } else if (token.equals("-")) {
        int a = stack[index--];
        int b = stack[index--];
        stack[++index] = b - a;
      } else if (token.equals("*")) {
        int a = stack[index--];
        int b = stack[index--];
        stack[++index] = b * a;
      } else if (token.equals("/")) {
        int a = stack[index--];
        int b = stack[index--];
        stack[++index] = b / a;
      } else {
        stack[++index] = Integer.valueOf(token);
      }
    }

    return stack[index];
  }

  public static int evalRPN2(String[] tokens) {
    int n = tokens.length;
    int[] stack = new int[n];
    int index = -1;
    for (int i = 0; i < n; i++) {
      String token = tokens[i];
      if (token.equals("+")) {
        index--;
        stack[index] += stack[index+1];
      } else if (token.equals("-")) {
        index--;
        stack[index] -= stack[index+1];
      } else if (token.equals("*")) {
        index--;
        stack[index] *= stack[index+1];
      } else if (token.equals("/")) {
        index--;
        stack[index] /= stack[index+1];
      } else {
        stack[++index] = Integer.valueOf(token);
      }
    }

    return stack[index];
  }

  public static void main(String[] args) {
    String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
    int i = evalRPN(tokens);
    System.out.println(i);
  }
}
