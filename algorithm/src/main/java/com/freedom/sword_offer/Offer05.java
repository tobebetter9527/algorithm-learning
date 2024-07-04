package com.freedom.sword_offer;

public class Offer05 {

    public static String replaceSpace(String s) {
        int n = s.length();
        if (n == 0) {
            return s;
        }

        char[] chars = new char[n * 3];
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                chars[size++] = '%';
                chars[size++] = '2';
                chars[size++] = '0';
            } else {
                chars[size++] = s.charAt(i);
            }
        }
        return String.valueOf(chars, 0, size);
    }

    public static void main(String[] args) {
        String s = "  ";
        System.out.println(replaceSpace(s));
    }
}
