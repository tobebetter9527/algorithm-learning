package com.freedom.zuo.class31_segment_tree;

/**
 * 线段树
 * <p>
 * 给定一个数组arr，用户希望你实现如下三个方法
 * <p>
 * 1）void add(int L, int R, int V) : 让数组arr[L…R]上每个数都加上V
 * <p>
 * 2）void update(int L, int R, int V) : 让数组arr[L…R]上每个数都变成V
 * <p>
 * 3）int sum(int L, int R) :让返回arr[L…R]这个范围整体的累加和
 * <p>
 * 怎么让这三个方法，时间复杂度都是O(logN)
 *
 * @author tobebetter9527
 * @create 2022/08/06 19:17
 */
public class Code01_SegmentTree {

    public static void main(String[] args) {
        int[] origin = {2, 1, 1, 2, 3, 4, 5};
        SegmentTree seg = new SegmentTree(origin);
        // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int l = 1;
        // 整个区间的结束位置，规定能到N，不是N-1 -> 固定, 因为新的数据大一位
        int r = origin.length;
        // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int lrIndex = 1;

        // 操作区间的开始位置 -> 可变
        int left = 2;
        // 操作区间的结束位置 -> 可变
        int right = 5;
        // 要加的数字或者要更新的数字 -> 可变
        int value = 4;

        // 区间生成，必须在[S,N]整个范围上build
        seg.build(l, r, lrIndex);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(left, right, value, l, r, lrIndex);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(left, right, value, l, r, lrIndex);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(left, right, l, r, lrIndex);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));
    }

    // ----------------------------------------------------------- //

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    // ----------------------------------------------------------- //

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);

            SegmentTree seg = new SegmentTree(origin);
            int l = 1;
            int r = origin.length;
            int lrIndex = 1;
            seg.build(l, r, lrIndex);

            AbRight rig = new AbRight(origin);

            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * r) + 1;
                int num2 = (int) (Math.random() * r) + 1;
                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);
                int value = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(left, right, value, l, r, lrIndex);
                    rig.add(left, right, value);
                } else {
                    seg.update(left, right, value, l, r, lrIndex);
                    rig.update(left, right, value);
                }
            }

            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * r) + 1;
                int num2 = (int) (Math.random() * r) + 1;
                int left = Math.min(num1, num2);
                int right = Math.max(num1, num2);
                long ans1 = seg.query(left, right, l, r, lrIndex);
                long ans2 = rig.query(left, right);
                if (ans1 != ans2) {
                    return false;
                }
            }

        }
        return true;
    }

    static class SegmentTree {

        /**
         * 比原数组大1，因为0位置不存值
         */
        private int maxLength;
        /**
         * 存储原数组信息，从1开始
         */
        private int[] arr;
        /**
         * 模拟线段树维护区间和
         */
        private int[] sum;
        /**
         * 累加和懒惰标记
         */
        private int[] lazy;
        /**
         * 更新的值
         */
        private int[] change;
        /**
         * 更新慵懒标记
         */
        private boolean[] update;

        public SegmentTree(int[] origin) {
            maxLength = origin.length + 1;
            arr = new int[maxLength];
            for (int i = 1; i < maxLength; i++) {
                arr[i] = origin[i - 1];
            }
            // 用来支持脑补概念中，某一个范围的累加和信息
            sum = new int[maxLength << 2];
            // 用来支持脑补概念中，某一个范围沒有往下傳遞的纍加任務
            lazy = new int[maxLength << 2];
            // 用来支持脑补概念中，某一个范围有没有更新操作的任务
            change = new int[maxLength << 2];
            // 用来支持脑补概念中，某一个范围更新任务，更新成了什么
            update = new boolean[maxLength << 2];
        }

        /**
         * 构建区间和数组int[] sum ，例子：三个参数分别为（1，10，1）
         *
         * @param l       索引左边界
         * @param r       索引右边界
         * @param lrIndex l到r数组和的存储位置
         */
        public void build(int l, int r, int lrIndex) {
            // 已经到最底了
            if (l == r) {
                sum[lrIndex] = arr[l];
                return;
            }
            // 求中点
            int mid = (l + r) >> 1;
            // left到mid继续构建
            build(l, mid, lrIndex << 1);
            // mid+1到right继续构建,  index << 1 | 1 意思是index * 2 + 1
            build(mid + 1, r, lrIndex << 1 | 1);

            // 求left到right的sum值
            pushUp(lrIndex);
        }

        /**
         * 要求left到right范围内每个值加value，
         *
         * @param left
         * @param right
         * @param value
         * @param l       索引左边界
         * @param r       索引右边界
         * @param lrIndex l到r数组和的存储位置
         */
        public void add(int left, int right, int value, int l, int r, int lrIndex) {
            // 关键点懒加，这是时间复杂度为O(logn)的原因,l,r在left和right范围
            if (left <= l && r <= right) {
                sum[lrIndex] += value * (r - l + 1);
                lazy[lrIndex] += value;
                return;
            }

            int mid = (l + r) >> 1;
            pushDowm(lrIndex, mid - l + 1, r - mid);

            if (left <= mid) {
                add(left, right, value, l, mid, lrIndex << 1);
            }

            if (right > mid) {
                add(left, right, value, mid + 1, r, lrIndex << 1 | 1);
            }

            pushUp(lrIndex);
        }

        public void update(int left, int right, int value, int l, int r, int lrIndex) {
            if (left <= l && r <= right) {
                update[lrIndex] = true;
                change[lrIndex] = value;
                sum[lrIndex] = value * (r - l + 1);
                lazy[lrIndex] = 0;
                return;
            }

            int mid = (l + r) >> 1;
            pushDowm(lrIndex, mid - l + 1, r - mid);

            if (left <= mid) {
                update(left, right, value, l, mid, lrIndex << 1);
            }

            if (right > mid) {
                update(left, right, value, mid + 1, r, lrIndex << 1 | 1);
            }

            pushUp(lrIndex);
        }

        public int query(int left, int right, int l, int r, int lrIndex) {
            if (left <= l && r <= right) {
                return sum[lrIndex];
            }

            int mid = (l + r) >> 1;
            pushDowm(lrIndex, mid - l + 1, r - mid);

            int ans = 0;

            if (left <= mid) {
                ans += query(left, right, l, mid, lrIndex << 1);
            }

            if (right > mid) {
                ans += query(left, right, mid + 1, r, lrIndex << 1 | 1);
            }

            return ans;
        }


        private void pushDowm(int lrIndex, int lSize, int rSize) {
            if (update[lrIndex]) {
                update[lrIndex << 1] = true;
                update[lrIndex << 1 | 1] = true;
                change[lrIndex << 1] = change[lrIndex];
                change[lrIndex << 1 | 1] = change[lrIndex];
                lazy[lrIndex << 1] = 0;
                lazy[lrIndex << 1 | 1] = 0;
                sum[lrIndex << 1] = change[lrIndex] * lSize;
                sum[lrIndex << 1 | 1] = change[lrIndex] * rSize;
                update[lrIndex] = false;
            }

            if (lazy[lrIndex] != 0) {
                lazy[lrIndex << 1] += lazy[lrIndex];
                sum[lrIndex << 1] += lazy[lrIndex] * lSize;
                lazy[lrIndex << 1 | 1] += lazy[lrIndex];
                sum[lrIndex << 1 | 1] += lazy[lrIndex] * rSize;
                lazy[lrIndex] = 0;
            }
        }


        /**
         * 合并两个子树的值
         *
         * @param index
         */
        private void pushUp(int index) {
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }
    }

    /**
     * 暴力法
     */
    static class AbRight {

        int[] arr;

        public AbRight(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void add(int left, int right, int value) {
            for (int i = left; i <= right; i++) {
                arr[i] += value;
            }
        }

        public void update(int left, int right, int value) {
            for (int i = left; i <= right; i++) {
                arr[i] = value;
            }
        }

        public int query(int left, int right) {
            int ans = 0;
            for (int i = left; i <= right; i++) {
                ans += arr[i];
            }
            return ans;
        }
    }


}
