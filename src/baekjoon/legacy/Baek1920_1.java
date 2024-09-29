package baekjoon.legacy;

import java.io.*;
import java.util.HashSet;
import java.util.List;

public class Baek1920_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;

        int n = Integer.valueOf(br.readLine());
        input = br.readLine();
        List<String> listN = List.of(input.split(" "));

        int m = Integer.valueOf(br.readLine());
        input = br.readLine();
        List<String> listM = List.of(input.split(" "));

        HashSet<String> set = new HashSet<String>(listN);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (String string : listM) {
            if (set.contains(string)){
                bw.write("1");
            } else {
                bw.write("0");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
