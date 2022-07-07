package com.freedom.zuo.class14_greedy_union_find;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 启动资金做项目问题
 * <p>
 * leetcode 502
 *
 * @author tobebetter9527
 * @create 2022/07/07 21:03
 */
public class Code04_IPO {


  /**
   * @param k       只能串行的最多做k个项目
   * @param w       初始的资金
   * @param profits 表示i号项目在扣除花费之后还能挣到的钱(利润)
   * @param capital costs[i]表示i号项目的花费
   * @return
   */
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    // 构建花费的小根堆和利润的大根堆
    PriorityQueue<Project> minCostHeap = new PriorityQueue<>(new MinCostComparator());
    PriorityQueue<Project> maxProfitHeap = new PriorityQueue<>(new MaxProfitComparator());

    // 先按照花费建立小根堆
    for (int i = 0; i < profits.length; i++) {
      minCostHeap.add(new Project(capital[i], profits[i]));
    }

    // 只能做k个项目
    for (int i = 0; i < k; i++) {
      // 将目前能做的项目放入大根堆
      while (!minCostHeap.isEmpty() && minCostHeap.peek().cost <= w) {
        maxProfitHeap.add(minCostHeap.poll());
      }

      // 如果大根堆没项目，代表无能力做
      if (maxProfitHeap.isEmpty()) {
        return w;
      }

      w += maxProfitHeap.poll().profit;
    }

    return w;
  }

  static class MinCostComparator implements Comparator<Project> {

    @Override
    public int compare(Project o1, Project o2) {
      return o1.cost - o2.cost;
    }
  }

  static class MaxProfitComparator implements Comparator<Project> {

    @Override
    public int compare(Project o1, Project o2) {
      return o2.profit - o1.profit;
    }
  }

  static class Project {

    int cost;
    int profit;

    public Project(int cost, int profit) {
      this.cost = cost;
      this.profit = profit;
    }
  }

}
