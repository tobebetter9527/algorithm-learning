package com.freedom.zuo.class32_index_tree_ac;

/**
 * <p>
 * 数组int[] arr,下标从1到8，忽略0位置。
 * <p>
 * 1）index位置的累加和规律，index二进制最右的1去掉，再加1得到start位置，index为end位置，例子：8=01000，start:00001,end:01000
 * <p>
 * 2) index位置插入value，影响的位置: index的二进制加上最右1的值，直到index小于等于length，例子：1=00001，影响位置1，2，4，8 .
 * <p>
 * 00001 -> 00010 -> 00100 -> 01000
 * <p>
 * 3) 求1到index位置的累加和，index二进制的最右1去掉的值，累加。 例子：6=0110，-> 4=0100,计算6和4位置的累加和即可。
 * <p>
 * 时间复杂度O(logn)
 *
 * @author tobebetter9527
 * @create 2022/08/07 16:14
 */
public class Code01_IndexTree {

    public static void main(String[] args) {
        int length = 100;
        int maxVlaue = 10;
        int testTimes = 10000000;

        IndexTree indexTree = new IndexTree(length);
        Right right = new Right(length);

        for (int i = 0; i < testTimes; i++) {
            int index = (int) (Math.random() * length) + 1;
            int value = (int) (Math.random() * maxVlaue) + 1;
            if (Math.random() <= 0.5D) {
                indexTree.add(index, value);
                right.add(index, value);
            } else {
                int sum = indexTree.sum(index);
                int sum1 = right.sum(index);
                if (sum != sum1) {
                    System.out.println("Something is wrong!");
                }
            }
        }
        System.out.println("done!");
    }

    // ----------------------------------------------------------------------------- //

    static class IndexTree {

        int[] tree;
        int length;

        public IndexTree(int length) {
            this.length = length;
            tree = new int[length + 1];
        }

        /**
         * 在index位置，加值value
         *
         * @param index 索引
         * @param value 值
         */
        public void add(int index, int value) {
            while (index <= length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        /**
         * 求1到index累加和
         *
         * @param index 索引
         * @return
         */
        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }
    }

    // ----------------------------------------------------------------------------- //

    static class Right {

        int[] nums;
        int length;

        public Right(int length) {
            this.length = length;
            nums = new int[length + 1];
        }

        public void add(int index, int value) {
            nums[index] += value;
        }

        public int sum(int index) {
            int ans = 0;
            for (int i = 1; i <= index; i++) {
                ans += nums[i];
            }
            return ans;
        }
    }

}
