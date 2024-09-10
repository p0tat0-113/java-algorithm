package sorting.basic;

import java.util.Arrays;

/*
* 삽입정렬을 재귀적인 방식으로 구현함.*/

public class InsertionSort_recursion {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        sort(arr,5);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{3,4,1,5,2};
        sort(arr,5);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int n) {
        if (n > 1){//n == 1이면 그대로 종료. 배열의 크기가 1이므로 그대로 두는 것이 정렬된 상태이다.
            sort(arr,n-1);//크기가 하나 작은 문제를 호출, 이 호출이 리턴됐을 때는 arr[0...n-1]은 정렬된 상태이다.

            int newItem = arr[n-1];//비교될 수
            int i;
            for (i = n-1; i > 0; i--) {
                if (newItem > arr[i-1]){//들어갈 자리를 찾으면 탐색을 멈춘다
                    break;
                }
                arr[i] = arr[i-1];//숫자를 뒤로 한 칸씩 민다.
            }
            arr[i] = newItem;
        }
    }
}
