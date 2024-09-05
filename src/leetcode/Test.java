package leetcode;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }

    public static int romanToInt(String s) {
        StringBuilder sb = new StringBuilder(s);

        Map<String, Integer> map1 =
                Map.of("IV",4,"IX",9,"XL",40,"XC",90,"CD",400,"CM",900);

        Map<String, Integer> map2 =
                Map.of("I",1,"V",5,"X",10,"L",50,"C",100,"D",500,"M",1000);

        int sum = 0;

        if (sb.length() > 1){
            for (int i = 0; i < sb.length()-1; i++) {
                String string = sb.substring(i,i+2);
                if (map1.containsKey(string)) {
                    sum += map1.get(string);
                    sb.replace(i,i+2,"!!");
                }
            }
        }

        System.out.println(sb);

        for (int i = 0; i < sb.length(); i++) {
            sum += map2.getOrDefault(sb.substring(i,i+1), 0);
        }

        return sum;
    }
}
