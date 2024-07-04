package com.freedom.zuo.class29_bfprt_reservoir;

import java.util.PriorityQueue;

/**
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数
 * <p>
 * 不同时间复杂度三个方法：
 * <p>
 * 1）O(NlogN)
 * <p>
 * 2）O(N + KlogN)
 * <p>
 * 3）O(n + k*logk)
 *
 * @author tobebetter9527
 * @create 2022/08/05 10:04
 */
public class Code01_FindMaxKth {

    /**
     * 利用小根堆,时间复杂度O(N*logK)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);

        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > heap.peek()) {
                heap.poll();
                heap.add(arr[i]);
            }
        }

        return heap.peek();
    }

    // --------------------------------------------------------- //

    /**
     * 改写快排方法，时间复杂度O(n)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int minKth2(int[] arr, int k) {
        return process1(arr, k - 1, 0, arr.length - 1);
    }

    private static int process1(int[] arr, int indexK, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int p = partition(arr, left, right);
        if (p == indexK) {
            return arr[p];
        } else if (p < indexK) {
            return process1(arr, indexK, p + 1, right);
        } else {
            return process1(arr, indexK, left, p - 1);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left + 1));
        swap(arr, left, pivot);
        pivot = left;

        int more = right + 1;
        int index = right;
        while (index > left) {
            if (arr[index] < arr[pivot]) {
                swap(arr, index, --more);
            }
            index--;
        }
        swap(arr, pivot, --more);
        return more;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // --------------------------------------------------------- //

    /**
     * 利用bfprt算法，时间复杂度O(N)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int minKth3(int[] arr, int k) {
        return bfprt(arr, k - 1, 0, arr.length - 1);
    }

    private static int bfprt(int[] arr, int indexK, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int pivot = medianOfMedians(arr, left, right);
        int[] range = partition3(arr, pivot, left, right);
        if (indexK >= range[0] && indexK <= range[1]) {
            return arr[indexK];
        } else if (indexK < range[0]) {
            return bfprt(arr, indexK, left, range[0] - 1);
        } else {
            return bfprt(arr, indexK, range[1] + 1, right);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right) {
        int size = right - left + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int length = size / 5 + offset;

        int[] mArr = new int[length];
        for (int i = 0; i < length; i++) {
            int firstI = left + i * 5;
            mArr[i] = getMedian(arr, firstI, Math.min(right, firstI + 4));
        }

        return bfprt(mArr, (length - 1) / 2, 0, length - 1);
    }

    private static int getMedian(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (arr[j] > arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }

        return arr[(left + right) / 2];
    }

    private static int[] partition3(int[] arr, int pivot, int left, int right) {
        int less = left - 1;
        int more = right + 1;
        int index = left;
        while (index < more) {
            if (arr[index] > pivot) {
                swap(arr, ++less, index++);
            } else if (arr[index] == pivot) {
                index++;
            } else {
                swap(arr, index, --more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    // --------------------------------------------------------- //


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr2, k);
            int ans3 = minKth3(arr3, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i != ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
