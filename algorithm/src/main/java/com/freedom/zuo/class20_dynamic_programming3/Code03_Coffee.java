package com.freedom.zuo.class20_dynamic_programming3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目
 * <p>
 * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
 * <p>
 * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
 * <p>
 * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
 * <p>
 * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
 * <p>
 * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
 * <p>
 * 四个参数：arr, n, a, b
 * <p>
 * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
 *
 * @author tobebetter9527
 * @create 2022/07/17 18:31
 */
public class Code03_Coffee {

  /**
   * 暴力递归 + 贪心
   *
   * @param arr 下标i表示一个咖啡机， arr[i] 表示冲咖啡时间
   * @param n   n个人要喝咖啡
   * @param a   只有一台洗咖啡机，洗每个杯子时间a，只能串行洗
   * @param b   自然挥发干净的时间，可以并行
   * @return 所有人喝完咖啡到洗完咖啡，至少用多少时间
   */
  public static int minTime1(int[] arr, int n, int a, int b) {
    // 用小根堆，模拟每个咖啡机分配几个人冲咖啡
    PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new MyCoffeeMachineComparator());
    for (int i : arr) {
      heap.add(new CoffeeMachine(0, i));
    }

    // 算出每杯咖啡杯可以开始洗或挥发的时间
    CoffeeMachine machine;
    int[] drinks = new int[n];
    for (int i = 0; i < n; i++) {
      machine = heap.poll();
      machine.timePoint += machine.workTime;
      drinks[i] = machine.timePoint;
      heap.add(machine);
    }

