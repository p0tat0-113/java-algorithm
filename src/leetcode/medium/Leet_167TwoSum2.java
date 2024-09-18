package leetcode.medium;

/*
정렬된 배열과 타겟넘버가 주어지면 배열의 두 수를 더해서 타겟넘버를 만들 수 있는 조합을 찾는 문제다.
답은 오직 하나만 있고, 같은 숫자를 두 번 사용하면 안된다고 한다. constant extra space만 사용해야 한다고 한다.
이미 정렬된 배열이 들어오기 때문에 투포인터로 풀면 아주 간단하게 풀 수 있다.

그런데 나는 이 문제를 binarySearch태그로 찾았다. binarySearch로도 풀 수 있다는 건데 어떻게 하는걸까?
일단 투포인터로 먼저 풀어보자.

이 문제의 가장 효율적인 풀이는 투포인터 알고리즘이 맞다. solutions를 봐도 다들 투포인터로 푸네.
https://chatgpt.com/share/66eae0a4-b04c-8006-a36b-00207ee8330b
이진 탐색으로 풀 수도 있지만, 배열을 순회하면서 target-배열에서 순서대로 고른 수와 같은 수가 있는지 이진탐색으로 찾는 방식이기 때문에 O(nlogn)이다.
*/

public class Leet_167TwoSum2 {
    public static void main(String[] args) {

    }

    //아주 간단한 투포인터 알고리즘 방식 풀이.
    public int[] twoSum(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;

        while(head != tail) {
            if (numbers[head] + numbers[tail] == target) {
                break;
            } else if (numbers[head] + numbers[tail] < target) {
                head++;
            } else {
                tail--;
            }
        }

        return new int[] {++head, ++tail};
    }
}
