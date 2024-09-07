package leetcode.medium;

/*
Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]

문제의 이름은 sort colors이지만, 그냥 0,1,2 이렇게 세가지 숫자로만 이루어진 배열을 정렬하면 되는 문제다.
정렬 알고리즘을 직접 구현하고, in-place방식으로 하라고 한다. 입력 크기에 비례하는 추가공간을 필요로 하지 않고, 입력 데이터 구조에 직접 작동하는 알고리즘을 의미함. https://en.wikipedia.org/wiki/In-place_algorithm
딱히 정렬이라고 할 것도 없고, 그냥 각 숫자가 출현한 횟수를 센 다음에 nums배열에 집어넣으면 되는거 아닌가?

이 방법이 맞는 것 같음. 제출해보니까 수행시간은 0ms로 100프로 다 땄고, 메모리는 63프로를 땄음.
*/

public class Leet_75SortColors {
    public void sortColors(int[] nums) {
        int red = 0; //0
        int white = 0; //1
        int blue = 0; //2

        for (int num : nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            } else {
                blue++;
            }
        }

        //ex) 2,2,2
        for (int i = 0; i < red; i++) {
            nums[i] = 0;
        }
        for (int i = red; i < red+white; i++) {
            nums[i] = 1;
        }
        for (int i = red+white; i < red+white+blue; i++) {
            nums[i] = 2;
        }
    }
}
