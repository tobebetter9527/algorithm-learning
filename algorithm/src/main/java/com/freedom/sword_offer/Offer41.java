package com.freedom.sword_offer;

import java.util.PriorityQueue;

public class Offer41 {

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        finder.addNum(2);
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }

    static class MedianFinder {

        /**
         * 保存中位数之前到数
         */
        PriorityQueue<Integer> big;
        /**
         * 保存中位数后面到数
         */
        PriorityQueue<Integer> small;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            big = new PriorityQueue<>((x, y) -> y - x);
            small = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (big.isEmpty() || num <= big.peek()) {
                big.add(num);
                if (big.size() > small.size() + 1) {
                    small.add(big.poll());
                }
            } else {
                small.add(num);
                if (big.size() < small.size()) {
                    big.add(small.poll());
                }
            }
        }

        public double findMedian() {
            if (big.size() > small.size()) {
                return big.peek();
            } else {
                return (big.peek() + small.peek()) / 2.0D;
            }
        }
    }

}
