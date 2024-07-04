package com.freedom.sword_offer;

import java.util.Arrays;

public class Offer11 {

    /**
     * time complexity is O(n), space complexity is O(1)
     *
     * @param numbers
     * @return
     */
    public static int minArray(int[] numbers) {
        if (numbers.length < 2) {
            return numbers[0];
        }
        int idx = 0, lenLessOne = numbers.length - 1;
        while (idx < lenLessOne && numbers[idx] <= numbers[idx + 1]) {
            idx++;
        }
        return idx == lenLessOne ? numbers[0] : numbers[idx + 1];
    }

    public static void main(String[] args) {
        int testTimes = 10000000, maxValue = 20, maxLen = 60;
        for (int i = 0; i < testTimes; i++) {
            int[] nums = generateArr(maxLen, maxValue);
            Arrays.sort(nums);
            int min1 = nums[0];

            int length = nums.length;
            int rotateTime = (int) (Math.random() * length) + 1;
            int[] numbers = new int[length];
            for (int j = rotateTime; j < length; j++) {
                numbers[j - rotateTime] = nums[j];
            }
            for (int j = 0; j < rotateTime; j++) {
                numbers[length - rotateTime + j] = nums[j];
            }

            int min2 = minArray(numbers);
            if (min1 != min2) {
                System.out.println(String.format("min1: %s, min2: %s", min1, min2));
                printArray(nums);
                printArray(numbers);
            }
        }
        System.out.println("done");
    }

    private static void printArray(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number + ",");
        }
        System.out.println("-------");
    }

    private static int[] generateArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = (int) (Math.random() * maxValue);
        }
        return nums;
    }
}
