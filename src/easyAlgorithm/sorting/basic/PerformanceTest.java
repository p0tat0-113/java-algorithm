package easyAlgorithm.sorting.basic;

import easyAlgorithm.sorting.GenerateArr;

import java.util.*;

public class PerformanceTest {
    public static void main(String[] args) {
        Sort[] arr = {new BubbleSort(),new InsertionSort(), new SelectionSort()};

        int[] nums = GenerateArr.generateRandomArr(20000);

        test(arr,nums);
    }

    private static void test(Sort[] arr, int[] nums){
        for (Sort sort : arr) {
            int[] copiedNums = Arrays.copyOf(nums,nums.length);//배열 복사

            System.out.println("============================");
            System.out.println(sort.getClass());
            long start = System.currentTimeMillis();
            sort.sort(copiedNums);
            long end = System.currentTimeMillis();
            System.out.println("소요시간: "+(end-start)+"ms");
        }

        System.out.println("============================");
        System.out.println("자바 제공 sort()");
        long start = System.currentTimeMillis();
        Arrays.sort(nums);
        long end = System.currentTimeMillis();
        System.out.println("소요시간: "+(end-start)+"ms");
    }
}
