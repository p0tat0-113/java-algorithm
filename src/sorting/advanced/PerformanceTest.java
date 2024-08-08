package sorting.advanced;

import sorting.GenerateArr;

import java.util.*;

public class PerformanceTest {
    public static void main(String[] args) {
        AdvancedSort[] algorithm = {new MergeSort(),new SwitchingMergeSort(),/*new MyQuickSort() ,*/new QuickSortUpgrade(), new MyQuickSortUpgrade(), new HeapSort()};

        test(algorithm, 10_000_000);
    }

    private static void test(AdvancedSort[] algorithm, int size){
        int[] randomArr = GenerateArr.generateRandomArr(size);
        int[] sortedArr = GenerateArr.generateSortedArr(size);
        int[] reversedSortedArr = GenerateArr.generateReversedSortedArr(size);
        int[] duplicatedArr = GenerateArr.generateDuplicatedArr(size);

        for (AdvancedSort advancedSort : algorithm) {
            int[] a1 = Arrays.copyOf(randomArr,size);
            int[] a2 = Arrays.copyOf(sortedArr,size);
            int[] a3 = Arrays.copyOf(reversedSortedArr,size);
            int[] a4 = Arrays.copyOf(duplicatedArr,size);

            int[][] arrs = {a1,a2,a3,a4};

            System.out.println("============================");
            System.out.println(advancedSort.getClass());

            for (int[] arr : arrs) {
                long start = System.currentTimeMillis();

                advancedSort.sort(arr,0,size-1);

                long end = System.currentTimeMillis();
                System.out.print("소요시간: "+(end-start)+"ms ");
            }
            System.out.println();
        }

        /*System.out.println("============================");
        System.out.println("자바 제공 sort()");
        long start = System.currentTimeMillis();
        Arrays.sort(nums);
        long end = System.currentTimeMillis();
        System.out.println("소요시간: "+(end-start)+"ms");*/
    }
}
