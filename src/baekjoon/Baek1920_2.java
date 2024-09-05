package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Baek1920_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(br.readLine());
        String[] arrN = br.readLine().split(" ");

        int m = Integer.valueOf(br.readLine());
        String[] arrM = br.readLine().split(" ");

        Arrays.sort(arrN);

        StringBuilder sb = new StringBuilder();
        for (String string : arrM) {
            sb.append(binarySearch(arrN,string)+"\n");
        }
        System.out.println(sb);

    }

    //[1,2,3,4,5,6,7,8,9]
    private static int binarySearch(String[] arr, String search){
        int start = 0;
        int end = arr.length-1;
        int mid = (end+start)/2;

        while (end-start >= 0){
            //System.out.println(start+"|"+mid+"|"+end);
            if(arr[mid].compareTo(search) < 0){//arr[mid] < search
                start = mid+1;
                mid = (start+end)/2;
            } else if (arr[mid].compareTo(search) > 0) {//arr[mid] > search
                end = mid-1;
                mid = (start+end)/2;
            } else {//arr[mid]와 search가 같음.
                return 1;
            }
        }
        return 0;
    }
}
