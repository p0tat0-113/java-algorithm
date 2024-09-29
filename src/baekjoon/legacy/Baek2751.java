package baekjoon.legacy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek2751 {

    //병합 정렬 구현하기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        arr = merge_sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[] merge_sort(int[] arr){
        if (arr.length > 1){
            int mid = arr.length/2;
            int[] leftArr = Arrays.copyOfRange(arr,0,mid);
            int[] rightArr = Arrays.copyOfRange(arr,mid,arr.length);

            int[] leftMerged = merge_sort(leftArr);//같은 성격이지만 크기 절반임. T(n) = 2T(n/2) + f(n)
            int[] rightMerged = merge_sort(rightArr);

            return merge(leftMerged,rightMerged);
        }
        return arr;
    }

    private static int[] merge(int[] left, int[] right){
        int[] merged = new int[left.length+ right.length];
        int mergedIndex = 0;

        int leftIndex = 0;
        int rightIndex = 0;

        while(mergedIndex < merged.length){
            if (leftIndex == left.length){
                merged[mergedIndex++] = right[rightIndex++];
                continue;
            }
            if (rightIndex == right.length){
                merged[mergedIndex++] = left[leftIndex++];
                continue;
            }

            if (left[leftIndex] < right[rightIndex]){
                merged[mergedIndex++] = left[leftIndex++];
            } else {
                merged[mergedIndex++] = right[rightIndex++];
            }
        }
        return merged;
    }
}
