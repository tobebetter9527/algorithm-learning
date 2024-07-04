package com.freedom.zuo.class40;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * <p>
 * 给定一个整数值K
 * <p>
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * <p>
 * 返回其长度
 *
 * @author tobebetter9527
 * @create 2022/08/21 17:38
 */
public class Code03_LongestLessSumSubArrayLength {


    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // i位置向右扩的最小累加和
        int[] minSums = new int[arr.length];
        // i位置向右扩的最小累加和的截止位置
        int[] minSumEnd = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] < 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSums[i] = arr[i];
                minSumEnd[i] = i;
            }
        }

        int end = 0;
        int sum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSums[end] <= k) {
                sum += minSums[end];
                end = minSumEnd[end] + 1;
            }
            maxLen = Math.max(maxLen, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }

        return maxLen;
    }

    // ----------------------------------------  //

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
