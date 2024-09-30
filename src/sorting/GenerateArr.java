package sorting;

import java.util.*;

public class GenerateArr {
    public static int[] generateRandomArr(int size){
        //랜덤한 숫자들이 있는 배열 생성
        Random random = new Random();

        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt();
        }

        return nums;
    }

    public static int[] generateSortedArr(int size){
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i+1;
        }
        return nums;
    }

    public static int[] generateReversedSortedArr(int size){
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = size-i;
        }
        return nums;
    }

    public static int[] generateDuplicatedArr(int size){
        int[] nums = new int[size];
        Arrays.setAll(nums, e -> 100);//모든 숫자가 100인 배열
        return nums;
    }
}
