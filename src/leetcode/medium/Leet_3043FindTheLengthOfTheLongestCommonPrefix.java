package leetcode.medium;

/*
arr1의 각 단어의 길이를 늘려가면서  arr2의 단어들의 앞 부분과 일치하는지 비교를 해야 함.
일단 max가 나오면 그 다음부터는 max보다 긴 길이로 비교를 해야 함.

일단 처음에 구현한 방식은 n^2이고 오버헤드가 너무 크다. arr1, arr2의 길이는 최대 50000,
각 요소의 길이는 10^8이다.

한가지 떠오르는 것은 arr1, arr2 각 요소를 앞에서부터 길이를 하나씩 늘려가면서 각각의 set에 저장을 하고
교집합을 때리는 거다. 그리고 꺼내서 우선순위 큐에 넣어서 길이가 가장 긴 것을 고르면 됨. 근데 이 방식도 시간복잡도도 그렇고 오버헤드가 상당히 클 것 같다는게 문제다.
그래도 한 번 시도해 볼까... <- 개 무식한 방법이긴 한데 이렇게 하니까 풀리긴 풀렸다. 근데 런타임이 241ms로 21%만 땄음... 나중에 좀 더 최적회를 해보자.
*/

import java.util.*;

public class Leet_3043FindTheLengthOfTheLongestCommonPrefix {
    public static void main(String[] args) {
        //System.out.println(longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000}));
        longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000});
    }

    public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        for (int i : arr1) {
            String s = String.valueOf(i);
            for (int j = 1; j <= s.length(); j++) {
                set1.add(s.substring(0,j));
            }
        }
        for (int i : arr2) {
            String s = String.valueOf(i);
            for (int j = 1; j <= s.length(); j++) {
                set2.add(s.substring(0,j));
            }
        }
        //System.out.println(set1);
        //System.out.println(set2);

        set1.retainAll(set2);
        //System.out.println(set1);

        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        }.reversed());

        for (String s : set1) {
            priorityQueue.offer(s);
        }

        String result = priorityQueue.poll();
        return (result != null) ? result.length() : 0;
    }

    //문제를 이상하게 파악을 했네ㅅㅂ
    /*public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        char[] start = String.valueOf(arr2[0]).toCharArray();
        char[] end = String.valueOf(arr2[arr2.length-1]).toCharArray();
        int max = 0;


        for (int i = 0; i < arr1.length; i++) {
            char[] temp = String.valueOf(arr1[i]).toCharArray();
            int tempMax = 0;
            for (int j = 0; j < temp.length; j++) {
                if (temp.length <= max) {
                    break;
                }
                if (j == start.length || j == end.length) {
                    break;
                }
                if (temp[j] == start[j] && temp[j] == end[j]) {
                    tempMax++;
                } else {
                    break;
                }
            }
            if (tempMax > max) {
                max = tempMax;
            }
        }
        return max;
    }*/

    /*public static int longestCommonPrefix(int[] arr1, int[] arr2) {
        String[] sArr1 = new String[arr1.length];
        String[] sArr2 = new String[arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            sArr1[i] = String.valueOf(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            sArr2[i] = String.valueOf(arr2[i]);
        }

        int max = 0;//가장 긴 접두사의 길이
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                while(true) {
                    if (max > sArr1[i].length()-1 || max > sArr2[j].length()-1) {//접두사가 둘 중 하나보다 길면 바로 break 걸고 다음으로 빠져나감.
                        break;
                    }
                    if (sArr1[i].substring(0, max+1).equals(sArr2[j].substring(0,max+1))) {
                        max++;
                    } else {
                        break;
                    }
                }
            }
        }

        return max;
    }*/
}
