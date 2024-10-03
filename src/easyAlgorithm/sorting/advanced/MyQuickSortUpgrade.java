package easyAlgorithm.sorting.advanced;

/*
* 퀵 정렬을 일단 내 나름대로 구현을 해보려고 함... 잘 될지는 모르겠네.
* 어찌어찌 하다보니 잘 구현됨! 속도도 개빨라! 자바가 제공하는 sort()보다도 더 빠름!!!!!!
*
* 얘도 정렬된 배열, 중복된 요소들이 있는 배열에서는 힘을 못 썼음.
* QuickSortUpgrade와 같은 방식으로 약점을 보완해보려고 함.
* */

import easyAlgorithm.sorting.GenerateArr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class MyQuickSortUpgrade implements AdvancedSort{
    public static void main(String[] args) throws IOException {
        MyQuickSortUpgrade myQuickSort = new MyQuickSortUpgrade();

        /*int[] arr = {1,2,3,4,5};
        myQuickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{2,4,5,3,1};
        myQuickSort.sort(arr,0,4);
        System.out.println(Arrays.toString(arr));

        arr = GenerateArr.generateRandomArr(100);
        System.out.println(Arrays.toString(arr));
        myQuickSort.sort(arr,0,99);
        System.out.println(Arrays.toString(arr));*/

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        System.out.println(sb);
    }

    private Random random = new Random();

    @Override
    public void sort(int[] arr, int s, int e){
        if (s<e){//그냥 이렇게 하는게 더 깔끔한 듯.
            int randomIndex = random.nextInt(e-s)+s;//랜덤한 인덱스의 숫자를 맨 앞으로 보내서 Privot이 되게 함. 정렬된 배열이 들어왔을 때 최대한 균등하게 분할되게 할 수 있다.
            int temp = arr[s];
            arr[s] = arr[randomIndex];
            arr[randomIndex] = temp;

            int m = process(arr,s,e);

            sort(arr,s,m-1);
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
