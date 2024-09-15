package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;

/*
https://imgur.com/a/prVVoZO
142번 LinkedListCycle2 문제를 배열로 한 번 꼬아서 낸 문제였다.
이 문제는 그냥 배열에서 중복된 숫자를 찾는게 아니라, 연결리스트에서 사이클을 탐지해내고, 사이클의 시작점을 찾아내는 문제의 관점에서 봐야한다.
배열의 숫자들은 다음으로 오는 노드의 참조값을 의미한다.

이 문제는 '플로이드의 토끼와 거북이'알고리즘을 사용하면 쉽게 풀린다. 이거 푸느라 좀 고생했는데 약간 허무하네ㅋㅋ
*/

public class Leet_287FindTheDuplicateNumber {
    public static void main(String[] args) {
        Leet_287FindTheDuplicateNumber leet = new Leet_287FindTheDuplicateNumber();
        System.out.println(leet.findDuplicate(new int[]{1,3,3,2,4}));
    }

    public int findDuplicate(int[] nums) {
        int slow = nums[0];//한 번에 한 칸씩 움직임
        int fast = nums[0];//한 번에 두 칸씩 움직임

        while(true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) {//만약 slow와 fast가 만나게 되면 연결리스트에 사이클이 존재하는 것이다.
                break;
            }
        }

        slow = nums[0];//slow를 다시 첫 노드로 보냄.
        while(slow != fast) {//그리고 다시 slow와 fast가 만날 때까지 둘 다 한 칸씩 이동시킨다. 둘이 만나는 지점이 사이클의 시작점이다.
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    //이 코드로 사이클이 시작되는 수가 중복되는 수라는 가설을 검증함. 성공했다. 이제 set만 어떻게든 제거하면 됨.
    //리트코드에서 analyze 해본 결과 공간복잡도는 여전히 O(n)이다. 상수공간을 사용하면서도 사이클의 시작지점을 찾을 수 있는 방법이 필요하다.
    /*public int findDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>(nums.length);

        int num = nums[0];//num은 실제 숫자, 이 숫자는 다음으로 오는 숫자의 인덱스다.
        set.add(0);//set에 들어가는 것은 해당 숫자의 인덱스(고유한 값)
        while(true) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
            num = nums[num];
        }
    }*/


    //아래의 두 풀이방법은, 문제의 조건에 맞지 않는 풀이방법이다. 그리고 제출했을 때 수행시간이 느린걸 보니까 O(logn)의 풀이 방법이 있는 것으로 보인다.
    /*public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == nums[i+1]) {
                return nums[i];
            }
        }
        return 0;
    }*/

    /*public int findDuplicate(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        HashSet<Integer> set = new HashSet<>(max);
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return 0;
    }*/
}
