package com.freedom.sword_offer;

public class Offer43 {
    /**
     * 思路题解：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solutions/757536/dong-hua-mo-ni-wo-tai-xi-huan-zhe-ge-ti-vxzwc/?envType=featured-list&envId=xb9nqhhg
     * time complexity is O(logn),space complexity is O(1)
     *
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        // 高位的值，低位的值，当前位的值，用来从左往右移动，总数
        int high = n, low, cur, digit = 1, count = 0;
        while (high != 0) {
            cur = high % 10;
            high /= 10;
            low = n % digit;
            if (cur == 0) {
                count += high * digit;
            } else if (cur == 1) {
                count += high * digit + low + 1;
            } else {
                count += high * digit + digit;
            }
            digit *= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 1004;
        System.out.println(countDigitOne(n));
    }
}
