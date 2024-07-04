package com.freedom.leetcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the
 * functions of a normal stack (push, top, pop, and empty). 双队列和单队列都能实现
 *
 * @author tobebetter9527
 * @create 2022/06/11 9:20
 */
public class Problem225_implementStackUsingQueues {

    private static class MyStack3 {

        Queue<Integer> queue1;

        public MyStack3() {
            queue1 = new LinkedList<>();
        }

        public void push(int x) {
            int size = queue1.size();
            queue1.offer(x);
            for (int i = 0; i < size; i++) {
                queue1.offer(queue1.poll());
            }
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }


    /**
     * 时间复杂度：push为O(n),其他为O(1).
     */
    private static class MyStack2 {

        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack2() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }

        public int pop() {
            return queue1.poll();
        }

        public int top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }


    private static class MyStack {

        Queue<Integer> queue1;
        Queue<Integer> queue2;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            if (queue1.isEmpty()) {
                queue1.add(x);
                while (!queue2.isEmpty()) {
                    queue1.add(queue2.remove());
                }
            } else {
                queue2.add(x);
                while (!queue1.isEmpty()) {
                    queue2.add(queue1.remove());
                }
            }
        }

        public int pop() {
            if (!queue1.isEmpty()) {
                return queue1.remove();
            }
            return queue2.remove();
        }

        public int top() {
            if (!queue1.isEmpty()) {
                return queue1.peek();
            }
            return queue2.peek();
        }

        public boolean empty() {
            return queue2.isEmpty() && queue1.isEmpty();
        }
    }

}
