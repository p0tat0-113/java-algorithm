package leetcode.medium;

/*
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

배열 nums에서 k번째로 큰 수를 찾아서 반환하라고 한다. 근데 이걸 정렬 없이 해결하라고 함.
그 말은 우선순위 큐를 쓰라는 뜻. 대표적인 우선순위 큐로는 힙구조가 있음. 전에 배웠던 힙 정렬을 이용해보자.
https://imgur.com/a/xl8V2N1

이번에는 우선수위큐가 아니라 quick select 알고리즘을 이용해서 풀어본다.
*/

import javax.swing.plaf.IconUIResource;
import java.util.Random;

public class Leet_215KthLargestElementInArray_1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        //int[] nums = {2,1};
        //int[] nums = {3,2,3,1,2,4,5,5,6};

        System.out.println(findKthLargest(nums, 2));

        /*Random random = new Random();
        System.out.println(random.nextInt(1, 3));*/
    }

    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        Random random = new Random();
        return select(nums, 0, nums.length, nums.length-k, random);//배열의 길이가 6일 때, 2번째로 큰 수는 5번째로 작은 수와 같다.
    }

    private static int select(int[] arr, int s, int e, int i, Random random){//i번째로 작은 수의 인덱스를 반환함. 없으면 -1을 반환한다.
        if (s < e) {
            int randomIdx = random.nextInt(s,e);
            int temp = arr[randomIdx];
            arr[randomIdx] = arr[s];
            arr[s] = temp;

            int pivot = partition(arr,s,e);
            if (i < pivot) {
                return select(arr,s,pivot, i, random);
            } else if (i > pivot) {
                return select(arr,pivot+1, e, i, random);
            } else {//i == pivot인 경우, i번째로 작은 수를 찾았음.
                return arr[pivot];
            }
        }
        return -1;
    }

    private static int partition(int[] arr, int s, int e){//기준원소를 알맞은 위치로 옮기고, 그 인덱스를 반환한다.
        int pivot = arr[s];//첫번째 원소를 pivot으로 선택한다.
        //이번에는 책에 나온 1구역, 2구역, 3구역 방식을 사용해보자.
        int first = s;
        int second = s+1;
        while(second < e) {//second <= e로 하니까 가장 큰 숫자를 고르는 경우가 안됨.
            if (pivot > arr[second]){
                first++;
                int temp = arr[second];
                arr[second] = arr[first];
                arr[first] = temp;
            } else if (pivot == arr[second] && second%2 == 0) {
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
