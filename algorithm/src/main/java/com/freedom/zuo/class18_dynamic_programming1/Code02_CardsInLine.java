package com.freedom.zuo.class18_dynamic_programming1;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * <p>
 * 玩家A和玩家B依次拿走每张纸牌
 * <p>
 * 规定玩家A先拿，玩家B后拿
 * <p>
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * <p>
 * 玩家A和玩家B都绝顶聪明
 * <p>
 * 请返回最后获胜者的分数。
 *
 * @author tobebetter9527
 * @create 2022/07/15 22:18
 */
public class Code02_CardsInLine {

  /**
   * 暴力递归
   *
   * @param arr 纸牌
   */
  public static int win1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int first = firstHand(arr, 0, arr.length - 1);
    int back = backHand(arr, 0, arr.length - 1);

    // 后手不一定比先手差，比如1, 200, 2 .
    return Math.max(first, back);
  }

  /**
   * 玩家A先手取牌
   *
   * @param arr   纸牌
   * @param left  左索引
   * @param right 右索引
   * @return 作为先手能取到最大的值
   */
  private static int firstHand(int[] arr, int left, int right) {
    // 只有一张牌，A取走就是了
    if (left == right) {
      return arr[left];
    }

    // A取走left的牌，接下来作为后手取 left+1 和 right 之间的牌
    int p1 = arr[left] + backHand(arr, left + 1, right);

    // A取走right的牌，接下来作为后手取 left 和 right-1 之间的牌
    int p2 = arr[right] + backHand(arr, left, right - 1);

    // 自己决定，取最大值
    return Math.max(p1, p2);
  }

  /**
   * 玩家A作为后手取牌
   *
   * @param arr   纸牌
   * @param left  左索引
   * @param right 右索引
   * @return 作为后手能取到最大的值
   */
  private static int backHand(int[] arr, int left, int right) {
    // 作为后手只能返回0，因为被先手取走了
    if (left == right) {
      return 0;
    }
    // left被玩家B取走，玩家A作为从剩下的left+1 和 right之间的牌作为先手取值
    int p1 = firstHand(arr, left + 1, right);

    // right被玩家B取走，玩家A作为从剩下的left 和 right-1 之间的牌作为先手取值
    int p2 = firstHand(arr, left, right - 1);

    // 对手只会让你取到比较小的值
    return Math.min(p1, p2);
  }

  // --------------------------------------------------------------------- //

  /**
   * 缓存法
   *
   * @param arr
   * @return
   */
  public static int win2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int n = arr.length;
    int[][] fmap = new int[n][n];
    int[][] gmap = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        fmap[i][j] = -1;
        gmap[i][j] = -1;
      }
    }

    int first = firstHand2(arr, 0, n - 1, fmap, gmap);
    int back = backHand2(arr, 0, n - 1, fmap, gmap);

    // 后手不一定比先手差，比如1, 200, 2 .
    return Math.max(first, back);
  }

  private static int firstHand2(int[] arr, int left, int right, int[][] fmap, int[][] gmap) {
    if (fmap[left][right] != -1) {
      return fmap[left][right];
    }

    int ans;

    // 只有一张牌，A取走就是了
    if (left == right) {
      ans = arr[left];
    } else {
      // A取走left的牌，接下来作为后手取 left+1 和 right 之间的牌
      int p1 = arr[left] + backHand2(arr, left + 1, right, fmap, gmap);

      // A取走right的牌，接下来作为后手取 left 和 right-1 之间的牌
      int p2 = arr[right] + backHand2(arr, left, right - 1, fmap, gmap);

      ans = Math.max(p1, p2);
    }

    fmap[left][right] = ans;

    // 自己决定，取最大值
    return ans;
  }

  private static int backHand2(int[] arr, int left, int right, int[][] fmap, int[][] gmap) {
    if (gmap[left][right] != -1) {
      return gmap[left][right];
    }

    int ans;

    // 作为后手只能返回0，因为被先手取走了
    if (left == right) {
      ans = 0;
    } else {
      // left被玩家B取走，玩家A作为从剩下的left+1 和 right之间的牌作为先手取值
      int p1 = firstHand2(arr, left + 1, right, fmap, gmap);

      // right被玩家B取走，玩家A作为从剩下的left 和 right-1 之间的牌作为先手取值
      int p2 = firstHand2(arr, left, right - 1, fmap, gmap);

      ans = Math.min(p1, p2);
    }

    gmap[left][right] = ans;
    // 对手只会让你取到比较小的值
    return ans;
  }

  // --------------------------------------------------------------------- //

  /**
   * 累加法
   *
   * @param arr
   * @return
   */
  public static int win3(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int n = arr.length;
    int[][] fmap = new int[n][n];
    int[][] gmap = new int[n][n];

    // 对角线赋值
    for (int i = 0; i < n; i++) {
      fmap[i][i] = arr[i];
    }

    // 从左到右，从上到下，先填fmap斜线，再填gmap斜线，再填fmap斜线，再填gmap斜线
    for (int i = 1; i < n; i++) {
      // 就是left
      int row = 0;
      // 就是right
      int col = i;
      while (col < n) {
        int p1 = arr[row] + gmap[row + 1][col];
        int p2 = arr[col] + gmap[row][col - 1];
        fmap[row][col] = Math.max(p1, p2);

        int p3 = fmap[row + 1][col];
        int p4 = fmap[row][col - 1];
        gmap[row][col] = Math.min(p3, p4);

        row++;
        col++;
      }
    }

    return Math.max(fmap[0][n - 1], gmap[0][n - 1]);
  }

  public static void main(String[] args) {
    int[] arr = {5, 7, 4, 1};
    System.out.println(win1(arr));
    System.out.println(win2(arr));
    System.out.println(win3(arr));

  }
}
