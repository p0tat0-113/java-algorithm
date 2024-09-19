package leetcode.medium;

import java.util.Arrays;

/*
https://imgur.com/a/6HkTNEt
기초적인 다이나믹 프로그래밍 문제다.
이 문제를 풀기위해서는 이 문제를 작은 문제들로 쪼개야 한다.
[1,2,5]의 숫자들로 11을 만드는 최적의 과정은 0에서 1을 만드는 최적의 과정, 1에서 2를 만드는 최적의 과정, 2에서 3을 만드는 최적의 과정.... 10에서 11을 만드는 최적의 과정의 연속이다.
이 문제의 점화식은 다음과 같다. f(n) = f(n-i) + 1 ex) f(5) = f(5-5) + 1 -> 5를 더해서 5를 만드는 과정은 0을 만드는 과정에 1만큼의 연산을 더한 값이라는 뜻.
그런데 여기에서는 모든 경우에 최적의 경우를 구해야 하므로 이 점화식 연산을 숫자마다 한 번씩 다 돌려서 가장 작은 값을 채택한다.
*/

public class Leet_322CoinChange {
    public static void main(String[] args) {
        Leet_322CoinChange leet = new Leet_322CoinChange();
        System.out.println(leet.coinChange(new int[]{1, 2, 5}, 11));
        //System.out.println(leet.coinChange(new int[]{1}, 2));
    }

    //이렇게 바꾸니까 14ms까지 나옴.
    public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> Integer.MAX_VALUE);//배열을 Integer.MAX_VALUE로 채워서 배열에 계산 결과를 넣을 때 -1인지 따로 확인해야 하는 과정을 생략함.
        processArr[0] = 0;

        for (int coin : coins) {
            for (int i = 1; i < amount+1; i++) {
                if (i-coin < 0 || processArr[i-coin] == Integer.MAX_VALUE) { //(i-coin)이 음수이거나, 뒷 받침해줄 연산 결과가 없는 코인은 패스함.
                    continue;
                }
                processArr[i] = Math.min(processArr[i], processArr[i-coin] + 1);//삼항연산자는 항상 리턴값을 가져야 한다는 점을 명심하자.
            }
        //coins를 순회를 마치면, i에 대한 최적의 답이 processArr[i]에 저장된다.
        }

        return (processArr[amount] != Integer.MAX_VALUE) ? processArr[amount] : -1;
    }

    //조건문을 조금 줄였음에도 성능향상이 없어서 다른 방법을 써야할 것 같다. coins 루프가 바깥으로 오도록 바꿔보는게 좋아보임.
    /*public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> Integer.MAX_VALUE);//배열을 Integer.MAX_VALUE로 채워서 배열에 계산 결과를 넣을 때 -1인지 따로 확인해야 하는 과정을 생략함.
        processArr[0] = 0;

        for (int i = 1; i < amount+1; i++) {
            for (int coin : coins) {
                if (i-coin < 0 || processArr[i-coin] == Integer.MAX_VALUE) { //(i-coin)이 음수이거나, 뒷 받침해줄 연산 결과가 없는 코인은 패스함.
                    continue;
                }
                processArr[i] = Math.min(processArr[i], processArr[i-coin] + 1);//삼항연산자는 항상 리턴값을 가져야 한다는 점을 명심하자.
            }
            //coins를 순회를 마치면, i에 대한 최적의 답이 processArr[i]에 저장된다.
        }

        return (processArr[amount] != Integer.MAX_VALUE) ? processArr[amount] : -1;
    }*/

    /*public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        // Initialize dp array with a large value to signify impossible states
        Arrays.fill(dp, Integer.MAX_VALUE);//Integer.MAX_VALUE는 int형 최대 양수

        dp[0] = 0; // It takes 0 coins to make the amount 0

        // Outer loop for iterating through each coin
        for (int i = 0; i < coins.length; i++) {
            // Inner loop for iterating through each amount starting from coins[i]
            for (int amt = coins[i]; amt <= amount; amt++) {
                if (dp[amt - coins[i]] != Integer.MAX_VALUE) {
                    dp[amt] = Math.min(dp[amt], 1 + dp[amt - coins[i]]);
                }
            }
        }

        // Return the result for the target amount
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }*/

    /*public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> -1);
        processArr[0] = 0;

        int operationResult;
        for (int i = 1; i < amount+1; i++) {
            for (int coin : coins) {
                if (i-coin < 0 || processArr[i-coin] == -1) { //(i-coin)이 음수이거나, 뒷 받침해줄 연산 결과가 없는 코인은 패스함.
                    continue;
                }

                operationResult = processArr[i-coin] + 1;

                if (processArr[i] == -1) {//아직 한 번도 계산되지 않았으면 그냥 바로 대입.
                    processArr[i] = operationResult;
                } else {//다른 코인들로 계산된 결과가 있다면, 더 좋은 경우일 때만 대입함.
                    processArr[i] = Math.min(processArr[i], operationResult);//삼항연산자는 항상 리턴값을 가져야 한다는 점을 명심하자.
                }
            }
            //coins를 순회를 마치면, i에 대한 최적의 답이 processArr[i]에 저장된다.
        }

        return processArr[amount];
    }*/
}
