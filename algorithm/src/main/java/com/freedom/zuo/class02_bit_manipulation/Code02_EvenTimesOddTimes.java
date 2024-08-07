package com.freedom.zuo.class02_bit_manipulation;

public class Code02_EvenTimesOddTimes {

    /**
     * arr中，只有一种数，出现奇数次
     *
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * arr中，有两种数，出现奇数次
     *
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000 , 提取出最右的1
        int rightOne = eor & (-eor);

        // eor'
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    /**
     * 求为1的二进制数有几个
     *
     * @param num
     * @return
     */
    public static int countBits(int num) {
        int count = 0;
        // 011011010000
        // 000000010000
        // 011011000000
        while (num != 0) {
            int rightOne = num & ((~num) + 1);
            count++;
            num ^= rightOne;
            // N -= rightOne
        }
        return count;
    }


    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

        int i = countBits(5);
        System.out.println(i);
    }

}
