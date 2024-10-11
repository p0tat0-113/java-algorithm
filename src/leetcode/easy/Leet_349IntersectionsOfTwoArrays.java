package leetcode.easy;

/*
두 int형 배열이 주어지면 이걸 교집합 하는 문제다.
자바 set을 쓰면 참 쉬운 문제인데....
날먹을 해도 되나...? 일단 set은 쓰고 교집합 연산만 내가 직접 하자.
*/

import java.util.ArrayList;
import java.util.HashSet;

public class Leet_349IntersectionsOfTwoArrays {
    public static void main(String[] args) {

    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (Integer num : set1) {
            if (set2.contains(num)) {
                result.add(num);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
