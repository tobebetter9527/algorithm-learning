package com.freedom.zuo.class14_greedy_union_find;

/**
 * 点灯问题
 *
 * @author tobebetter9527
 * @create 2022/07/07 22:08
 */
public class Code01_Light {

  public static int minLight2(String road) {
    if (road == null || road.length() == 0) {
      return 0;
    }

    int light = 0;
    int i = 0;
    while (i < road.length()) {
      // 如果遇到X，不管
      if (road.charAt(i) == 'X') {
        i++;
      } else {
        light++;
        // 此时i位置是., 如果相等，说明已经到尾巴了
        if (i + 1 == road.length()) {
          break;
        }
        // 如果i + 1位置是X，灯数量加1，i来到i + 2
        if (road.charAt(i + 1) == 'X') {
          i = i + 2;
        } else {
          // 此时i + 1 位置是.， 不管i + 2 位置是.还是X，灯数量加1，i来到i+3
          i = i + 3;
        }
      }
    }

    return light;
  }

  /**
   * 两个X之间的点数量除以3，向上取整
   *
   * @param road
   * @return
   */
  public static int minLight3(String road) {
    if (road == null || road.length() == 0) {
      return 0;
    }

    int light = 0;
    int curCount = 0;
    for (char c : road.toCharArray()) {
      if (c == 'X') {
        light += (curCount + 2) / 3;
        curCount = 0;
      } else {
        curCount++;
      }
    }
    light += (curCount + 2) / 3;
    return light;
  }


  public static void main(String[] args) {
    int maxLength = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      String road = generateRoad(maxLength);
      if (minLight2(road) != minLight3(road)) {
        System.out.println("wrong!");
      }
    }
    System.out.println("done!");
  }

  private static String generateRoad(int maxLength) {
    int length = (int) (Math.random() * maxLength);
    char[] chars = new char[length];
    for (int i = 0; i < length; i++) {
      chars[i] = Math.random() < 0.65D ? '.' : 'X';
    }
    return String.valueOf(chars);
  }

}
