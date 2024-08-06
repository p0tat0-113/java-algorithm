package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class GenerateArr {
    public static int[] generateRandomArr(int size){
        //중복 없이 랜덤한 순서의 배열 생성
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();

        while (set.size() < size){
            set.add(random.nextInt(size)+1);
        }

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.shuffle(list);

        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = list.get(i);
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
        for (int i = 0; i < size; i++) {
            nums[i] = 100;
        }
        return nums;
    }
}
