package com.freedom.zuo.class29_bfprt_reservoir;

/**
 * reservoir algorithm
 *
 * @author tobebetter9527
 * @create 2022/08/05 20:00
 */
public class Code03_ReservoirSampling {

  /**
   * 从数组中等概率抽中k个数据
   *
   * @param arr
   * @param k
   * @return
   */
  public static int[] selectKItems(int[] arr, int k) {
    int[] res = new int[k];

    int n = arr.length;
    for (int i = 0; i < k; i++) {
      res[i] = arr[i];
    }

    for (int i = k; i < n; i++) {
      int j = random(i + 1);
      // 相等于k/j的概率
      if (j < k) {
        // 相等于1/k概率
        int index = random(k);
        res[index] = arr[i];
      }
      // 等价写法
//      if (j < k) {
//        res[j] = arr[i];
//      }
    }

    return res;
  }

  private static int random(int num) {
    return (int) (Math.random() * num);
  }

  // -------------------------------------- //

  public static void main(String[] args) {
    int testTimes = 1000000;
    int ballNum = 20;
    int[] count = new int[ballNum];
    int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
    int k = 5;
    for (int i = 0; i < testTimes; i++) {
      int[] items = selectKItems(arr, k);
      for (int item : items) {
        count[item]++;
      }
    }

    for (int i : count) {
      System.out.println(i);
    }
  }


}
