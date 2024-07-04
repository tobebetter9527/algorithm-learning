package com.freedom.leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 738. Monotone Increasing Digits
 */
public class Problem738_MonotoneIncreasingDigits {

    public static int monotoneIncreasingDigits(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int start = chars.length;
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                chars[i]--;
                start = i + 1;
            }
        }

        for (int i = start; i < chars.length; i++) {
            chars[i] = '9';
        }

        return Integer.parseInt(String.valueOf(chars));
    }

    /**
     * 比较蠢的做法
     *
     * @param n
     * @return
     */
    public static int monotoneIncreasingDigits1(int n) {
        if (n < 10) {
            return n;
        }

        int num = n;

        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }

        int index = list.size() - 1;
        boolean flag = false;
        for (int i = index; i >= 1; i--) {
            if (list.get(i) < list.get(i - 1)) {
                index = i - 1;
            }
            if (list.get(i) > list.get(i - 1)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            return num;
        }

        int ans = 0;
        for (int i = list.size() - 1; i >= index + 1; i--) {
            ans += list.get(i) * Math.pow(10, i);
        }
        if (index == 0) {
            ans += list.get(index) * Math.pow(10, index);
        } else {
            ans += (list.get(index) - 1) * Math.pow(10, index);
        }
        for (int i = index - 1; i >= 0; i--) {
            ans += 9 * Math.pow(10, i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 11;
        System.out.println(monotoneIncreasingDigits(n));
    }

}
