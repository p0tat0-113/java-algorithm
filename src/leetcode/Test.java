package leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashSet<List<Integer>> set = new HashSet<>();

        set.add(List.of(1,2,3));
        set.add(List.of(3,1,2));

        System.out.println(set);
    }
}
