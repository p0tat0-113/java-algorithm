package sorting.basic;

/*
* 삽입 정렬 구현
* 버블, 선택 정렬과는 반대의 발상*/

import java.util.Arrays;

public class InsertionSort implements Sort {

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();

        int[] arr = {1,2,3,4,5};
        insertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{3,3,5,2,1};
        insertionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {

            int newItem = arr[i];
            int j;
            for (j = i; j > 0; j--) {
                if (newItem >= arr[j-1]){//삽입하는 배열이 이미 다 정렬된 상태라는 전재가 깔려있기 때문에 가능한 것.
                    break;
                }
                arr[j] = arr[j-1];//오른쪽으로 한 칸 밀기.
            }
            arr[j] = newItem;
        }
    }
}
