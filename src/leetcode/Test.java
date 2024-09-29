package leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        HashSet<List<Integer>> set = new HashSet<>();

        set.add(List.of(1,2,3));
        set.add(List.of(3,1,2));

        System.out.println(set);

        System.out.println(getDigit(10));
    }

    private static int getDigit(int num){
        int digit = 0;
        do {
            num = num/10;
            digit++;
        } while (num != 0);

        return digit;
    }
}
