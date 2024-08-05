package sorting.basic;

import java.util.*;

public class PerformanceTest {
    public static void main(String[] args) {
        Sort[] arr = {new BubbleSort(),new InjectionSort(), new SelectionSort()};

        int[] nums = generateArr(20000);

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

    public static int[] generateArr(int size){
        //중복 없이 랜덤한 순서의 배열 생성
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();

        while (set.size() < size){
            set.add(random.nextInt(size)+1);
        }

        int[] nums = new int[size];

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.shuffle(list);

        for (int i = 0; i < size; i++) {
            nums[i] = list.get(i);
        }

        return nums;
    }
}
