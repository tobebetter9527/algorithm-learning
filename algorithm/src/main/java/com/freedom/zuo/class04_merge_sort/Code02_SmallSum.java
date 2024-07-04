package com.freedom.zuo.class04_merge_sort;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 *
 * @author tobebetter9527
 * @create 2022/06/11 16:54
 */
public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return process(arr, left, mid) + process(arr, mid + 1, right) + merge(arr, left, mid, right);
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int index1 = left;
        int index2 = mid + 1;
        int ans = 0;
        while (index1 <= mid && index2 <= right) {
            ans += arr[index1] < arr[index2] ? (right - index2 + 1) * arr[index1] : 0;
            help[i++] = arr[index1] < arr[index2] ? arr[index1++] : arr[index2++];
        }

        while (index1 <= mid) {
            help[i++] = arr[index1++];
        }
        while (index2 <= right) {
            help[i++] = arr[index2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }

        return ans;
    }

    public static int testsmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            for (int j = 0; j < i; j++) {
                ans += arr[j] < num ? arr[j] : 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int rang = 100;
        int maxSize = 100;
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(rang, maxSize);
            int[] copyArr = copy(arr);
            int sum = smallSum(arr);
            int testSum = testsmallSum(copyArr);
            if (sum != testSum) {
                System.out.println("有错误！");
                print(arr);
                print(copyArr);
            }
        }
        System.out.println("done!");
    }

    private static void print(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("----");
    }

    private static int[] copy(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }

        return copyArr;
    }

    private static int[] generateRandomArray(int rang, int maxSize) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
        }
        return arr;
    }


}
