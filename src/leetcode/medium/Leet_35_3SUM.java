package leetcode.medium;

/*
https://imgur.com/a/2JUGibl
투포인터 방식으로 탐색하면 될 듯함. 만들긴 만들었는데 시간이 너무 오래걸림. 114ms로 29프로를 땄고, 메모리는 겨우 10프로를 땀.

solution을 보고 힌트를 얻었음. 투포인터 탐색 범위를 줄여나가면 숫자 조합 중복문제도 해결하고, 탐색범위도 줄일 수 있다!
개선 후 41ms로 훨씬 빨라졌다.
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
            head = i+1;//어차피 투포인터 탐색범위를 하나씩 줄여나갈 것이기 때문에 head는 i+1로 잡는다. ex)num은 0번 인덱스, 투 포인터는 각각 바로 뒤의 인덱스, 맨 끝 인덱스. 이후 탐색을 하면서 셋을 합쳐서 0을 만든다.
            tail = nums.length-1;

            if (head >= tail) {
                break;
            }

            num = nums[i];
            if (logSet.contains(num)) {//이미 체크한 숫자라면(조합을 찾았던, 못 찾았던 이미 한 번 탐색했음.) 다시 for문 처음으로 돌아감.
                continue;
            }
            targetNum = 0 - num;

            while (true) {

                //head가 tail과 같거나 더 커지면 탐색 중단.
                if (head >= tail) {
                    break;
                }

                if (nums[head] + nums[tail] == targetNum) {
                    ArrayList<Integer> list = new ArrayList<>(List.of(num, nums[head], nums[tail]));
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

    /*public List<List<Integer>> threeSum(int[] nums) {
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

                if (nums[head] + nums[tail] == targetNum) {//조합을 찾으면
                    ArrayList<Integer> list = new ArrayList<>(List.of(num, nums[head], nums[tail]));
                    list.sort(null);//이미 나온 숫자 조합을 걸러내기 위해 정렬한 다음에 셋에 집어넣었음. 이 과정이 비효율적이었다.
                    result.add(list);

                    head++;//다른 조합도 찾기 위해 계속 전진
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
    }*/
}
