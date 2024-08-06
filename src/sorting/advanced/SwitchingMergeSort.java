package sorting.advanced;

/*
* 스위칭 병합 정렬
* 주배열에서 보조배열로 복사하며 정렬, 정렬된 보조배열을 다시 주배열로 복사하는 과정을 제거하고 주배열과 보조배열을 스위칭 하는 방식으로 효율성을 높임.
*
* 스위칭 병합 정렬의 점화식: T(n) = 2T(n/2)+n 병합정렬과 점근적 복잡도는 nlogn 으로 같지만 그래도 조금 더 빠르다.
* */

import sorting.GenerateArr;
import sorting.basic.PerformanceTest;

import java.io.IOException;
import java.util.Arrays;

public class SwitchingMergeSort implements AdvancedSort{
    public static void main(String[] args) throws IOException {
        SwitchingMergeSort switchingMergeSort = new SwitchingMergeSort();

        int[] arr = {1,2,3,4,5};
        switchingMergeSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2,1,3,5,4};
        switchingMergeSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = GenerateArr.generateRandomArr(100);
        System.out.println(Arrays.toString(arr));
        switchingMergeSort.sort(arr,0,99);
        System.out.println(Arrays.toString(arr));

        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        switchingMergeSort.sort(arr, 0, arr.length-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);*/
    }

    @Override
    public void sort(int[] arrA, int s, int e){
        int[] arrB = Arrays.copyOf(arrA,arrA.length);//복사해서 똑같은 보조배열을 하나 만들어준다.
        switchMs(arrA,arrB,s,e);
    }

    //주배열과 보조배열의 역할을 바꿔가면서 merge 한다.
    private void switchMs(int[] arrA, int[] arrB, int s, int e){
        if (s < e){
            int m = (s+e)/2;
            switchMs(arrB, arrA, s, m);
            switchMs(arrB, arrA, m+1, e);
            merge(arrB,arrA,s,m,e);
        }
        return;
    }

    private void merge(int[] arrC, int[] arrD, int s, int m, int e){ //c를 주, d를 보조 역할로 사용
        int left = s;
        int right = m+1;
        int count = s;

        while(left <= m && right <= e){
            if (arrC[left] < arrC[right]){
                arrD[count++] = arrC[left++];
            } else {
                arrD[count++] = arrC[right++];
            }
        }
        while (left <= m){
            arrD[count++] = arrC[left++];
        }
        while (right <= e){
            arrD[count++] = arrC[right++];
        }
    }
}
