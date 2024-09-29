package baekjoon.legacy;/*
문제
접미사 배열은 문자열 S의 모든 접미사를 사전순으로 정렬해 놓은 배열이다.
baekjoon의 접미사는 baekjoon, aekjoon, ekjoon, kjoon, joon, oon, on, n 으로 총 8가지가 있고, 이를 사전순으로 정렬하면, aekjoon, baekjoon, ekjoon, joon, kjoon, n, on, oon이 된다.
문자열 S가 주어졌을 때, 모든 접미사를 사전순으로 정렬한 다음 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000보다 작거나 같다.

출력
첫째 줄부터 S의 접미사를 사전순으로 한 줄에 하나씩 출력한다.
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Baek11656 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputStr = new StringBuilder(scanner.nextLine());

        int length = inputStr.length();

        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = inputStr.toString();
            inputStr.deleteCharAt(0);
        }

        //System.out.println(Arrays.toString(arr));
        quickSort(arr,0,length-1);
        //System.out.println(Arrays.toString(arr));

        StringBuilder result = new StringBuilder();
        for (String string : arr) {
            result.append(string).append("\n");
        }
        System.out.println(result);
    }

    private static Random random = new Random();

    //퀵소트로 정렬하려고 함.
    //[1,2,3,4,5] 0,4
    private static void quickSort(String[] arr, int start, int end) {
        if (start < end){//start와 end가 같아지면 재귀 호출 종료, 여기에 if문이 아닌 while을 써서 한참 고민했다. 멍청했다....
            int randomIdx = start + random.nextInt(end-start+1);//랜덤하게 하나 뽑아서 Pivot이 될 맨 앞자리와 교환, 정렬된 배열이 들어오는 최악의 경우에도 배열이 균등하게 분할되게 하기 위함이다.
            String temp = arr[start];
            arr[start] = arr[randomIdx];
            arr[randomIdx] = temp;

            int mid = partition(arr,start,end);//partition함수의 결과로 분할의 기준이 되는 인덱스 값을 반환받는다.

            quickSort(arr,start,mid-1);
            quickSort(arr,mid+1,end);
        }

    }

    //[3,1,2,4,5]
    private static int partition(String[] arr, int start, int end){
        String pivot = arr[start];
        int Low = start+1;
        int High = end;

        while(Low <= High){//Low와 High가 교차되면 종료함.
            if (arr[Low].compareTo(pivot) < 0){
                Low++;
            } else if (arr[High].compareTo(pivot) > 0) {
                High--;
            } else {//Low와 High둘 다 움직이지 못한다면, 교환 후 각각 전진
                String temp = arr[Low];
                arr[Low] = arr[High];
                arr[High] = temp;
                Low++;
                High--;
            }
        }

        //High와 Pivot을 교환
        String temp = arr[High];
        arr[High] = pivot;
        arr[start] = temp;
        //Pivot은 이미 알맞은 위치를 찾았다.

        return High;
    }
}
