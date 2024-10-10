package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/*
https://imgur.com/a/TMasTsd
이 문제는 이런 아이디어로 풀었다. DP 메모이제이션이라고 쫄지 말자.
결국 핵심은 이미 한 번 계산해둔 것은 기록해두고 다시 하지 말자라는 것이다... 아마도?
*/

public class Leet_118PascalsTriangle {
    public static void main(String[] args) {
        System.out.println("generate(5) = " + generate(5));
        System.out.println("generate(1) = " + generate(1));
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of(1));

        for (int i = 1; i < numRows; i++) {//바깥리스트 반복문, 두번째 리스트부터
            result.add(new ArrayList<>(List.of(1,1)));
            for (int j = 1; j < i; j++) {//안쪽 리스트 반복문, 인데스 1부터 리스트의 맨 끝 인덱스-1까지 반복. 양끝의 1제외. i는 이번 리스트의 길이보다 1작기 때문에 이렇게 하면 된다.
                int plus = result.get(i-1).get(j-1) + result.get(i-1).get(j);
                result.get(i).add(j, plus);//계산 결과를 해당 인덱스에 삽입
            }
        }

        return result;
    }
}
