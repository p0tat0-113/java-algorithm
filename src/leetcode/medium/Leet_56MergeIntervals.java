package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class Leet_56MergeIntervals {
    public static void main(String[] args) {
        //new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})
        //new int[][]{{1, 4}, {0, 0}})

        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 3}})));
    }

    //intervals = [[1,4],[0,4]]일 때 내 코드는 [[1,4]]를 뱉지만, [[0,4]]가 나와야 함.
    //오버랩되는 모든 경우의 수를 다 커버해야 할 듯. o를 왼쪽, x를 오른쪽 배열이라고 할 때
    //oxxo xoox oxox xoxo 이런 경우의 수가 있음.

    //
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a1, a2) -> {
            return Integer.compare(a1[0],a2[0])+Integer.compare(a1[1],a2[1]);
        });

        System.out.println(Arrays.deepToString(intervals));

        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int resultIdx = 0;

        for (int i = 0; i < intervals.length; i++) {
            if (result.get(resultIdx)[1] >= intervals[i][0]) {
                //result.get(resultIdx)[1] = intervals[i][1];

                //추가
                if (result.get(resultIdx)[0] > intervals[i][0]) {
                    result.get(resultIdx)[0] = intervals[i][0];
                }

                if (result.get(resultIdx)[1] < intervals[i][1]) {
                    result.get(resultIdx)[1] = intervals[i][1];
                }
            }

            else {
                result.add(intervals[i]);
                resultIdx++;
            }
        }

        return result.toArray(new int[resultIdx+1][]);
    }
}
