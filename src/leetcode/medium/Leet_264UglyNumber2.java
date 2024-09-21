package leetcode.medium;

import java.util.HashSet;

public class Leet_264UglyNumber2 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1352));
    }

    //우선 HashTable을 이용한 방식으로 풀어본다. DP와 Heap을 쓰는 방식은 아직 잘 모르겠음.
    //잘 작동되기는 하지만 너무 비효율적이다. 1352번째 수는 402653184이다. 지금의 방식으로는 찾는데 수 초 이상 걸림.
    public static int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }

        int count = n-1;
        int num = 2;
        int[] primeFactors = {2, 3, 5};
        HashSet<Integer> set = new HashSet<>();
        set.add(1);

        while (count != 0) {
            for (int primeFactor : primeFactors) {
                if (num % primeFactor == 0) {
                    if (set.contains(num / primeFactor)) {
                        count--;
                        set.add(num);
                        break;
                    }
                }
            }
            num++;
        }
        return num-1;
    }
}
