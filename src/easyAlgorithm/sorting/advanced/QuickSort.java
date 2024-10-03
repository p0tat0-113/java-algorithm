package easyAlgorithm.sorting.advanced;

/*
* 퀵 정렬을 이번에는 책에 나온 방식대로 구현해 본다.
* 앞에서 내가 직접 구현해본 것과 원리 자체는 똑같음. 다만 기준원소(Pivot)를 중심으로 작은 숫자를 왼쪽,
* 큰 숫자를 오른쪽으로 옮기는 방식이 약간 다를 뿐이다. 너무 비효율적인 방식만 아니라면 어떤 방식으로 하던 간에 상관 없다고 한다.
*
* 책에 나온 방법은 맨 오른쪽에 있는 원소를 Pivot으로 정하고
* Pivot보다 작은 숫자들을 1구역, 크거나 같은 숫자들을 2구역, 아직 확인하지 않은 숫자들을 3구역을 나눈다.
* 1구역과 2구역의 크기가 최대한 균등하게 나누어져야지만 효율적으로 정렬할 수 있다.
*
* 일단 작동은 잘 되고, 대부분의 경우에 매우 빠른 속도로 정렬함. 그런데 퀵 정렬에도 약점이 있다.
* 매우 드문 경우이긴 하지만 최악의 경우로 정렬된 배열, 혹은 거의 정렬된 배열이 입력되면 n^2의 시간복잡도를 보인다.
* 또한 배열에 중복이 많아도 구역이 불균등 하게 분할되면서 성능이 떨어진다.
*
* 실제로 성능 테스트를 해보니까 QuickSort의 경우 정렬되고, 중복된 길이 100_000의 배열을 넣으니까 구역을 균등하게 나누지 못하고 깊이가 너무 깊어져서 그런지 스택 오버 플로우가 발생함.
* 근데 신기하게도 MyQuickSort는 이런 문제가 발생하지 않음. 그냥 개빠름. 아니네 뭐지 다시 테스트 해보니까 느리고 스택 오버플로우 터지네.
*
* 그런데 이 약점들 또한 보완할 수 있는 방법이 있음.*/

import easyAlgorithm.sorting.GenerateArr;
import java.util.Arrays;

public class QuickSort implements AdvancedSort{
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        int[] arr = {1,2,3,4,5};
        quickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2,4,5,1,3};
        quickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = GenerateArr.generateRandomArr(100);
        System.out.println(Arrays.toString(arr));
        quickSort.sort(arr,0,99);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void sort(int[] arr, int s, int e) {
        if (s < e){
            int pivot = partition(arr,s,e);
            sort(arr,s,pivot-1);
            sort(arr,pivot+1,e);
        }
    }

    //  s       e
    // [2,4,5,1,3]
    //i j
    private int partition(int[] arr, int s, int e){
        int pivot = arr[e];//맨 오른쪽 원소를 Pivot으로 잡았음.
        int i = s-1;//i는 1구역 맨 끝 인덱스
        int j = s;//j는 2구역 맨 처음 인덱스

        for (int k = j; k < e; k++) {
            if (arr[j] < pivot){//숫자가 privot보다 작은 경우 1구역을 한 칸 확장하고, 확장된 칸에 있던 숫자 pivot보다 크거나 같은 숫자와 교환한다. <- 이 숫자는 2구역의 맨 끝으로 가게 되는 것.
                int temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            }
            j++;
        }

        //탐색과 교환이 다 끝나면 privot과 arr[i+1]을 교환한다. 그리고 privot의 현재 인덱스를 반환함.
        arr[e] = arr[i+1];//탐색과 교환이 다 끝나면
        arr[i+1] = pivot;
        return i+1;
    }

}
