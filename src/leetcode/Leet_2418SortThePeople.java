package leetcode;

/*
사람의 이름과 신장이 담긴 각각의 배열 2개가 주어진다. i번째 이름과 신장이 매치되어있는 구조다.
그리고 키 내림차순 순서대로 정렬된 이름 배열을 반환해야 한다.
이름 배열은 중복될 수 있지만, 신장 배열은 distinct, 즉 중복되지 않는다고 한다.

이걸 어떻게 정렬을 하는게 좋을까.... 퀵 정렬을 구현하고, 신장 배열을 움직이는대로 그대로 이름배열의 요소들을 움직이면 될까?
<- 이 방식으로 했더니 시간이 매우 느려짐.. 시간에서 6프로 밖에 못땀. 하지만 메모리에서는 98프로를 땄음.

아니면 트리맵에 신장을 키로, 이름을 값으로 넣고, values()를 꺼내면 될까? <- 이 방법으로 풀이했더니 시간에서 91프로, 메모리에서 96프로를 땀.
*/

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;

public class Leet_2418SortThePeople {

    /*public String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, String> treeMap = new TreeMap<>(Collections.reverseOrder());//Collections.reverseOrder() 역순으로 정렬되게 함

        //신장을 키로, 이름을 값으로 맴에 집어넣음. 이진트리구조의 map으로 키를 새로 넣을 때마다 지속적으로 정렬된다.
        for (int i = 0; i < names.length; i++) {
            treeMap.put(heights[i], names[i]);
        }

        //values(), 즉 이름만 뽑아내서 배열로 반환
        Collection<String> values = treeMap.values();
        return values.toArray(new String[0]);//toArray()로 배열로 반환한다. 인수로 new String[0]을 넣어줘야 함.
        //https://priming.tistory.com/37
    }*/

    //직접 퀵 정렬을 구현해서 정렬해본다. partition()에서 heights의 요소들을 움직이는 거에 맞춰서 names의 요소들도 똑같이 움직이게 하면 되지 않을까?
    public String[] sortPeople(String[] names, int[] heights) {
        quickSort(names, heights, 0, heights.length-1);
        return names;
    }

    private void quickSort(String[] names, int[] heights, int start, int end) {
        if (start < end) {
            int mid =  partition(names, heights, start, end);

            quickSort(names, heights, start, mid-1);
            quickSort(names, heights, mid+1, end);
        }
    }

    private int partition(String[] names, int[] heights, int start, int end) {
        int pivot = heights[start];
        int low = start+1;
        int high = end;

        //heights의 요소들에서 교환이 일어날 때 names의 요소들도 똑같이 움직여줘야 한다.

        while (low <= high) {
            if (heights[low] > pivot) {//내림차순으로 정렬
                low++;
            } else if (heights[high] < pivot) {//내림차순으로 정렬
                high--;
            } else {
                int tempHeight = heights[low];
                heights[low] = heights[high];
                heights[high] = tempHeight;

                String tempName = names[low];
                names[low] = names[high];
                names[high] = tempName;

                low++;
                high--;
            }
        }

        int tempHeight = heights[start];
        heights[start] = heights[high];
        heights[high] = tempHeight;

        String tempName = names[start];
        names[start] = names[high];
        names[high] = tempName;

        return high;
    }

    public static void main(String[] args) {
        Leet_2418SortThePeople leet2418SortThePeople = new Leet_2418SortThePeople();
        System.out.println(Arrays.toString(leet2418SortThePeople.sortPeople(new String[]{"Mary", "John", "Emma"}, new int[]{180, 165, 170})));
    }
}
