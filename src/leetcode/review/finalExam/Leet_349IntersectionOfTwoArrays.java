package leetcode.review.finalExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Leet_349IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Leet_349IntersectionOfTwoArrays leet = new Leet_349IntersectionOfTwoArrays();
        System.out.println(Arrays.toString(leet.intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(leet.intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4})));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (Integer integer : set1) {
            if (set2.contains(integer)) {
                list.add(integer);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
