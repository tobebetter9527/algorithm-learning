package com.freedom.leetcode.array;

/**
 * 66. Plus One
 */
public class Problem66_PlusOne {

    /**
     * time complexity is O(n), space complexity is O(1)
     *
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        int digit;
        int res = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (res == 1) {
                digit = digits[i] + 1;
                digits[i] = digit % 10;
                res = digit / 10;
            }
        }

        if (res == 1) {
            int[] ans = new int[n + 1];
            ans[0] = res;
            return ans;
        }

        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9};
        int[] ints = plusOne(digits);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
