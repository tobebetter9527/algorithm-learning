package com.freedom.zuo.class05_merge_sort_quick_sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_QuickSortUnrecursiveVersion {

  /**
   * 利用栈实现
   *
   * @param arr
   */
  public static void quickSortUseStack(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    Stack<int[]> stack = new Stack<>();

    // 随机选一个做pivot
    swap(arr, (int) (Math.random() * n), n - 1);
    int[] indexs = dutchNationalFlag(arr, 0, n - 1);
    stack.push(new int[]{indexs[1] + 1, n - 1});
    stack.push(new int[]{0, indexs[0] - 1});

    while (!stack.isEmpty()) {
      int[] pop = stack.pop();
      if (pop[0] < pop[1]) {
        swap(arr, pop[0] + (int) (Math.random() * (pop[1] - pop[0] + 1)), pop[1]);
        int[] subIndex1 = dutchNationalFlag(arr, pop[0], pop[1]);
        stack.push(new int[]{subIndex1[1] + 1, pop[1]});
        stack.push(new int[]{pop[0], subIndex1[0] - 1});
      }
    }
  }

  /**
   * 利用队列实现
   *
   * @param arr
   */
  public static void quickSortUseQueue(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int n = arr.length;
    Queue<int[]> queue = new LinkedList<>();

    swap(arr, (int) (Math.random() * n), n - 1);
    int[] indexs = dutchNationalFlag(arr, 0, n - 1);
    queue.offer(new int[]{0, indexs[0] - 1});
    queue.offer(new int[]{indexs[1] + 1, n - 1});

    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      if (poll[0] < poll[1]) {
        swap(arr, poll[0] + (int) (Math.random() * (poll[1] - poll[0] + 1)), poll[1]);
        int[] subIndexs = dutchNationalFlag(arr, poll[0], poll[1]);
        queue.offer(new int[]{poll[0], subIndexs[0] - 1});
        queue.offer(new int[]{subIndexs[1] + 1, poll[1]});
      }
    }
  }


  private static int[] dutchNationalFlag(int[] arr, int left, int right) {
    if (left > right) {
      return new int[]{-1, -1};
    }
    if (left == right) {
      return new int[]{left, right};
    }

    int pivot = right;
    int more = right;
    int less = left - 1;
    int index = left;
    while (index < more) {
      if (arr[index] == arr[pivot]) {
        index++;
      } else if (arr[index] < arr[pivot]) {
        swap(arr, ++less, index++);
      } else {
        swap(arr, index, --more);
      }
    }
    swap(arr, right, more);
    return new int[]{less + 1, more};
  }

  private static void swap(int[] arr, int index, int i) {
    int temp = arr[index];
    arr[index] = arr[i];
    arr[i] = temp;
  }

  public static void main(String[] args) {
    int rang = 100;
    int maxSize = 100;
    int testTime = 1000000;
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(rang, maxSize);
      int[] copyArr = copy(arr);
      quickSortUseQueue(arr);
      Arrays.sort(copyArr);
      if (!isEqual(arr, copyArr)) {
        System.out.println("wrong");
      }
    }
    System.out.println("done!");

  }


  private static int[] generateRandomArray(int rang, int maxSize) {
    int size = (int) (Math.random() * (maxSize + 1));
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
    }
    return arr;
  }

  private static int[] copy(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] copyArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      copyArr[i] = arr[i];
    }

    return copyArr;
  }

  private static boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }
}
