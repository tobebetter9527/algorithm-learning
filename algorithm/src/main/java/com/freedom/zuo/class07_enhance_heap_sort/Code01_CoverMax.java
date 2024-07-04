package com.freedom.zuo.class07_enhance_heap_sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 *
 * @author tobebetter9527
 * @create 2022/06/21 22:05
 */
public class Code01_CoverMax {

    /**
     * 暴力遍历法
     *
     * @param lines
     * @return
     */
    public static int maxCover1(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }

        int length = lines.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < length; i++) {
                if (p > lines[i][0] && p < lines[i][1]) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }

        return cover;
    }

    /**
     * 堆方式处理
     *
     * @param arr
     * @return
     */
    public static int maxCover2(int[][] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int length = arr.length;
        Line[] lines = new Line[length];
        for (int i = 0; i < length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }

        Arrays.sort(lines, new StartComparator());
        // 对于Integer，默认就是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < length; i++) {
            Line line = lines[i];
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static int maxCover3(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }

        Arrays.sort(lines, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i][0]) {
                heap.poll();
            }
            heap.add(lines[i][1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    public static void main(String[] args) {
        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        int maxValue = 100;
        int minValue = 0;
        int maxSize = 100;
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateArray(maxSize, minValue, maxValue);
            int i1 = maxCover1(lines);
            int i2 = maxCover2(lines);
            int i3 = maxCover3(lines);
            if (i1 != i2 || i1 != i3 || i2 != i3) {
                System.out.println("something wrong");
            }
        }
        System.out.println("done!");
    }

    private static int[][] generateArray(int maxSize, int minValue, int maxValue) {
        int size = (int) (Math.random() * maxSize);
        int[][] lines = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = minValue + (int) (Math.random() * (maxValue - minValue + 1));
            int b = minValue + (int) (Math.random() * (maxValue - minValue + 1));
            if (a == b) {
                b = b + 1;
            }
            lines[i][0] = Math.min(a, b);
            lines[i][1] = Math.max(a, b);
        }
        return lines;
    }

    private static class Line {

        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }


}
