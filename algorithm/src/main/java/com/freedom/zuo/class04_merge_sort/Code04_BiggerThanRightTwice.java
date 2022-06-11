package com.freedom.zuo.class04_merge_sort;

/**
 * BiggerThanRightTwice
 *
 * @author tobebetter9527
 * @create 2022/06/11 19:32
 */
public class Code04_BiggerThanRightTwice {

  public static int biggerThanRightTwice(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }

    return process(arr, 0, arr.length - 1);
  }

  private static int process(int[] arr, int left, int right) {
    if (left == right) {
      return 0;
    }
    int mid = left + ((right - left) >> 1);
    return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
  }

  private static int merge(int[] arr, int left, int mid, int right) {
    int window = mid + 1;
    int ans = 0;
    for (int i = left; i <= mid; i++) {
      while (window <= right && (long) arr[i] > (long) (arr[window] * 2)) {
        window++;
      }
      ans += window - (mid + 1);
    }

    int[] help = new int[right - left + 1];
    int i = 0;
    int index1 = left;
    int index2 = mid + 1;
    while (index1 <= mid && index2 <= right) {
      help[i++] = arr[index1] < arr[index2] ? arr[index1++] : arr[index2++];
    }

    while (index1 <= mid) {
      help[i++] = arr[index1++];
    }
    while (index2 <= right) {
      help[i++] = arr[index2++];
    }

    for (int j = 0; j < help.length; j++) {
      arr[left + j] = help[j];
    }

    return ans;
  }

  public static int testBiggerThanRightTwice(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      int num = arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        if ((long) num > (long) (arr[j] * 2)) {
          ans++;
        }
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int rang = 100;
    int maxSize = 100;
    int testTiems = 100000000;
    for (int i = 0; i < testTiems; i++) {
      int[] arr = generateArray(rang, maxSize);
      int[] copyArr = copyArray(arr);

      int pairs = biggerThanRightTwice(arr);
      int copyPairs = testBiggerThanRightTwice(copyArr);
      if (pairs != copyPairs) {
        System.out.println("有错误！");
        print(arr);
        print(copyArr);
      }
    }
  }

  private static void print(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("----");
  }

  private static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] copyArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      copyArr[i] = arr[i];
    }
    return copyArr;
  }

  private static int[] generateArray(int rang, int maxSize) {
    int length = (int) (Math.random() * maxSize + 1);
    int[] arr = new int[length];
    for (int i = 0; i < length; i++) {
      arr[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
    }
    return arr;
  }


}
