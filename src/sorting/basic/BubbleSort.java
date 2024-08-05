package sorting.basic;

/*
* 이 클래스에서는 가장 비효율적인 정렬방식인 버블 정렬을 구현한다.
* 배열의 크기를 n이라고 할 때 범위를 n-1에서 1까지 줄여가며
* 그 중 제일 큰 값을 찾아 맨 오른쪽으로 보내는 방식으로 정렬한다.
* */

import java.util.Arrays;

public class BubbleSort implements Sort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();

        int[] arr = {3,4,5,2,1};
        bubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Override
    public void sort(int[] arr){
        for (int i = arr.length-1; i > 0; i--) {//범위를 n-1에서 1까지 줄여나감.

            boolean sorted = true;

            for (int j = 0; j < i; j++) {//해당 범위를 움직이면서 두 값을 비교, 왼쪽 값이 더 크면 위치를 맞바꿈. 결국 매 반복마다 가장 큰 값이 맨 오른쪽으로 가게 됨.

                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    sorted = false;
                }
            }

            if (sorted) {//만약 앞의 반복문에서 값의 위치를 한 번도 바꾸지 않았다면(정렬된 상태라면), sorted가 true인 상태로 남아있음. 바로 return. 최선의 경우 O(n)으로 끝낼 수도 있음.
                return;
            }
        }
    }
}
