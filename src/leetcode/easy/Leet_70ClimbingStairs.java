package leetcode.easy;

/*
n개의 계단을 한걸음 혹은 두걸음씩 오른다고 할 때 계단을 오를 수 있는 경우의 수가 몇 개가 있는지 구하는 문제다.
DP는 아닌 것 같고, 백트래킹 DFS문제인 것 같은데... 아니네. 토픽을 보니까 DP문제다.

1계단 오를 때의 경우의 수를 기록해두고, 다시 그걸 기반으로 2계단 오를 때의 경우의 수를 기록하는게 아닌가 싶다. <- 이건 아님.

그림을 그리다 보니까 규칙을 찾았디. 이거 피보나치랑 비슷하다.

풀이과정: https://imgur.com/a/a0itutS
*/

public class Leet_70ClimbingStairs {
    public static void main(String[] args) {

    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 2;

        return similarWithFibonacci(n, arr);
    }

    //피보나치수를 DP 바텀-업으로 구하는 방식과 거의 비슷하다.
    private int similarWithFibonacci(int n, int[] arr) {
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }

        return arr[arr.length-1];
    }
}
