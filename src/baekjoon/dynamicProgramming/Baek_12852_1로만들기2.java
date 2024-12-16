package baekjoon.dynamicProgramming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/*
전에 풀어봤던 1로 만들기 문제는 간단한 dp문제였다. 그런데 이 문제에서는 이 과정을 역추적해야한다.
역추적 하는 것은 LIS를 역추적 하던 방식에서 힌트를 얻었다.

첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다. 정답이 여러 가지인 경우에는 아무거나 출력한다.
예제 입력 1
2
예제 출력 1
1
2 1

그런데 알고리즘 분류를 보면 DP뿐만 아니라 그래프 탐색도 적혀있네. dfs로 풀 수 있을 것 같기도 하고...
*/
public class Baek_12852_1로만들기2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        process(scanner.nextInt());
    }

    private static void process(int n) {
        int[] dp = new int[n+1];//dp[i]는 숫자 i를 만드는 최소 연산 횟수를 저장한다.
        dp[1] = 0;

        int[] track = new int[n+1];//track[i]는 숫자 i를 만드는 최소 연산 바로 전의 숫자를 저장한다. 예를 들어 10을 만드는 최소 연산이 9에 1을 더하는 거였다면 track[10]에는 9가 저장된다.

        for (int i = 2; i <= n; i++) {
            int multi3 = Integer.MAX_VALUE;
            int multi2 = Integer.MAX_VALUE;
            int plus1 = 0;

            if (i % 3 == 0) {
                multi3 = dp[i/3] + 1;
            }
            if (i % 2 == 0) {
                multi2 = dp[i/2] + 1;
            }
            plus1 = dp[i-1] + 1;

            dp[i] = Math.min(plus1, Math.min(multi2, multi3));//여기까지는 dp연산 과정.

            //여기부터가 추적 과정이다.
            if (dp[i] == plus1) {
                track[i] = i-1;
            } else if (dp[i] == multi2) {
                track[i] = i/2;
            } else {
                track[i] = i/3;
            }
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(track));

        //추적 배열을 따라가면서 sb에 숫자를 저장.
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append(" ");
        int currentIdx = n;
        while (track[currentIdx] != 0) {
            sb.append(track[currentIdx]);
            sb.append(" ");
            currentIdx = track[currentIdx];
        }

        System.out.println(dp[n]);
        System.out.println(sb);
    }
}
