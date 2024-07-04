package com.freedom.sword_offer;

import java.util.Arrays;

/**
 * @author tobebetter9527
 * @create 2023/03/06 21:23
 */
public class Offer61 {

    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int joker = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                joker++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[4] - nums[joker] < 5;
    }
}
