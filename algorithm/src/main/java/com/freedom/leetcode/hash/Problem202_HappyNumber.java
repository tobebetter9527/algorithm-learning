package com.freedom.leetcode.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. Happy Number
 *
 * @author tobebetter9527
 * @create 2022/10/19 23:21
 */
public class Problem202_HappyNumber {

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getSum(n);
        }

        return n == 1;
    }

    private static int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            int m = n % 10;
            sum += m * m;
            n = n / 10;
        }
        return sum;
    }


    public static boolean isHappy2(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return fast == 1;
    }

    private static int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            int m = n % 10;
            sum += m * m;
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

}
