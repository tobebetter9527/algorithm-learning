package com.freedom.leetcode.heap;

import java.util.PriorityQueue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value
 * and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3. For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object. void addNum(int num) adds the integer num from the data stream to
 * the data structure. double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual
 * answer will be accepted.
 *
 * @author tobebetter9527
 * @create 2022/06/23 21:35
 */
public class Problem295_FindMedianFromDataStream {

    static class MedianFinder {

        int count;
        PriorityQueue<Integer> bigRootHeap;
        PriorityQueue<Integer> smallRootHeap;


        public MedianFinder() {
            // 创建大根堆
            bigRootHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
            // 默认小根堆
            smallRootHeap = new PriorityQueue<>();
        }

        /**
         * 官方版本，更好
         *
         * @param num
         */
        public void addNum2(int num) {
            if (bigRootHeap.isEmpty() || num <= bigRootHeap.peek()) {
                bigRootHeap.offer(num);
                if (smallRootHeap.size() + 1 < bigRootHeap.size()) {
                    smallRootHeap.offer(bigRootHeap.poll());
                }
            } else {
                smallRootHeap.offer(num);
                if (smallRootHeap.size() > bigRootHeap.size()) {
                    bigRootHeap.offer(smallRootHeap.poll());
                }
            }
        }

        /**
         * 官方版本，更好
         */
        public double findMedian2() {
            if (bigRootHeap.size() > smallRootHeap.size()) {
                return bigRootHeap.peek();
            }
            return (bigRootHeap.peek() + smallRootHeap.peek()) / 2.0D;
        }

        public void addNum(int num) {
            count++;

            // 如果num大于等于小根堆堆顶，num放入小根堆，如果小根堆大小不是n/2, 取出堆顶放入大根堆
            if (!smallRootHeap.isEmpty() && num >= smallRootHeap.peek()) {
                // 如果num大于等于小根堆堆顶，num放入小根堆，如果小根堆大小不是n/2, 取出堆顶放入大根堆
                smallRootHeap.add(num);
                if (smallRootHeap.size() != (count / 2)) {
                    bigRootHeap.add(smallRootHeap.poll());
                }
            } else {
                // 如果num小于等于大根堆堆顶，num放入大根堆，根据count是否奇偶数，判断大根堆的大小是否符合要求
                bigRootHeap.add(num);
                // count是奇数
                if (count % 2 == 1) {
                    if (bigRootHeap.size() != (count / 2 + 1)) {
                        smallRootHeap.add(bigRootHeap.poll());
                    }
                } else {
                    if (bigRootHeap.size() != (count / 2)) {
                        smallRootHeap.add(bigRootHeap.poll());
                    }
                }
            }
        }

        public double findMedian() {
            if (count % 2 == 1) {
                return bigRootHeap.isEmpty() ? 0.0D : bigRootHeap.peek();
            } else {
                double a = !bigRootHeap.isEmpty() ? bigRootHeap.peek() : 0.0D;
                double b = !smallRootHeap.isEmpty() ? smallRootHeap.peek() : 0.0D;
                return (a + b) / 2.0D;
            }
        }
    }

}
