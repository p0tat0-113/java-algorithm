package baekjoon;/*
문제
아직 아무 의견이 없다면 문제의 난이도는 0으로 결정한다.
의견이 하나 이상 있다면, 문제의 난이도는 모든 사람의 난이도 의견의 30% 절사평균으로 결정한다.
절사평균이란 극단적인 값들이 평균을 왜곡하는 것을 막기 위해 가장 큰 값들과 가장 작은 값들을 제외하고 평균을 내는 것을 말한다. 30% 절사평균의 경우 위에서 15%, 아래에서 15%를 각각 제외하고 평균을 계산한다. 따라서 20명이 투표했다면, 가장 높은 난이도에 투표한 3명과 가장 낮은 난이도에 투표한 3명의 투표는 평균 계산에 반영하지 않는다는 것이다.

제외되는 사람의 수는 위, 아래에서 각각 반올림한다. 25명이 투표한 경우 위, 아래에서 각각 3.75명을 제외해야 하는데, 이 경우 반올림해 4명씩을 제외한다.
마지막으로, 계산된 평균도 정수로 반올림된다. 절사평균이 16.7이었다면 최종 난이도는 17이 된다.
사용자들이 어떤 문제에 제출한 난이도 의견 목록이 주어질 때, solved.ac가 결정한 문제의 난이도를 계산하는 프로그램을 작성하시오.

입력
첫 번째 줄에 난이도 의견의 개수 n이 주어진다. (0 ≤ n ≤ 3 × 105)
이후 두 번째 줄부터 1 + n번째 줄까지 사용자들이 제출한 난이도 의견 n개가 한 줄에 하나씩 주어진다. 모든 난이도 의견은 1 이상 30 이하이다.

출력
solved.ac가 계산한 문제의 난이도를 출력한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];

        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int cut = (int)(Math.round(num * 0.15));

        //System.out.println(cut);

        Arrays.sort(arr);

        int sum = 0;
        for (int i = cut; i < arr.length-cut; i++) {
            sum += arr[i];
        }

        System.out.println(Math.round((double)sum/(arr.length - 2*cut)));
    }
}
