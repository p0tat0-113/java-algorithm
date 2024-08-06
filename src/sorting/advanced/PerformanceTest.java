package sorting.advanced;

import sorting.GenerateArr;

import java.util.*;

public class PerformanceTest {
    public static void main(String[] args) {
        AdvancedSort[] arr = {new MergeSort(),new SwitchingMergeSort(), new MyQuickSort(), new QuickSort()};

        test(arr, 1000_000);
    }

    private static void test(AdvancedSort[] arr, int size){
        for (AdvancedSort advancedSort : arr) {
            int[] randomArr = GenerateArr.generateRandomArr(size);
            int[] sortedArr = GenerateArr.generateSortedArr(size);
            int[] duplicatedArr = GenerateArr.generateDuplicatedArr(size);

            System.out.println("============================");
            System.out.println(advancedSort.getClass());

            long start = System.currentTimeMillis();
            advancedSort.sort(randomArr,0,size-1);
            long end = System.currentTimeMillis();
            System.out.println("소요시간: "+(end-start)+"ms");

            /*start = System.currentTimeMillis();
            advancedSort.sort(sortedArr,0,size-1);
            end = System.currentTimeMillis();
            System.out.println("소요시간: "+(end-start)+"ms");*/

            /*start = System.currentTimeMillis();
            advancedSort.sort(duplicatedArr,0,size-1);
            end = System.currentTimeMillis();
            System.out.println("소요시간: "+(end-start)+"ms");*/
        }

        /*System.out.println("============================");
        System.out.println("자바 제공 sort()");
        long start = System.currentTimeMillis();
        Arrays.sort(nums);
        long end = System.currentTimeMillis();
        System.out.println("소요시간: "+(end-start)+"ms");*/
    }
}
