package com.freedom.zuo.class38;

/**
 * 小虎去买苹果，商店只提供两种类型的塑料袋，每种类型都有任意数量。
 * <p>
 * 1）能装下6个苹果的袋子
 * <p>
 * 2）能装下8个苹果的袋子
 * <p>
 * 小虎可以自由使用两种袋子来装苹果，但是小虎有强迫症，他要求自己使用的袋子数量必须最少，且使用的每个袋子必须装满。
 * <p>
 * 给定一个正整数N，返回至少使用多少袋子。如果N无法让使用的每个袋子必须装满，返回-1
 *
 * @author tobebetter9527
 * @create 2022/08/15 21:01
 */
public class Code01_AppleMinBags {

  public static int minBags(int n) {
    if (n < 0) {
      return -1;
    }

    int bag8 = n / 8;
    int restN = n - bag8 * 8;
    while (bag8 >= 0) {
      if (restN % 6 == 0) {
        return restN / 6 + bag8;
      } else {
        bag8--;
        restN += 8;
      }
    }

    return -1;
  }

  /**
   * 数学推导： 8y + 6x = n, y + x = min, 两式合并：min = (n + 2x)/8, x的限制值[0,n/6]
   * @param n
   * @return
   */
  public static int minBags2(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0) {
      return 0;
    }

    int x = n / 6;
    for (int i = 0; i <= x; i++) {
      if ((n + 2 * i) % 8 == 0) {
        return (n + 2 * i) / 8;
      }
    }

    return -1;
  }


  public static int minBagAwesome(int apple) {
    if ((apple & 1) != 0) { // 如果是奇数，返回-1
      return -1;
    }
    if (apple < 18) {
      return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
          : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
    }
    return (apple - 18) / 8 + 3;
  }


  public static void main(String[] args) {
    int n = 200;
    for (int i = 0; i < n; i++) {
      if (minBags(i) != minBags2(i)) {
        System.out.println("Wrong !");
      }

     // System.out.println("apple: " + i + ", bags: " + minBags2(i));
    }
  }
}