package leetcode.review;

import java.util.Scanner;

/*
https://www.acmicpc.net/problem/1463
간단한 DP 메모이제이션 문제다.
임의의 수 N이 주어지면, 3으로 나누어 떨어지는 경우 3으로 나누기, 2로 나누어 떨어지는 경우 2로 나누기, 1빼기, 이렇게 세가지 연산을 가장 적게 사용해서 N을 1로 만들 수 있는 방법을 찾으면 된다.
나는 이걸 1에 3곱하기, 2곱하기, 1더하기 연산을 해서 N을 만드는 문제로 접근을 했다.
*/

public class Baek_1463_1로만들기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] processArr = new int[n + 1];

        //System.out.println(Arrays.toString(processArr));
        process(processArr);
        //System.out.println(Arrays.toString(processArr));

        System.out.println(processArr[n]);
    }

    private static void process(int[] processArr){
        for (int i = 2; i < processArr.length; i++) {
            int multi3 = Integer.MAX_VALUE;
            int multi2 = Integer.MAX_VALUE;
            int plus1 = processArr[i-1] + 1;

            if (i % 3 == 0) {
                multi3 = processArr[i/3] + 1;
            }
            if (i % 2 == 0) {
                multi2 = processArr[i/2] + 1;
            }

            processArr[i] = Integer.min(multi3,Integer.min(multi2, plus1));
        }
    }
}
