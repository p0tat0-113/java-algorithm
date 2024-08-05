package sorting.advanced;

/*
* 퀵 정렬을 일단 내 나름대로 구현을 해보려고 함... 잘 될지는 모르겠네.
* 어찌어찌 하다보니 잘 구현됨! 속도도 개빨라! 자바가 제공하는 sort()보다도 더 빠름!!!!!!
* */

import sorting.basic.PerformanceTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MyQuickSort implements AdvancedSort{
    public static void main(String[] args) throws IOException {
        MyQuickSort myQuickSort = new MyQuickSort();

        int[] arr = {1,2,3,4,5};
        myQuickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2,4,5,3,1};
        myQuickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = PerformanceTest.generateArr(100);
        System.out.println(Arrays.toString(arr));
        myQuickSort.sort(arr,0,99);
        System.out.println(Arrays.toString(arr));

        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        myQuickSort.sort(arr, 0, arr.length-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);*/
    }

    @Override
    public void sort(int[] arr, int s, int e){
        int m = process(arr,s,e);
        //System.out.println(m);
        //System.out.println(Arrays.toString(arr));
        if (m-s > 1){//배열의 길이가 적어도 2이상은 되어야 한다는 것.
            sort(arr,s,m-1);
        }
        if (e-m > 1) {
            sort(arr,m+1, e);
        }
    }

    private int process(int[] arr, int s, int e){
        int pivot = s;
        int low = pivot+1;
        int high = e;

        while (low<=high){
            if (arr[low] < arr[pivot]){
                low++;
            }
            else if (arr[high] > arr[pivot]){
                high--;
            } else {//둘 다 움직이지 못하는 상태면 값을 맞바꾸고, 둘 다 한 칸 씩 움직여줌.
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;

                low++;
                high--;
            }
        }

        //[1,2,3,4,5]이런 상황에서도 high가 pivot과 같아지면서 자연스럽게 그대로 유지됨.
        int temp = arr[pivot];
        arr[pivot] = arr[high];
        arr[high] = temp;

        return high;
    }
}
