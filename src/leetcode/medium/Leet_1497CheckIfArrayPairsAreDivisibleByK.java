package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Leet_1497CheckIfArrayPairsAreDivisibleByK {
    public static void main(String[] args) {
        //List는 이렇게 set을 이용해서 중복을 제거할 수 있다. List에서 이미 hashCode를 오버라이딩 해놓았기 때문에 가능한 것.
        /*HashSet<List<Integer>> set = new HashSet<>();
        set.add(List.of(1,2,3));
        set.add(List.of(1,2,3));
        System.out.println(set);*/

        System.out.println(canArrange(new int[]{1, 2, 3, 4, 5, 6, 15, -1, -2, 5}, 10));
        System.out.println(canArrange(new int[]{75,5,-5,75,-2,-3,88,10,10,87}, 85));
    }

    //solutions를 보니까 나 혼자 계속 고민해서는 절대 못 풀 문제였음....
    //https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/solutions/5854226/can-you-pair-these-numbers-beats-100-explained-with-video-c-java-python-js-explained/?envType=daily-question&envId=2024-10-01
    //이 문제를 아주 영리한 방식으로 해결했음.
    //먼저 길이 k의 배열을 생성한다.
    //그리고 k의 각 원소 <- k의 각 원소는 각 숫자를 k로 나눈 나머지를 의미함. 이 나머지들이 각각 몇개씩 등장했는지를 기록한다. 당연한 얘기지만 나머지는 k를 넘을 수 없음.
    //그리고 나머지들을 기록해둔 배열을 통해 arr에서 올바르게 쌍을 만들 수 있는지 확인할 수 있다.
    //먼저 나머지가 0인 경우는 짝수여야 한다. 예를 들어서 [5,15,20,25] k=5 이런 경우 5로 나눠지는 수, 5로 나눈 나머지가 0인 수들끼리는 어떻게 더하든지 항상 5로 나누어진다.
    //그리고 나머지들끼리 합쳐서 k가 되는 경우는 둘의 등장 수가 같아야 한다. 예를 들어서 [1,3,5,7] k=4 이 경우 (1,3) (5,7)이렇게 쌍이 만들어진다.
    public static boolean canArrange(int[] arr, int k) {
        int [] freq = new int[k];
        for(int num: arr){
            int rem = num % k;
            if(rem < 0){
                rem = rem + k;
            }
            freq[rem]++;
        }
        if(freq[0] % 2 != 0){
            return false;
        }
        for(int i = 1;i <= k / 2;i++){
            if(freq[i] != freq[k-i]) {
                return false;
            }
        }
        return true;
    }

    //코드를 이렇게 고치니까 이제 정상적으로 동작하기는 하지만, 입력의 크기가 매우 클 때 시간초과가 발생한다.
    /*public static boolean canArrange(int[] arr, int k) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for (int i = 0; i < arr.length-1; i++) {//투포인터 탐색 범위를 점차 줄여감
            for (int j = i+1; j < arr.length; j++) {//움직이는 포인터
                if ((arr[i] + arr[j]) % k == 0) {
                    if (!(set.contains(i) || set.contains(j))) {
                        set.add(i);
                        set.add(j);
                        count++;
                        break;
                    }
                }
            }

            if (count == arr.length / 2) {
                return true;
            }
        }

        System.out.println(count);

        return false;
    }*/

    /*public static boolean canArrange(int[] arr, int k) {
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        HashSet<List<Integer>> listSet = new HashSet<>();
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < arr.length-1; i++) {//투포인터 탐색 범위를 점차 줄여감
            for (int j = i+1; j < arr.length; j++) {//움직이는 포인터
                if ((arr[i] + arr[j]) % k == 0) {
                    *//*if (!(set.contains(i) || set.contains(j))) {
                        set.add(i);
                        set.add(j);
                        listSet.add(List.of(arr[i], arr[j]));//조합 중복을 무지성으로 제거해서는 안됨 [5,5,10,10] k=15 이런 경우를 염두에 둬야 한다.
                        break;
                    }*//*
                    listSet.add(List.of(arr[i], arr[j]));
                }
            }
        }

        System.out.println(listSet);

        return listSet.size() >= arr.length / 2;
    }*/
}
