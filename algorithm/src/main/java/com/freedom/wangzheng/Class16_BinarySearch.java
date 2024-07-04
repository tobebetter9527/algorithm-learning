package com.freedom.wangzheng;

/**
 * 二分查询变形问题
 *
 * @author tobebetter9527
 * @create 2022/06/05 10:44
 */
public class Class16_BinarySearch {

    /**
     * 查找第一个值等于给定值的元素l
     *
     * @param arr
     * @param value
     */
    public static int binarySearch1(int[] arr, int value) {
        int high = arr.length - 1;
        int low = 0;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (arr[mid - 1] != value || mid == 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     *
     * @param arr
     * @param value
     */
    public static int binarySearch2(int[] arr, int value) {
        int high = arr.length - 1;
        int low = 0;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (arr[mid + 1] != value || mid == arr.length - 1) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param arr
     * @param value
     */
    public static int binarySearch3(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if (arr[mid - 1] < value || mid == 0) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param arr
     * @param value
     */
    public static int binarySearch4(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] <= value) {
                if (arr[mid + 1] > value || mid == arr.length - 1) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 10};
        System.out.println(binarySearch4(arr, 8));
    }

}
