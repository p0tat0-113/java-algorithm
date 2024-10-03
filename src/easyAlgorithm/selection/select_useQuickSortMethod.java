package easyAlgorithm.selection;

import java.util.Random;

public class select_useQuickSortMethod {
    public static void main(String[] args) {
        int[] arr = {5,2,3,4,1};
        int i = 5;

        Random random = new Random();
        System.out.println(select(arr, 0, arr.length-1, i-1, random));//arr.length-1로 하니까 가장 큰 숫자를 고르는 경우가 안됨.
    }

    private static int select(int[] arr, int s, int e, int i, Random random){//i번째로 작은 수의 인덱스를 반환함. 없으면 -1을 반환한다.
        if (s < e) {
            //pivot을 랜덤으로 뽑아서 partition의 성능을 향상시킨다.
            int randomIdx = random.nextInt(s,e+1);
            int temp = arr[randomIdx];
            arr[randomIdx] = arr[s];
            arr[s] = temp;

            int pivot = partition(arr,s,e);
            if (i < pivot) {
                return select(arr,s,pivot-1, i, random);
            } else if (i == pivot) {//i == pivot를 먼저 검사하게 하니까 되네.
                return arr[pivot];
            } else {//i == pivot인 경우, i번째로 작은 수를 찾았음.
                return select(arr,pivot+1, e, i, random);
            }
        }
        return -1;
    }

    private static int partition(int[] arr, int s, int e){//기준원소를 알맞은 위치로 옮기고, 그 인덱스를 반환한다.
        int pivot = arr[s];//첫번째 원소를 pivot으로 선택한다.
        //이번에는 책에 나온 1구역, 2구역, 3구역 방식을 사용해보자.
        int first = s;
        int second = s+1;
        while(second <= e) {//second <= e로 하니까 가장 큰 숫자를 고르는 경우가 안됨.
            if (pivot > arr[second]){
                first++;
                int temp = arr[second];
                arr[second] = arr[first];
                arr[first] = temp;
            } else if (pivot == arr[second] && second%2 == 0) {//중복된 요소가 많은 경우에 균등하게 분배되게 해서 성능을 개선한다.
                first++;
                int temp = arr[second];
                arr[second] = arr[first];
                arr[first] = temp;
            }
            second++;
        }

        arr[s] = arr[first];
        arr[first] = pivot;

        return first;
    }
}
