package leetcode.review;
/*
DP 메모이제이션
*/

import java.util.Arrays;

public class Leet_322CoinChange {
    public static void main(String[] args) {
        Leet_322CoinChange leet = new Leet_322CoinChange();
        System.out.println(leet.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(leet.coinChange(new int[]{2}, 3));
        System.out.println(leet.coinChange(new int[]{1}, 0));
    }

    public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> Integer.MAX_VALUE);
        processArr[0] = 0;

        for (int i = 1; i < processArr.length; i++) {
            for (int coin : coins) {
                if (i-coin >= 0 && processArr[i-coin] != Integer.MAX_VALUE) {//i-coin이 음수가 아니고, 지지해줄 이전 계산기록이 있으면
                    //점화식 T(n) = T(n-i) + 1
                    processArr[i] = Integer.min(processArr[i], processArr[i-coin]+1);
                }
            }
        }

        if (processArr[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return processArr[amount];
    }
}
