package com.freedom.sword_offer;

import java.util.LinkedList;
import java.util.List;

public class Offer57II {

    public static void main(String[] args) {
        int target = 9;
        int[][] continuousSequence = findContinuousSequence3(target);
        for (int[] ints : continuousSequence) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println(";");
        }
    }


    /**
     * 暴力枚举
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        List<int[]> ans = new LinkedList<>();
        // target为什么减一再除以2，因为答案至少是两个数，所以不能超过数的一半
        int sum = 0, limit = (target - 1) / 2;
        for (int i = 1; i <= limit; i++) {
            for (int j = i; ; j++) {
                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = 0; k < res.length; k++) {
                        res[k] = i + k;
                    }
                    ans.add(res);
                    sum = 0;
                    break;
                }
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 暴力枚举 + 数学优化（求平方根公式）
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence2(int target) {
        double y = 2.0;
        int limit = (target - 1) / 2;
        List<int[]> ans = new LinkedList<>();
        for (int x = 1; x <= limit; x++) {
            y = (-1 + Math.sqrt(1 + (4 * ((long) x * x - x + 2 * target)))) / 2;
            if (x < y && y == (int) y) {
                int[] res = new int[(int) y - x + 1];
                for (int i = 0; i < res.length; i++) {
                    res[i] = x + i;
                }
                ans.add(res);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 双指针法
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence3(int target) {
        int left = 1, right = 2, sum = left + right;
        List<int[]> ans = new LinkedList<>();
        while (left < right) {
            if (sum == target) {
                int[] res = new int[right - left + 1];
                for (int i = 0; i < res.length; i++) {
                    res[i] = left + i;
                }
                ans.add(res);
            }

            if (sum >= target) {
                sum -= left;
                left++;
            } else {
                right++;
                sum += right;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
