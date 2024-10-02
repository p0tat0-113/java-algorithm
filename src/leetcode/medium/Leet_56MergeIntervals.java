package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*
https://imgur.com/a/x8xyKHF
98프로를 땄다. 뿌듯하네ㅋㅋ
*/

public class Leet_56MergeIntervals {
    public static void main(String[] args) {
        //new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})
        //new int[][]{{1, 4}, {0, 0}})

        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 4}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 0}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}})));
    }

    //알고리즘 이론 과제 제출을 위해 아래 코드의 정렬 부분을 내가 직접 구현하려고 함. comparator는 그냥 그대로 쓰고, 병합정렬만 내가 직접 구현을 하자.
    public static int[][] merge(int[][] intervals) {
        //길이가 1이면 그대로 반환. 비교할 대상이 없음.
        if (intervals.length == 1) {
            return intervals;
        }

        //배열의 0번 인덱스 숫자를 기준으로 정렬한다. 뒤에 오는 배열의 0번 인덱스 숫자는 절대로 앞 배열의 0번 인덱스 숫자보다 작을 수 없다.
        //병합정렬을 직접 구현함. 코드의 간소화를 위해 Comparator를 구현해서 인수로 넣어주었다.
        mergeSort(intervals, 0, intervals.length-1, (a1, a2) -> {
            return Integer.compare(a1[0],a2[0]);
        });

        //System.out.println(Arrays.deepToString(intervals));//이거 하나 지웠더니 리트코드상에서 10ms빨라짐ㅋㅋ

        int resultIdx = 0;

        //합치는 과정을 별도의 배열을 만들지 않고, 그냥 inplace로 진행한다.
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[resultIdx][1] >= intervals[i][0]) {//둘이 겹치는 부분이 있다면
                if (intervals[resultIdx][1] < intervals[i][1]) {//양쪽 1번 인덱스 숫자를 비교, result쪽이 interval쪽보다 작다면 result쪽 [1]을 interval쪽 [1]로 바꿈.
                    intervals[resultIdx][1] = intervals[i][1];
                }
            } else {//둘이 겹치는 부분이 아예 없으면 새로 추가
                intervals[++resultIdx] = intervals[i];
            }
        }

        //하나하나 복사하지 않고, Arrays.copyOf()로 간소화함. 합치는 과정을 inplace로 진행해서 이렇게 실제 합쳐진 범위만큼만 뽑아내서 반환해줘야 한다.
        /*int[][] result = new int[resultIdx+1][];
        for (int i = 0; i < resultIdx+1; i++) {
            result[i] = intervals[i];
        }
        return result;*/

        return Arrays.copyOf(intervals, resultIdx+1);
    }

    private static void mergeSort(int[][] arr, int s, int e, Comparator<int[]> comparator){
        if (s<e) {
            int m = (s+e)/2;
            mergeSort(arr,s,m,comparator);//s~m까지
            mergeSort(arr,m+1,e,comparator);//m+1에서 e까지
            merge(arr,s,m,e,comparator);
        }
    }

    private static void merge(int[][] arr, int s, int m, int e, Comparator<int[]> comparator){
        int[][] temp = new int[e - s + 1][];//정렬 결과를 저장할 보조배열
        int tempIdx = 0;
        int left = s;
        int right = m+1;

        while (left <= m && right <= e) {
            if (comparator.compare(arr[left], arr[right]) < 0) {//왼쪽이 더 작으면
                temp[tempIdx++] = arr[left++];
            } else {
                temp[tempIdx++] = arr[right++];
            }
        }

        while (left <= m) {
            temp[tempIdx++] = arr[left++];
        }
        while (right <= e) {
            temp[tempIdx++] = arr[right++];
        }

        System.arraycopy(temp,0,arr,s,temp.length);
    }

    //아래 코드를 별도의 리스트를 사용하지 말고, in-place하게 개선할 수도 있음!!!
    /*public static int[][] merge(int[][] intervals) {
        //길이가 1이면 그대로 반환. 비교할 대상이 없음.
        if (intervals.length == 1) {
            return intervals;
        }

        //배열의 0번 인덱스 숫자를 기준으로 정렬한다. 뒤에 오는 배열의 0번 인덱스 숫자는 절대로 앞 배열의 0번 인덱스 숫자보다 작을 수 없다.
        Arrays.sort(intervals, (a1, a2) -> {
            return Integer.compare(a1[0],a2[0]);
        });

        //System.out.println(Arrays.deepToString(intervals));//이거 하나 지웠더니 리트코드상에서 10ms빨라짐ㅋㅋ

        int resultIdx = 0;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[resultIdx][1] >= intervals[i][0]) {//둘이 겹치는 부분이 있다면
                if (intervals[resultIdx][1] < intervals[i][1]) {//양쪽 1번 인덱스 숫자를 비교, result쪽이 interval쪽보다 작다면 result쪽 [1]을 interval쪽 [1]로 바꿈.
                    intervals[resultIdx][1] = intervals[i][1];
                }
            } else {//둘이 겹치는 부분이 아예 없으면 새로 추가
                intervals[++resultIdx] = intervals[i];
            }
        }

        *//*int[][] result = new int[resultIdx+1][];
        for (int i = 0; i < resultIdx+1; i++) {
            result[i] = intervals[i];
        }

        return result;*//*

        return Arrays.copyOf(intervals, resultIdx+1);
    }*/


    /*public static int[][] merge(int[][] intervals) {
        //길이가 1이면 그대로 반환. 비교할 대상이 없음.
        if (intervals.length == 1) {
            return intervals;
        }

        //배열의 0번 인덱스 숫자를 기준으로 정렬한다. 뒤에 오는 배열의 0번 인덱스 숫자는 절대로 앞 배열의 0번 인덱스 숫자보다 작을 수 없다.
        Arrays.sort(intervals, (a1, a2) -> {
            return Integer.compare(a1[0],a2[0]);
        });

        //System.out.println(Arrays.deepToString(intervals));//이거 하나 지웠더니 리트코드상에서 10ms빨라짐ㅋㅋ

        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int resultIdx = 0;

        for (int i = 0; i < intervals.length; i++) {
            if (result.get(resultIdx)[1] >= intervals[i][0]) {//둘이 겹치는 부분이 있다면
                if (result.get(resultIdx)[1] < intervals[i][1]) {//양쪽 1번 인덱스 숫자를 비교, result쪽이 interval쪽보다 작다면 result쪽 [1]을 interval쪽 [1]로 바꿈.
                    result.get(resultIdx)[1] = intervals[i][1];
                }
            } else {//둘이 겹치는 부분이 아예 없으면 새로 추가
                result.add(intervals[i]);
                resultIdx++;
            }
        }

        return result.toArray(new int[resultIdx+1][]);
    }*/

    //이 코드는 처음부터 접근이 잘못 돼서 계속 틀리고 있었다.
    /*//intervals = [[1,4],[0,4]]일 때 내 코드는 [[1,4]]를 뱉지만, [[0,4]]가 나와야 함.
    //오버랩되는 모든 경우의 수를 다 커버해야 할 듯. o를 왼쪽, x를 오른쪽 배열이라고 할 때
    //oxxo xoox oxox xoxo 이런 경우의 수가 있음.
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (a1, a2) -> {
            return Integer.compare(a1[0],a2[0])-Integer.compare(a1[1],a2[1]);
        });

        System.out.println(Arrays.deepToString(intervals));

        ArrayList<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int resultIdx = 0;

        for (int i = 0; i < intervals.length; i++) {

            if (result.get(resultIdx)[1] >= intervals[i][0] && !(result.get(resultIdx)[0] > intervals[i][1])) {
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
    }*/
}
