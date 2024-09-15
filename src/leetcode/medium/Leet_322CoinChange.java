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

    public int coinChange(int[] coins, int amount) {
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
    }

    /*public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> -1);
        processArr[0] = 0;

        for (int i = 1; i < amount+1; i++) {
            for (int coin : coins) {
                if (i-coin < 0) {
                    continue;
                }

                if (processArr[i] == -1) {//아직 한 번도 계산되지 않음.
                    processArr[i] = processArr[i-coin] + 1;
                } else {
                    processArr[i] = (processArr[i] > (processArr[i-coin] + 1)) ? (processArr[i-coin] + 1) : processArr[i];//삼항연산자는 항상 리턴값을 가져야 한다는 점을 명심하자.
                }
            }
        }

        return processArr[amount];
    }*/
}
