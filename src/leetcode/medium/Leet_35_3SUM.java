package leetcode.medium;

/*
https://imgur.com/a/2JUGibl
투포인터 방식으로 탐색하면 될 듯함. 만들긴 만들었는데 시간이 너무 오래걸림. 114ms로 29프로를 땄고, 메모리는 겨우 10프로를 땀.
*/

import java.util.*;

public class Leet_35_3SUM {
    public static void main(String[] args) {
        Leet_35_3SUM leet353SUM = new Leet_35_3SUM();
        System.out.println(leet353SUM.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>();//이미 나온 숫자들의 조합을 담아두는 set
        HashSet<Integer> logSet = new HashSet<>();//이미 체크한 num을 담아두는 set

        Arrays.sort(nums);//투포인터를 쓰기 위해 우선 정렬한다.

        int head;
        int tail;
        int num;
        int targetNum;
        for (int i = 0; i < nums.length; i++) {
            head = 0;
            tail = nums.length-1;

            num = nums[i];
            if (logSet.contains(num)) {//이미 체크한 숫자라면 다시 for문 처음으로 돌아감.
                continue;
            }
            targetNum = 0 - num;

            while (true) {
                //head나 tail이 num의 인덱스와 같다면 한 칸 전진함.
                if (head == i) {
                    head++;
                } else if (tail == i) {
                    tail--;
                }

                //head가 tail과 같거나 더 커지면 탐색 중단.
                if (head >= tail) {
                    break;
                }

                if (nums[head] + nums[tail] == targetNum) {
                    ArrayList<Integer> list = new ArrayList<>(List.of(num, nums[head], nums[tail]));
                    list.sort(null);
                    result.add(list);

                    head++;
                    tail--;
                }

                //투포인터 탐색
                if (nums[head] + nums[tail] < targetNum) {
                    head++;
                } else if (nums[head] + nums[tail] > targetNum) {
                    tail--;
                }
            }
            logSet.add(num);
        }

        return new ArrayList<>(result);
    }
}
