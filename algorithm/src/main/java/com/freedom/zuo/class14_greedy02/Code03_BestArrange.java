package com.freedom.zuo.class14_greedy02;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 安排最多会议数量问题
 *
 * @author tobebetter9527
 * @create 2022/01/16 23:09
 */
public class Code03_BestArrange {

  public static void main(String[] args) {
    Meeting[] meetings = new Meeting[5];
    meetings[0] = new Meeting(0, 1);
    meetings[1] = new Meeting(1, 3);
    meetings[2] = new Meeting(4, 6);
    meetings[3] = new Meeting(1, 2);
    meetings[4] = new Meeting(2, 4);

    int i = greedyArrange(meetings);
    System.out.println(i);
  }


  public static int greedyArrange(Meeting[] meetings) {
    if (meetings == null || meetings.length == 0) {
      return 0;
    }

    Arrays.sort(meetings, new MeetingComparator());
    int result = 0;
    int timeLine = 0;

    for (Meeting meeting : meetings) {
      if (timeLine <= meeting.getStart()) {
        result++;
        timeLine = meeting.getEnd();
      }
    }

    return result;
  }

  static class MeetingComparator implements Comparator<Meeting> {

    @Override
    public int compare(Meeting o1, Meeting o2) {
      return o1.getEnd() - o2.getEnd();
    }
  }

  static class Meeting {

    private int start;
    private int end;

    public Meeting(int start, int end) {
      if (start > end) {
        throw new IllegalArgumentException(" start is bigger than end");
      }

      this.start = start;
      this.end = end;
    }

    public int getStart() {
      return start;
    }

    public void setStart(int start) {
      this.start = start;
    }

    public int getEnd() {
      return end;
    }

    public void setEnd(int end) {
      this.end = end;
    }
  }

}
