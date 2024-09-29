package baekjoon.legacy;

import java.util.*;

public class Baek2941 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        List<String> list = List.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");

        for (String key : list) {
            word = word.replace(key, "@");
        }
        System.out.println(word.length());
    }
}
