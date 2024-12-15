package baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
LIS문제다. 리트코드로 이미 풀어본 문제임. 단순히 LIS의 길이를 구하는 것 뿐만 아니라 역추적까지 해야한다.

*/
public class Baek_14002가장긴증가하는부분수열4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        String[] strings = br.readLine().split(" ");
        for (int i = 0; i < strings.length; i++) {
            nums[i] = Integer.parseInt(strings[i]);
        }

        process(n, nums);
    }

    private static void process(int n, int[] nums) {
        int[] dp = new int[n];//dp[i]는 nums[i]의 숫자로 끝나는 LIS의 길이를 의미한다. 우선 어떤 숫자로 끝나던 간에 LIS의 길이는 최소한 1이다.
        Arrays.fill(dp, 1);

        int[] track = new int[n];//역추적을 위한 배열. track[i]는 nums[i]로 끝나는 LCS에서 nums[i]바로 앞에 오는 숫자의 인덱스를 저장한다.
        Arrays.fill(track, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    track[i] = j;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        ArrayList<String> lis = new ArrayList<>();
        while (maxIndex != -1) {
            lis.add(String.valueOf(nums[maxIndex]));
            maxIndex = track[maxIndex];
        }
        Collections.reverse(lis);

        System.out.println(max);
        System.out.println(String.join(" ", lis));
    }
}
