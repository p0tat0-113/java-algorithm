package baekjoon.legacy;/*문제
N×N의 표에 수 N2개 채워져 있다. 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다. N=5일 때의 예를 보자.

12	7	9	15	5
13	8	11	19	6
21	10	26	31	16
48	14	28	35	25
52	20	32	41	49
이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오. 표에 채워진 수는 모두 다르다.

입력
첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다. 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다. 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.

출력
첫째 줄에 N번째 큰 수를 출력한다.
*/

//입력으로 들어오는 테이블이 되게 독특하다. 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것, 즉 각 열을 이미 정렬된 상태라는 것이다.
//이 특성을 이용하면 효율적으로 정렬할 수 있을 듯. 재귀적으로 병합하면 될 듯 함. 우선순위큐를 쓰면 그냥 날먹 할 수 있을 것 같기도 한데...
//풀긴 풀었는데 뭔가 찝찝하네..

//개같이 시간 초과됨. 오버헤드를 줄여야 함. 스위칭 병합 정렬같이 주배열과 보조배열을 바꿔가면서 하면 되지 않을까...?
//생각해보니 애초에 비교횟수를 최대한 줄여야 하는데.. 지금의 방법은 잘못됐음.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek2075_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        int[] arr = new int[num*num];

        for (int i = 0; i < num; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

            for (int j = i; j < num*num; j = j+5) {
                arr[j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        System.out.println(Arrays.toString(arr));

        split(arr,0,arr.length-1,num);
        System.out.println(Arrays.toString(arr));;
    }

    private static void split(int[] arr, int start, int end, int depth){
        if (depth > 0) {
            int mid = start + depth/2*5;

            split(arr, start, mid-1, depth/2);
            split(arr, mid, end, depth/2);

            merge(arr,start,mid,end);
        } else {
            return;
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end-start+1];
        int tempIdx = 0;

        int left = start;
        int right = mid;
        //(end-start)/2+1

        while(left < mid && right < end+1){
            if(arr[left] > arr[right]) {
                temp[tempIdx++] = arr[left++];
            } else {
                temp[tempIdx++] = arr[right++];
            }
        }
        while (left < mid) {
            temp[tempIdx++] = arr[left++];
        }
        while (right < end+1) {
            temp[tempIdx++] = arr[right++];
        }

        for (int i = 0; i < tempIdx; i++) {
            arr[start+i] = temp[i];
        }
    }
}
