package baekjoon.dynamicProgramming;

/*
n번째 피보나치 수를 구하는 문제를 재귀호출 방식과 DP방식으로 각각 구현해보고, 각각의 재귀호출 횟수를 반환하는 문제다.
쉽게 배우는 알고리즘 책에서도 봤지만, 피보나치수를 구하는 문제는 DP로 재귀호출 방식의 중복 호출 문제를 해결하는 예시를 보여준다.
n의 범위는 5~40이다.

문제가 좀 이상해서 그냥 재귀호출과 DP의 실행속도를 측정하는 코드를 짰다.
n = 40일 때 재귀호출은 522ms, DP는 0ms다.
*/

import java.util.Arrays;
import java.util.Scanner;

public class Baek_24416FibonacciDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        long start = System.currentTimeMillis();

        System.out.println(fib(num));

        long end = System.currentTimeMillis();
        System.out.println(end-start + "ms");


        start = System.currentTimeMillis();

        int[] arr = new int[num + 1];
        Arrays.setAll(arr, e -> -1);
        arr[0] = 0;
        arr[1] = 1;
        System.out.println(fibWithDP(num, arr));

        end = System.currentTimeMillis();
        System.out.println(end-start + "ms");
    }


    //재귀호출
    private static int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    //DP
    private static int fibWithDP(int n, int[] arr) {
        if (arr[n] != -1) {
            return arr[n];
        }
        arr[n] = fibWithDP(n-1, arr) + fibWithDP(n-2, arr);
        return arr[n];
    }
}
