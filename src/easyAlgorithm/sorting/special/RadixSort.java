package easyAlgorithm.sorting.special;

/*기수 정렬, 원소들이 모두 k개 이하의 자릿수를 가진 자연수과 같은 특수한 경우에 사용할 수 있는 정렬 알고리즘이다. (자연수가 아닌 제한된 길이를 가진 알파벳도 포함됨.)
* 기수정렬은 안전정렬이다. 같은 값일 때 원래의 순서를 유지하는 것을 안정 정렬이라고 한다.
* 그리고 얘는 Θ(n)의 수행시간을 가진다. 일반적인 방식으로 정렬해서는 안됨.
*/


import java.util.*;

public class RadixSort {
    public static void main(String[] args) {
        RadixSort radixSort = new RadixSort();

        int[]arr = {12,123,224,22,123,124};
        radixSort.radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void radixSort(int[] arr){
        //먼저 최대값을 구해서, 그 자리수를 알아내야 함.
        int digit = getMaxDigit(arr);

        Queue<Integer>[] bucket = new Queue[10];//Queue를 사용, 앞으로 넣고 빼는 성능이 좋음.
        initBucket(bucket);

        //제일 큰 수가 123이라면, 그 수의 자릿수인 3 만큼 반복
        for (int i = 0; i < digit; i++) {//1의 자릿수부터 순서대로 작업을 진행함.

            //배열에서 숫자를 하나씩 꺼내서, 현재 탐색하는 자릿수의 숫자를 구함, 그리고 거기에 맞는 번지수의 queue에 집어넣는다.
            for (int num : arr) {
                bucket[getDigit(num, i)].offer(num);
            }

            //다 집어넣었으니까 이제 순서대로 하나씩 빼서 다시 배열에다가 집어넣음. 다음 반복을 준비
            int index = 0;
            for (Queue<Integer> queue : bucket) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    arr[index++] = queue.poll();
                }
            }
        }

    }

    private void initBucket(Queue<Integer>[] bucket){
        for (int i = 0; i < 10; i++) {
            bucket[i] = new ArrayDeque<>();
        }
    }

    // 숫자의 자리수 반환
    // getDigit(123, 0) => 3
    // getDigit(123, 1) => 2
    // getDigit(123, 2) => 1
    private int getDigit(int num, int index) {
        return (int)Math.floor(Math.abs(num) / Math.pow(10, index)) % 10;
    }

    private int digitCount(int num) {
        if (num == 0) {
            return 1;
        }

        // log10을 하면 자리수가 나옴
        // log10(10) => 1
        // log10(100) -> log10(10^2) => 2
        return (int)Math.floor(Math.log10(Math.abs(num))) + 1;
    }

    //최대값을 구하고, 그 수의 자릿수를 알아낸다.
    private int getMaxDigit(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]){
                max = arr[i];
            }
        }

        return digitCount(max);
    }
}
