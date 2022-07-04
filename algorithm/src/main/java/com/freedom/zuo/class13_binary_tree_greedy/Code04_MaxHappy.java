package com.freedom.zuo.class13_binary_tree_greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 *
 * @author tobebetter9527
 * @create 2022/07/04 22:05
 */
public class Code04_MaxHappy {


  /**
   * 与maxHappy2本质没差别
   *
   * @param boss
   * @return
   */
  public static int maxHappy3(Employee boss) {
    if (boss == null) {
      return 0;
    }
    return process3(boss, false);
  }

  /**
   * @param head
   * @param up   表示head的上级来不来，true来，false不来，这里跟maxHappy1方法的不同，特别注意
   * @return
   */
  private static int process3(Employee head, boolean up) {
    if (up) {
      int ansNo = 0;
      for (Employee next : head.nexts) {
        // 由于head的上级来了，head只能自己不来，所以这里是false
        ansNo += process3(next, false);
      }
      return ansNo;
    } else {
      // 由于head的上级没来，head可以来，也可以不来
      int ansYes = head.happy;
      int ansNo = 0;
      for (Employee next : head.nexts) {
        ansYes += process3(next, true);
        ansNo += process3(next, false);
      }
      return Math.max(ansNo, ansYes);
    }
  }


  /**
   * 与maxHappy2本质没差别
   *
   * @param boss
   * @return
   */
  public static int maxHappy1(Employee boss) {
    if (boss == null) {
      return 0;
    }
    return Math.max(process1(boss, false), process1(boss, true));
  }

  /**
   * @param cur 节点
   * @param up  true当前节点来，false当前不来
   * @return
   */
  private static int process1(Employee cur, boolean up) {
    // 当前节点cur的领导来了
    if (up) {
      int ans = cur.happy;
      List<Employee> nexts = cur.nexts;
      for (Employee next : nexts) {
        // 领导来了，下级只能不来
        ans += process1(next, false);
      }
      return ans;
    } else {
      // 当前节点不来
      int ans = 0;
      List<Employee> nexts = cur.nexts;
      for (Employee next : nexts) {
        int ansYes = process1(next, true);
        int ansNo = process1(next, false);
        ans += Math.max(ansNo, ansYes);
      }
      return ans;
    }
  }


  public static int maxHappy2(Employee head) {
    Info info = process2(head);
    return Math.max(info.no, info.yes);
  }

  private static Info process2(Employee head) {
    if (head == null) {
      return new Info(0, 0);
    }
    // head来，yes的初始值不为0
    int yes = head.happy;
    // head不来
    int no = 0;

    List<Employee> nexts = head.nexts;
    for (Employee next : nexts) {
      Info info = process2(next);
      // head不来，那么next可以来，也可以不来
      no += Math.max(info.no, info.yes);

      // head来了，next就不能来
      yes += info.no;
    }

    return new Info(yes, no);
  }

  // for test
  public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
    if (Math.random() < 0.02) {
      return null;
    }
    Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
    genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
    return boss;
  }

  // for test
  public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
    if (level > maxLevel) {
      return;
    }
    int nextsSize = (int) (Math.random() * (maxNexts + 1));
    for (int i = 0; i < nextsSize; i++) {
      Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
      e.nexts.add(next);
      genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
    }
  }

  public static void main(String[] args) {
    int maxLevel = 4;
    int maxNexts = 7;
    int maxHappy = 100;
    int testTimes = 100000;
    for (int i = 0; i < testTimes; i++) {
      Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
      if (maxHappy1(boss) != maxHappy2(boss) && maxHappy1(boss) != maxHappy3(boss)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }

  static class Info {

    int yes;
    int no;

    public Info(int yes, int no) {
      this.yes = yes;
      this.no = no;
    }
  }


  static class Employee {

    public int happy;
    public List<Employee> nexts;

    public Employee(int h) {
      happy = h;
      nexts = new ArrayList<>();
    }

  }
}
