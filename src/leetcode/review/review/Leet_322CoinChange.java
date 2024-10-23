import java.util.Arrays;

public class Leet_322CoinChange {
    public static void main(String[] args) {
        Leet_322CoinChange leet = new Leet_322CoinChange();
        System.out.println(leet.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(leet.coinChange(new int[]{2}, 3));
        System.out.println(leet.coinChange(new int[]{1}, 0));
    }

    //0에서 amount를 만들어내야 한다.
    public int coinChange(int[] coins, int amount) {
        int[] processArr = new int[amount + 1];
        Arrays.setAll(processArr, e -> Integer.MAX_VALUE);
        processArr[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j < processArr.length; j++) {
                if (j - coins[i] < 0 || processArr[j - coins[i]] == Integer.MAX_VALUE) {
                    continue;
                }
                processArr[j] = Math.min(processArr[j], processArr[j - coins[i]]+1);
            }
        }

        //System.out.println(Arrays.toString(processArr));

        if (processArr[amount] == Integer.MAX_VALUE) {
            return -1;
        }

        return processArr[amount];
    }
}
