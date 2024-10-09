package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/*
1 <= n <= 1690
*/

public class Leet_264UglyNumber2 {
    public static void main(String[] args) {
        //System.out.println(nthUglyNumber(1354));
        //System.out.println(nthUglyNumber(1407));
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        //2,3,5가 곱해질 인덱스를 나타냄.
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        //첫 ugly number인 1에 2,3,5가 각각 곱해진 값
        int next2 = 2;
        int next3 = 3;
        int next5 = 5;

        for (int i = 1; i < n; i++) {
            //다음 ugly number가 될 수를 정한다.
            int nextUgly = Math.min(next2, Math.min(next3, next5));
            dp[i] = nextUgly;

            if (nextUgly == next2) {
                i2++;//다음에 2를 곱할 수를 가리키게 한다. ex) 1 -> 2
                next2 = 2 * dp[i2];//next2를 i2가 가리키는 수로 바꾼다. ex) 2 -> 4
            }
            if (nextUgly == next3) {//처음에 else if문을 써서 계속 엉뚱한 답이 나오고 있었음. 6같은 경우 2*3, 3*2 두가지 경우의 수가 있다. 이때 i2, i3둘다 하나씩 증가시키고, next2, next3의 값도 바꿔줘야 한다.
                i3++;
                next3 = 3 * dp[i3];
            }
            if (nextUgly == next5) {
                i5++;
                next5 = 5 * dp[i5];
            }
        }

        //System.out.println(Arrays.toString(dp));

        return dp[n-1];
    }

    //TreeSet을 이용해서 풀어본다. TreeSet의 증복을 제거하고, 정렬된 상태를 유지하는 특성을 이용하는 것인데 나쁘지 않아 보인다.
    //TreeSet말고 최소힙을 써도 똑같다.
    //다만 수행시간이 50ms로 37%밖에 따지 못해서 DP를 이용한 풀이 방법을 알아봐야겠다.
    /*public static int nthUglyNumber(int n) {
        TreeSet<Long> treeSet = new TreeSet<>();//n이 1360쯤 되면 Integer 오버플로우가 발생해서 Long을 사용했다.
        treeSet.add(1L);
        Long result = 1L;

        for (int i = 0; i < n; i++) {
            Long ugly = treeSet.pollFirst();
            result = ugly;

            treeSet.add(ugly * 2);
            treeSet.add(ugly * 3);
            treeSet.add(ugly * 5);
        }

        return result.intValue();
    }*/

    //우선 HashTable을 이용한 방식으로 풀어본다. DP와 Heap을 쓰는 방식은 아직 잘 모르겠음.
    //잘 작동되기는 하지만 너무 비효율적이다. 1352번째 수는 402653184이다. 지금의 방식으로는 찾는데 수 초 이상 걸림.
    /*public static int nthUglyNumber(int n) {
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
    }*/
}
