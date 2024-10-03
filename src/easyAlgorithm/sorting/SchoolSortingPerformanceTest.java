package easyAlgorithm.sorting;

import easyAlgorithm.sorting.advanced.*;
import easyAlgorithm.sorting.basic.InsertionSort;
import easyAlgorithm.sorting.basic.Sort;

import java.util.Arrays;

public class SchoolSortingPerformanceTest {
    public static void main(String[] args) {
        AdvancedSort[] algorithms1 = {new MergeSort(),new SwitchingMergeSort(), new MyQuickSortUpgrade(), new HeapSort()};
        //testAdvanced(algorithms1, 10_000_000);

        Sort[] algorithms2 = {new InsertionSort()};
        testBasic(algorithms2, 10_000_000);
    }

    private static void testAdvanced(AdvancedSort[] algorithm, int size) {
        int[] randomArr = GenerateArr.generateRandomArr(size);
        int[] sortedArr = GenerateArr.generateSortedArr(size);
        int[] duplicatedArr = GenerateArr.generateDuplicatedArr(size);

        System.out.println("랜덤 배열, 정렬된 배열, 모든 수가 중복된 배열 순으로 정렬한다.");

        for (AdvancedSort advancedSort : algorithm) {
            //각 정렬 알고리즘으로 랜덤 배열, 정렬된 배열, 모든 요소가 중복된 배열을 정렬한다.
            int[] a1 = Arrays.copyOf(randomArr, size);
            int[] a2 = Arrays.copyOf(sortedArr, size);
            int[] a3 = Arrays.copyOf(duplicatedArr, size);

            int[][] arrs = {a1, a2, a3};

            System.out.println("============================");
            System.out.println(advancedSort.getClass().getTypeName());//정렬 방식 이름 출력

            for (int[] arr : arrs) {
                long start = System.nanoTime();

                advancedSort.sort(arr, 0, size - 1);

                long end = System.nanoTime();
                System.out.print("소요시간: " + (end - start)/1_000_000_000.0 + "s ");//나노초를 초로 변환해서 출력
            }
            System.out.println();
        }
    }

    private static void testBasic(Sort[] algorithm, int size) {
        int[] randomArr = GenerateArr.generateRandomArr(size);
        int[] sortedArr = GenerateArr.generateSortedArr(size);
        int[] duplicatedArr = GenerateArr.generateDuplicatedArr(size);

        for (Sort sort : algorithm) {
            //각 정렬 알고리즘으로 랜덤 배열, 정렬된 배열, 모든 요소가 중복된 배열을 정렬한다.
            int[] a1 = Arrays.copyOf(randomArr, size);
            int[] a2 = Arrays.copyOf(sortedArr, size);
            int[] a3 = Arrays.copyOf(duplicatedArr, size);

            int[][] arrs = {a2, a3};

            System.out.println("============================");
            System.out.println(sort.getClass().getTypeName());//정렬 방식 이름 출력

            for (int[] arr : arrs) {
                long start = System.nanoTime();

                sort.sort(arr);

                long end = System.nanoTime();
                System.out.print("소요시간: " + (end - start)/1_000_000_000.0 + "s ");//나노초를 초로 변환해서 출력
            }
            System.out.println();
        }
    }
}
