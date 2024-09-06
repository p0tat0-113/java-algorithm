package leetcode.medium;

/*
mediun난이도인데 문제가 좀 쉽지 않음...
주사위를 m번 던져서 나온 결과들의 배열과, 결과를 관찰하지 못한 주사위를 던진 횟수 n, 그리고 주사위를 n+m번 던진 평균을 알려준다.
그리고 n번 던졌을 때 어떤 숫자들이 나왔을 지를 배열에 담아서 반환해야함.

가장 간단하게 푸는 방법은 부르트포스인데, 문제는 n이 최대 10^5이다. 6^100000개의 가능성이 있는 것...

가만 생각해보면 1~6까지의 숫자 n개를 합쳐서 원하는 숫자가 나오게 하는게 핵심임. 잘 생각해보면 될 법도 함.
답이 여러가지면, 그중 아무거나 하나 반환하면 된다고 함.

길이가 n인 배열을 전부 3으로 채운 다음에, 총 합이 내가 원하는 숫자보다 크면 빼고, 작으면 더하고, 숫자가 1이나 6에 도달하면 다음 칸으로 넘어가는 식으로 하면 되지 않을까?
아 그리고 중요한 조건이 하나 더 있음. 문제를 푸는 것이 불가능하면 빈 배열을 반환해야 한다.

풀긴 풀었는데 성능이 그리 썩 좋지는 못함. 최적화 할 수 있는 여지가 있음. 그래도 접근 방식자체는 맞는 듯 하다. 다른 빠른 풀이들을 봐도 나랑 접근방식 자체는 같은 듯.
처음 봤을 때는 다이나믹 프로그래밍 같은 것을 써야하는 줄 알고 좀 쫄았다.
*/

import java.util.Arrays;

public class Leet_2028FindMissingObservation {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int sumOfRolls = Arrays.stream(rolls).reduce((n1,n2) -> n1+n2).getAsInt();//rolls의 총합을 구함! 함수형 프로그래밍 시간날 때 복습하자...
        int goalNumber = mean * (rolls.length + n) - sumOfRolls;

        int[] nRolls = new int[n];
        Arrays.setAll(nRolls,e -> 3);//배열의 모든 요소를 3으로 초기화

        int sum = 3*n;
        int position = 0;

        while (sum != goalNumber) {
            if (nRolls[position] == 1 || nRolls[position] == 6) {
                position++;

                //이 조건문이 빠져있어서 [1,2,3,4] 6 4 조건에서 인덱스 예외가 발생했었다.
                if (position >= n) {
                    return new int[] {};
                }
            }

            if (sum < goalNumber) {
                nRolls[position]++;
                sum++;
            } else if (sum > goalNumber) {
                nRolls[position]--;
                sum--;
            }
        }

        return nRolls;
    }

    public static void main(String[] args) {
        Leet_2028FindMissingObservation leet2028FindMissingObservation = new Leet_2028FindMissingObservation();
        System.out.println(Arrays.toString(leet2028FindMissingObservation.missingRolls(new int[]{1,2,3,4}, 6, 4)));
    }
}
