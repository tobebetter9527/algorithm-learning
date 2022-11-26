package com.freedom.leetcode.greedy;

/**
 * 860. Lemonade Change
 *
 * @author tobebetter9527
 * @create 2022/11/26 9:36
 */
public class Problem860_LemonadeChange {

  /**
   * time complexity is O(n), space complexity is O(1).
   *
   * @param bills
   * @return
   */
  public boolean lemonadeChange(int[] bills) {
    int five = 0;
    int ten = 0;
    int twenty = 0;
    for (int bill : bills) {
      if (bill == 5) {
        five++;
      }
      if (bill == 10) {
        if (five > 0) {
          five--;
          ten++;
        } else {
          return false;
        }
      }
      if (bill == 20) {
        if (ten > 0 && five > 0) {
          ten--;
          five--;
          twenty++;
        } else if (five > 2) {
          five -= 3;
          twenty++;
        } else {
          return false;
        }
      }
    }
    return true;
  }

}
