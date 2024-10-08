package leetcode.medium;

/*
어떻게든 풀어보려고 하다가 결국 solution을 보고 풀었다.
당장 눈 앞의 상황에서 최적의 선택을 한다는 그리디 알고리즘의 기본 원리(?)에 정말 충실한 풀이방식 같은데 이런 식으로 풀린다는게 신기하네.
*/

import java.util.*;

public class Leet_179LargestNumber_1 {
    public static void main(String[] args) {
        largestNumber(new int[] {10,2});
        largestNumber(new int[] {3,30,34,5,9});
    }

    public static String largestNumber(int[] nums) {
        //우선 숫자 조합에 따른 대소를 쉽게 비교하기 위해 숫자를 전부 문자열로 바꿔준다.
        String[] numStrs = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }

        //이 부분이 중요하다. 문자열 배열을 돌리는데, 정렬의 기준으로 문자열 a,b를 ab ba로 합쳐서 둘의 대소를 비교한다.
        //일반적으로는 숫자를 문자열로 저장해서 대소를 비교하는 것은 불가능하지만 지금 ab, ba의 길이가 같기 때문에 그냥 사전순으로 비교가 되는 것. tmi지만 이렇게 문자열로 저장된 숫자의 크기를 간단하게 비교하는 아이디어는 1985번 문제를 풀 떄 잘 써먹었다.
        //아무튼 정렬 알고리즘이 돌아가면서 내부적으로 이 comparator를 사용해 ab, ba중 더 큰 방식으로 a,b를 정렬한다. <- 이게 바로 눈 앞의 상황에서 최적의 판단을 하는 것이다.
        //이런 과정을 반복하면서 결국 결과에 도달하는 것이다.
        //뭔가 감각적으로는 이해가 되긴 하는데.... 쉽지 않네.
        Arrays.sort(numStrs, new Comparator<String>() {
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1); // 내림차순 정렬
            }
        });

        //모든 숫자가 0인 경우 "0" 반환, 마지막에 "0000000000000"같이 출력되는 것을 방지한다.
        if(numStrs[0].equals("0")) {
            return "0";
        }

        //정렬된 숫자들을 이어붙임
        StringBuilder sb = new StringBuilder();
        for(String num : numStrs) {
            sb.append(num);
        }

        return sb.toString();
    }
}
