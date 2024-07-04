package com.freedom.sword_offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Offer59II {

    static class MaxQueue {
        private Queue<Integer> queue;
        private Deque<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public int max_value() {
            return deque.isEmpty() ? -1 : deque.peekFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.offerLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            int peek = queue.poll();
            if (peek == deque.peekFirst()) {
                deque.pollFirst();
            }
            return peek;
        }
    }
}
