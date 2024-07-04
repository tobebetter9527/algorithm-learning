package com.freedom.zuo.class26_fibonacci;

/**
 * https://leetcode.cn/problems/sum-of-subarray-minimums/
 *
 * @author tobebetter9527
 * @create 2022/07/29 21:34
 */
public class Code01_SumOfSubarrayMinimums {

    /**
     * 暴力法
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins1(int[] arr) {
        int sum = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int minValue = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    minValue = Math.min(minValue, arr[k]);
                }
                sum += minValue;
            }
        }
        return sum;
    }

    // ----------------------------------------------------------- //

    /**
     * 不用单调栈
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins2(int[] arr) {
        long sum = 0;
        int length = arr.length;
        // leftLessEqualIndex[i] = value,i位置，value为左边最近<= arr[i]的值的索引
        int[] leftLessEqualIndex = leftLessEqualIndex(arr, length);
        // rightLessIndex[i] = value,i位置，value为右边最近< arr[i]的值的索引
        int[] rightLessIndex = rightLessIndex(arr, length);
        // 当前i位置为子数组的最小值
        for (int i = 0; i < length; i++) {
            long start = i - leftLessEqualIndex[i];
            long end = rightLessIndex[i] - i;
            sum += (start * end) * (long) arr[i];
            sum %= 1000000007;
        }

        return (int) sum;
    }

    private static int[] rightLessIndex(int[] arr, int length) {
        int[] rightLessIndex = new int[length];
        for (int i = 0; i < length; i++) {
            int ans = length;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[i]) {
                    ans = j;
                    break;
                }
            }
            rightLessIndex[i] = ans;
        }
        return rightLessIndex;
    }

    private static int[] leftLessEqualIndex(int[] arr, int length) {
        int[] leftLessEqualIndex = new int[length];
        for (int i = 0; i < length; i++) {
            int ans = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    ans = j;
                    break;
                }
            }
            leftLessEqualIndex[i] = ans;
        }
        return leftLessEqualIndex;
    }

    // ----------------------------------------------------------- //


    /**
     * 单调栈优化
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins3(int[] arr) {
        long sum = 0;
        int length = arr.length;
        int[] stack = new int[length];
        // leftLessEqualIndex[i] = value,i位置，value为左边最近<= arr[i]的值的索引
        int[] leftLessEqualIndex = nearLeftLessEqualIndex(arr, length, stack);
        // rightLessIndex[i] = value,i位置，value为右边最近< arr[i]的值的索引
        int[] rightLessIndex = nearRightLessIndex(arr, length, stack);
        // 当前i位置为子数组的最小值
        for (int i = 0; i < length; i++) {
            long start = i - leftLessEqualIndex[i];
            long end = rightLessIndex[i] - i;
            sum += (start * end) * (long) arr[i];
            sum %= 1000000007;
        }

        return (int) sum;
    }

    private static int[] nearRightLessIndex(int[] arr, int length, int[] stack) {
        int[] rightLessIndex = new int[length];
        // 栈大小
        int size = 0;

        for (int i = 0; i < length; i++) {
            while (size > 0 && arr[stack[size - 1]] > arr[i]) {
                int index = stack[--size];
                rightLessIndex[index] = i;
            }
            stack[size++] = i;
        }

        while (size > 0) {
            int index = stack[--size];
            rightLessIndex[index] = length;
        }

        return rightLessIndex;
    }

    private static int[] nearLeftLessEqualIndex(int[] arr, int length, int[] stack) {
        int[] leftLessEqualIndex = new int[length];
        // 栈大小
        int size = 0;

        for (int i = 0; i < length; i++) {
            // 如果两个值相等，后一个值保存前一个的索引
            while (size > 0 && arr[stack[size - 1]] > arr[i]) {
                int index = stack[--size];
                leftLessEqualIndex[index] = size == 0 ? -1 : stack[size - 1];
            }
            stack[size++] = i;
        }

        while (size > 0) {
            int index = stack[--size];
            leftLessEqualIndex[index] = size == 0 ? -1 : stack[size - 1];
        }

        return leftLessEqualIndex;
    }

    // ----------------------------------------------------------- //

    public static int[] randomArray(int len, int maxValue) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * maxValue) + 1;
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 15;
        int maxValue = 10;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = sumSubarrayMins1(arr);
            int ans2 = sumSubarrayMins2(arr);
            int ans3 = sumSubarrayMins3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }
}



