package sorting.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 병합 정렬을 구현함.
* 배열을 절반 크기의 두개의 배열로 재귀적으로 쪼개고, 배열의 크기가 1에 도달했을 때부터 작은 배열 조각들을 합지면서 정렬한다.
*
* 병합정렬의 점화식: T(n) = 2T(n/2)+2n
* */

public class MergeSort implements AdvancedSort{
    public static void main(String[] args) throws IOException {
        /*int[] arr = {1,2,3,4,5};
        mergerSort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{3,2,4,5,1};
        mergerSort(arr,0,4);
        System.out.println(Arrays.toString(arr));*/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr, 0, arr.length-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    @Override
    public void sort(int[] arr, int s, int e){
        if (s<e) {//배열의 길이가 1보다 크다면 재귀 호출 반복
            int m = (s+e)/2;//중간 지점의 인덱스를 구함.
            sort(arr,s,m);
            sort(arr,m+1,e);
            merge(arr,s,m,e);
        }
        return;//배열의 길이가 1이하면 그대로 return
    }

    //[2,1,4,3]  0,1,3  0,1-2,3
    private void merge(int[] arr, int s, int m, int e){
        int[] tmp = new int[e-s+1];//정렬을 위한 임시배열
        int left = s;
        int right = m+1;
        int tmp_idx = 0;

        //주배열 arr에서 보조배열인 tmp로 옮기는데 n
        while(left <= m && right <= e){//둘 다 아직 남아있다면, 비교해서 둘 중 작은 것은 tmp에 옮김.
            if (arr[left] < arr[right]){
                tmp[tmp_idx++] = arr[left++];
            } else {
                tmp[tmp_idx++] = arr[right++];
            }
        }
        while(left <= m){//왼쪽만 남아있다면, 비교없이 tmp에 옮김.
            tmp[tmp_idx++] = arr[left++];
        }
        while (right <= e){//오른쪽만 남아있다면, 비교없이 tmp에 옮김.
            tmp[tmp_idx++] = arr[right++];
        }

        //보조배열에서 주배열로 다시 복사하는데 n
        for (int i = s; i <= e; i++) {
            arr[i] = tmp[i-s];
        }
    }
}