    // 计算
    return bestTime(drinks, a, b, 0, 0);
  }

  /**
   * drinks[index....]都变干净，最早的结束时间（返回）
   *
   * @param drinks   drinks[i]咖啡杯开始洗或挥发的时间
   * @param a        只有一台洗咖啡机，洗每个杯子时间a，只能串行洗
   * @param b        自然挥发干净的时间，可以并行
   * @param index    当前index的杯子
   * @param freeTime 洗杯子的机器来到什么空间时间
   */
  private static int bestTime(int[] drinks, int a, int b, int index, int freeTime) {
    // 最后的杯子都完成了
    if (index == drinks.length) {
      return 0;
    }

    // index号杯子，机器洗
    int machineCleanTime = Math.max(drinks[index], freeTime) + a;
    int restCleanTime = bestTime(drinks, a, b, index + 1, machineCleanTime);
    // 最后一个杯子干净的时间决定了用时最少的时间，所以取max
    int p1 = Math.max(machineCleanTime, restCleanTime);

    // index号杯子，挥发干净
    int selfCleanTime = drinks[index] + b;
    int restCleanTime2 = bestTime(drinks, a, b, index + 1, freeTime);
    int p2 = Math.max(selfCleanTime, restCleanTime2);

    // 以上两种方式，那种更省时
    return Math.min(p1, p2);
  }

  // -------------------------------------------------------------------------//

  /**
   * 动态规划 + 贪心
   *
   * @param arr 下标i表示一个咖啡机， arr[i] 表示冲咖啡时间
   * @param n   n个人要喝咖啡
   * @param a   只有一台洗咖啡机，洗每个杯子时间a，只能串行洗
   * @param b   自然挥发干净的时间，可以并行
   * @return 所有人喝完咖啡到洗完咖啡，至少用多少时间
   */
  public static int minTime2(int[] arr, int n, int a, int b) {
    // 用小根堆，模拟每个咖啡机分配几个人冲咖啡
    PriorityQueue<CoffeeMachine> heap = new PriorityQueue<>(new MyCoffeeMachineComparator());
    for (int i : arr) {
      heap.add(new CoffeeMachine(0, i));
    }

    // 算出每杯咖啡杯可以开始洗或挥发的时间
    CoffeeMachine machine;
    int[] drinks = new int[n];
    for (int i = 0; i < n; i++) {
      machine = heap.poll();
      machine.timePoint += machine.workTime;
      drinks[i] = machine.timePoint;
      heap.add(machine);
    }

    // 计算
    return bestTime2(drinks, a, b);
  }

  /**
   * drinks[index....]都变干净，最早的结束时间（返回）
   *
   * @param drinks drinks[i]咖啡杯开始洗或挥发的时间
   * @param a      只有一台洗咖啡机，洗每个杯子时间a，只能串行洗
   * @param b      自然挥发干净的时间，可以并行
   */
  private static int bestTime2(int[] drinks, int a, int b) {
    int n = drinks.length;
    int maxFreeTime = 0;
    for (int i = 0; i < n; i++) {
      maxFreeTime = Math.max(maxFreeTime, drinks[i]) + a;
    }

    int[][] dp = new int[n + 1][maxFreeTime + 1];

    // 最底层的一行都是0
    // 从下到上，从左到右
    for (int index = n - 1; index >= 0; index--) {
      for (int freeTime = 0; freeTime <= maxFreeTime; freeTime++) {
        // 1.index号杯子，机器洗
        int machineCleanTime = Math.max(drinks[index], freeTime) + a;

        // 因为后面的也都不用填了,不然报越界错误
        if (machineCleanTime > maxFreeTime) {
          break;
        }

        int restCleanTime = dp[index + 1][machineCleanTime];
        // 最后一个杯子干净的时间决定了用时最少的时间，所以取max
        int p1 = Math.max(machineCleanTime, restCleanTime);

        // 2.index号杯子，挥发干净
        int selfCleanTime = drinks[index] + b;
        int restCleanTime2 = dp[index + 1][freeTime];
        int p2 = Math.max(selfCleanTime, restCleanTime2);

        // 以上两种方式，那种更省时
        dp[index][freeTime] = Math.min(p1, p2);
      }
    }

    return dp[0][0];
  }

  // ----------------------------------------//

  // 验证的方法
  // 彻底的暴力
  // 很慢但是绝对正确
  public static int right(int[] arr, int n, int a, int b) {
    int[] times = new int[arr.length];
    int[] drink = new int[n];
    return forceMake(arr, times, 0, drink, n, a, b);
  }

  // 每个人暴力尝试用每一个咖啡机给自己做咖啡
  public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
    if (kth == n) {
      int[] drinkSorted = Arrays.copyOf(drink, kth);
      Arrays.sort(drinkSorted);
      return forceWash(drinkSorted, a, b, 0, 0, 0);
    }
    int time = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      int work = arr[i];
      int pre = times[i];
      drink[kth] = pre + work;
      times[i] = pre + work;
      time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
      drink[kth] = 0;
      times[i] = pre;
    }
    return time;
  }

  public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
    if (index == drinks.length) {
      return time;
    }
    // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
    int wash = Math.max(drinks[index], washLine) + a;
    int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

    // 选择二：当前index号咖啡杯，选择自然挥发
    int dry = drinks[index] + b;
    int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
    return Math.min(ans1, ans2);
  }

  // --------------------------------------------//


  public static void main(String[] args) {
    int len = 10;
    int max = 10;
    int testTime = 10;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int[] arr = randomArray(len, max);
      int n = (int) (Math.random() * 7) + 1;
      int a = (int) (Math.random() * 7) + 1;
      int b = (int) (Math.random() * 10) + 1;
      int ans1 = right(arr, n, a, b);
      int ans2 = minTime1(arr, n, a, b);
      int ans3 = minTime2(arr, n, a, b);
      if (ans1 != ans2 || ans2 != ans3) {
        printArray(arr);
        System.out.println("n : " + n);
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println(ans1 + " , " + ans2 + " , " + ans3);
        System.out.println("===============");
        break;
      }
    }
    System.out.println("测试结束");

  }


  static class CoffeeMachine {

    // 来到什么时间点
    int timePoint;

    // 冲咖啡时间
    int workTime;

    public CoffeeMachine(int timePoint, int workTime) {
      this.timePoint = timePoint;
      this.workTime = workTime;
    }
  }

  static class MyCoffeeMachineComparator implements Comparator<CoffeeMachine> {

    @Override
    public int compare(CoffeeMachine o1, CoffeeMachine o2) {
      return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
    }
  }


  // for test
  public static int[] randomArray(int len, int max) {
    int[] arr = new int[len];
    for (int i = 0; i < len; i++) {
      arr[i] = (int) (Math.random() * max) + 1;
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    System.out.print("arr : ");
    for (int j = 0; j < arr.length; j++) {
      System.out.print(arr[j] + ", ");
    }
    System.out.println();
  }

}
