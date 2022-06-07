package com.freedom.zuo.class03_linkedlist_queue_stack;

import java.util.EmptyStackException;

/**
 * 用数组实现不超过固定大小的栈
 *
 * @author tobebetter9527
 * @create 2022/06/07 19:18
 */
public class Code03_StackUseArray {

  private static class MyStack {

    Integer[] arr;
    int size;
    int elementCount;

    public MyStack(int size) {
      if (size < 1) {
        throw new IllegalArgumentException();
      }
      this.arr = new Integer[size];
      this.size = size;
    }



    public int push(int item) {
      if (elementCount + 1 > size) {
        throw new RuntimeException("the stack is full");
      }
      arr[elementCount++] = item;
      return item;
    }

    public int pop() {
      if (elementCount <= 0) {
        throw new EmptyStackException();
      }
      int item = arr[elementCount-1];
      arr[--elementCount] = null;
      return item;
    }
  }

  public static void main(String[] args) {
    MyStack myStack = new MyStack(6);
    myStack.push(1);
    myStack.push(2);
    myStack.push(3);
    myStack.pop();
    myStack.pop();
    myStack.pop();
    myStack.push(4);
    myStack.push(5);
    myStack.push(6);

    for (int i = 0 ,j = myStack.elementCount; i < j; i++) {
      System.out.println(myStack.pop());
    }

  }

}
