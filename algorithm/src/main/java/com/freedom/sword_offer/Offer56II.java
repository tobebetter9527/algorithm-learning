package com.freedom.sword_offer;

/**
 * @author tobebetter9527
 * @create 2023/03/04 22:23
 */
public class Offer56II {

    public static int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bits[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (bits[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 6};
        System.out.println(singleNumber(nums));


    }

}
