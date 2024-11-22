package leetcode.review.mid;

public class Leet_287FIndTheDuplicatedNumber {
    public static void main(String[] args) {
        Leet_287FIndTheDuplicatedNumber leet = new Leet_287FIndTheDuplicatedNumber();
        System.out.println(leet.findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(leet.findDuplicate(new int[]{3,1,3,4,2}));
        System.out.println(leet.findDuplicate(new int[]{3,3,3,3,3}));
    }

    //사이클을 탐지하고, 사이클의 시작점을 찾아내야 한다.
    public int findDuplicate(int[] nums) {
        int slow = 0;//slow와 fast가 가지고 있는 것은 자기 자신의 참조값, next는 nums[slow], nums[fast]를 하며 얻어진다.
        int fast = 0;

        while(true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast) {//사이클을 탐지함.
                slow = 0;//slow를 다시 맨 앞으로 보내고, slow와 fast를 한칸씩만 움직인다.
                while(true) {
                    slow = nums[slow];
                    fast = nums[fast];

                    if (slow == fast) {//둘이 만나는 지점이 사이클이 시작되는 지점이다.
                        return slow;
                    }
                }
            }
        }
    }
}
