package easyAlgorithm.selection;

import java.util.Random;

public class select_useQuickSortMethod {
    public static void main(String[] args) {
        int[] arr = {5,2,3,4,1};
        int i = 4;

        Random random = new Random();
        System.out.println(select(arr, 0, arr.length-1, i, random));//arr.length-1로 하니까 가장 큰 숫자를 고르는 경우가 안됨.
    }

    //책에 나온 그대로 좀 더 세련되게 만들어봤다.
    private static int select(int[] arr, int p, int r, int i, Random random){
        if (p == r) {
            return arr[p];
        }
        //pivot을 랜덤으로 선정
        int randomIdx = random.nextInt(p,r+1);
        int temp = arr[randomIdx];
        arr[randomIdx] = arr[p];
        arr[p] = temp;

        int q = partition(arr,p,r);
        int k = q-p+1;//k는 pivot이 부분배열 내에서 몇 번째로 작은 수인지를 나타낸다. k는 부분배열 내에서 pivot이 상대적으로 몇번째 작은 수인지를 나타내는 것 뿐이므로, 재귀호출시 인덱스 범위는 당연히 q를 사용해서 나타내야 한다.
        if (i < k) {//이 경우 왼쪽 그룹을 뒤져봐야 함.
            return select(arr,p,q-1, i, random);//찾고자 하는 수는 왼쪽 그룹 안에서 여전히 i번째로 작은 수다.
        } else if (i == k) {
            return arr[q];
        } else {//이 경우 오른족 그룹을 뒤져봐야 함.
            return select(arr,q+1, r, i-k, random);//찾고자 하는 수는 오른쪽 그룹 안에서 i-k번째로 작은 수다.
        }
    }

    /*private static int select(int[] arr, int s, int e, int i, Random random){//i번째로 작은 수의 인덱스를 반환함. 없으면 -1을 반환한다.
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
            } else {
                return select(arr,pivot+1, e, i, random);
            }
        }
        return -1;
    }*/

    private static int partition(int[] arr, int s, int e){//기준원소를 알맞은 위치로 옮기고, 그 인덱스를 반환한다.
        int pivot = arr[s];//첫번째 원소를 pivot으로 선택한다.
        //이번에는 책에 나온 1구역, 2구역, 3구역 방식을 사용해보자.
        int first = s;
        int second = s+1;
        while(second <= e) {
            if (pivot > arr[second] || (pivot == arr[second] && second%2 == 0)){//pivot과 같은 값이 있을 때 양쪽으로 균등하게 분배하는 부분의 코드도 중복을 제거함.
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
