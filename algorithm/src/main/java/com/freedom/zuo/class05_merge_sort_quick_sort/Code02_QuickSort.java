package com.freedom.zuo.class05_merge_sort_quick_sort;

/**
 * 快速排序
 */
public class Code02_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        process(arr, left, pivot - 1);
        process(arr, pivot + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = right;
        int index = left;
        int i = left - 1;
        while (index < right) {
            if (arr[index] <= arr[pivot]) {
                swap(arr, index, ++i);
            }
            index++;
        }
        swap(arr, ++i, pivot);
        return i;
    }

    public static void quickSort2ThroughDutchFlag(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        processWithDutchFlag(arr, 0, arr.length - 1);
    }

    private static void processWithDutchFlag(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 随机选一个作为pivot，降低出现最差时间复杂度的概率
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);

        int[] indexs = dutchNationalFlag(arr, left, right);
        processWithDutchFlag(arr, left, indexs[0] - 1);
        processWithDutchFlag(arr, indexs[1] + 1, right);
    }


    /**
     * The Dutch national flag (DNF) problem is one of the most popular programming problems proposed by Edsger Dijkstra.
     * The flag of the Netherlands consists of three colors: white, red, and blue. The task is to randomly arrange balls
     * of white, red, and blue such that balls of the same color are placed together.
     * <p>
     * Consider this problem on an array; the task is to sort arrays of 0, 1, and 2 in linear time without any extra
     * space. Since the array is only traversed once, the time complexity of the algorithm given below is O(n).
     * <p>
     * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
     *
     * @return 数组下标
     */
    private static int[] dutchNationalFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        // 最后一个为pivot
        int pivot = right;
        // 小于给定值的索引
        int less = left - 1;
        // 大于给定值的索引
        int more = right;
        int index = left;

        while (index < more) {
            if (arr[index] == arr[pivot]) {
                index++;
            } else if (arr[index] < arr[pivot]) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, pivot);
        return new int[]{less + 1, more};
    }


    private static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
//    int rang = 100;
//    int maxSize = 100;
//    int testTime = 10000000;
//    for (int i = 0; i < testTime; i++) {
//      int[] arr = generateRandomArray(rang, maxSize);
//      int[] copyArr = copy(arr);
//      quickSort(arr);
//      Arrays.sort(copyArr);
//      if (!isEqual(arr, copyArr)) {
//        System.out.println("wrong");
//      }
//    }
//    System.out.println("done!");

//    int[] arr = {3, 2, 1, 3, 6, 4, 3};
//    quickSort(arr);

        int[] arr = generateRandomArray(100, 1000);
        quickSort2ThroughDutchFlag(arr);
        System.out.println(arr);
    }

    private static int[] generateRandomArray(int rang, int maxSize) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * rang) - (int) (Math.random() * rang);
        }
        return arr;
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

    // for test
    private static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
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
}
