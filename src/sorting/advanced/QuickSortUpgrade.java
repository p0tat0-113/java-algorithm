package sorting.advanced;

/*
* 앞에서 언급한 약점들, 정렬된 배열, 중복된 요소들이 있는 배열이 들어오는 경우를 처리해줘야 한다.
*
* 일단 정렬된 배열이 들어오는 경우에 대해서는 Pivot을 배열의 맨 앞이나 뒤로 고정적으로 정하지 말고, 랜덤으로 정하면 된다.
* 이렇게 하면 확률적으로 대부분 균등하게 분할된다고 한다.
*
* 그리고 중복된 요소들이 있는 배열이 들어오는 경우, 기존의 코드에서는 Pivot과 숫자가 같으면 그냥 2구역에 두는 식이었는데 이러면 중복된 숫자들이 뭉치게 되고,
* 그룹이 균등하게 분할되지 못하면서 알고리즘이 계속 깊이 파고들게 된다.
* 이런 상황을 막기 위해 한번은 1구역으로 던지고, 한 번은 2구역에 두는 식으로 구역이 최대한 균등하게 분할되게 하면 된다.
* 근데 이렇게 하더라도 아까 내가 테스트 하던 것처럼 모든 숫자들이 전부 중복된 상황은 좀 에바인 듯.... <- 아님.
* 결국 좌우로 균등하게 분할하기 때문에 이런 극한의 상황에서도 문제없음.*/

import sorting.GenerateArr;

import java.util.Arrays;
import java.util.Random;

public class QuickSortUpgrade implements AdvancedSort{
    public static void main(String[] args) {
        QuickSortUpgrade quickSort = new QuickSortUpgrade();

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

    private Random random = new Random();

    @Override
    public void sort(int[] arr, int s, int e) {
        if (s < e){
            //랜덤한 인덱스의 숫자가 Pivot이 되게 함.
            int randomIndex = random.nextInt(e-s)+s;

            int temp = arr[e];
            arr[e] = arr[randomIndex];
            arr[randomIndex] = temp;

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
            if (arr[j] < pivot){//숫자가 privot보다 1구역을 한 칸 확장하고, 확장된 칸에 있던 숫자와 교환한다.
                int temp = arr[j];
                arr[j] = arr[++i];
                arr[i] = temp;
            } else if (arr[j] == pivot && j%2 == 1) {//Pivot과 같은 숫자는 균등하게 왼쪽 오른쪽 나눠서 저장함.
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
