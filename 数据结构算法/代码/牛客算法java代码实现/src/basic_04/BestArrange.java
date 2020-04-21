package basic_04;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    /*
    [best arrange]一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目 的宣讲。
    给你每一个项目开始的时间和结束的时间(给你一个数 组，里面 是一个个具体的项目)，
    你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回这个最多的宣讲场次。
    思路：先选最早结束的，淘汰掉冲突的。再选剩下中最早结束的。
    过程：先把项目按结束时间大小排序。遍历这个排序好的数组，若cur时间小于项目i的开始时间，
    则计数加1，且cur变为该项目的结束时间
     */
    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
