package sorting.basic;

/*
* 선택정렬 방식을 구현한다.
* 얘도 기본적인 아이디어와, 시간복잡도는 똑같다. 다만 가장 큰 값을 찾아서 맨 오른쪽으로 보내는 과정이 좀 다름.
* 10만개의 데이터가 있을 때 버블정렬보다 5배 정도 빠르다고 한다.*/

import java.util.Arrays;

public class SelectionSort implements Sort {

    public static void main(String[] args) {
        int[] arr = {2,4,5,3,1};

        SelectionSort selectionSort = new SelectionSort();
        selectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void sort(int[] arr) {
        for (int i = arr.length-1; i > 0; i--) {//n-1에서 1까지 범위를 좁힘.
            int max = getMax(arr,i);

            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }
    }

    private int getMax(int[] arr, int range){//최대값의 인덱스를 반환.
        int max = 0;
        for (int i = 1; i <= range; i++) {//k개의 숫자 중 최댓값을 찾는 데에는 k-1번이 비교가 필요하다.
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        return max;
    }
}
