package leetcode.medium;

/*
*/

import java.util.*;
import java.util.stream.Collectors;

public class Leet_179LargestNumber_1 {
    public static void main(String[] args) {
        largestNumber(new int[] {10,2});
        largestNumber(new int[] {3,30,34,5,9});
    }

    public static String largestNumber(int[] nums) {
        // 숫자들을 문자열로 변환
        String[] numStrs = new String[nums.length];
        for(int i=0; i<nums.length; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }

        // 커스텀 정렬 기준 정의
        Arrays.sort(numStrs, new Comparator<String>() {
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1); // 내림차순 정렬
            }
        });

        // 모든 숫자가 0인 경우 "0" 반환
        if(numStrs[0].equals("0")) {
            return "0";
        }

        // 정렬된 숫자들을 이어붙임
        StringBuilder sb = new StringBuilder();
        for(String num : numStrs) {
            sb.append(num);
        }

        return sb.toString();
    }
}
