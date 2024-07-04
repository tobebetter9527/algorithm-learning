package com.freedom.zuo.class01_sort_binary_search;

import java.util.Arrays;

/**
 * Insertion sort is a simple sorting algorithm that works similar to the way you sort playing cards in your hands. The
 * array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at
 * the correct position in the sorted part.
 * <p>
 * 时间复杂度分析： 最好最坏都是O(n^2)
 *
 * @author tobebetter9527
 * @create 2022/06/04 14:34
 */
public class Code3_InsertionSort {

    public static void insertionSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > temp) {
                    // 数据移动
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            // 数据交换
            arr[j + 1] = temp;
        }
    }


    /**
     * 此版本不是最优，原因在于多次swap
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] generateRandomArray(int maxSize, int range) {
        int length = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = generateRandomNum(range);

        }
        return arr;
    }

    private static int generateRandomNum(int range) {
        return (int) (Math.random() * range - Math.random() * range);
    }

    private static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return arr;
    }

    private static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }
        if (arr1 != null && arr2 == null) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static void print(int[] arr) {
        if (arr == null) {
            System.out.println("arr is null");
        }
        System.out.println("--------");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


    public static void main(String[] args) {
        int size = 20, range = 100, testTimes = 1000000;
        boolean flag = false;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(size, range);
            int[] copyArray = copyArray(arr);
            insertionSort2(arr);
            Arrays.sort(copyArray);

            if (!isEqual(arr, copyArray)) {
                flag = true;
                System.out.println("selection sort error");
                print(arr);
                print(copyArray);
            }
        }
        System.out.println(flag ? "bad" : "good");
    }

}
