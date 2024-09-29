package baekjoon.legacy;/*문제
온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다. 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다. 입력은 가입한 순서로 주어진다.

출력
첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.*/


//앞서 만든 코드처럼 굳이 따로 내부 클래스를 만들어서 나이와 우선순위를 가지고 정렬하지 않고, 그냥 안정정렬 알고리즘으로 한 큐에 끝내는 식으로 고치려고 함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek10814_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        String[][] arr = new String[num][2];

        for (int i = 0; i < num; i++) {
            arr[i] = br.readLine().split(" ");
        }

        mergeSort(arr,0,arr.length-1);

        StringBuilder sb = new StringBuilder();
        for (String[] strings : arr) {
            sb.append(strings[0]).append(" ").append(strings[1]).append("\n");
        }
        System.out.println(sb);
    }

    //정렬에는 안정정렬이면서 속도도 빠른 스위칭 병합 정렬을 사용 <-- 이걸 쓰니까 메모리 초과가 떠버림..... <-- 그래서 그냥 병합 정렬을 들고 왔는데 얘도 오류가 남...
    private static void mergeSort(String[][] arr, int start, int end){
        if (start < end){
            int mid = (end-start)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);
            merge(arr,start,end);
        }
    }

    private static void merge(String[][] arr, int start, int end){
        String[][] tempArr = new String[end-start+1][2];
        int tempIdx = 0;
        int mid = (end-start)/2;
        int left = start;
        int right = mid+1;

        while(left <= mid && right <= end){//아직 둘 다 소모되지 않은 상황
            if(arr[left][0].compareTo(arr[right][0]) <= 0) {//arrC[left][0]가 더 작거나 같은 경우, 안정정렬이기 위해서 나이가 같은 경우 왼쪽부터 들어가야 함.
                tempArr[tempIdx++] = arr[left++];
            } else {
                tempArr[tempIdx++] = arr[right++];
            }
        }

        while (left <= mid) {
            tempArr[tempIdx++] = arr[left++];
        }
        while (right <= end) {
            tempArr[tempIdx++] = arr[right++];
        }

        System.arraycopy(tempArr,0,arr,start,(end-start+1));
    }
}
