package leetcode.medium;

import java.util.Arrays;

/*
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

배열 nums에서 k번째로 큰 수를 찾아서 반환하라고 한다. 근데 이걸 정렬 없이 해결하라고 함.
그 말은 우선순위 큐를 쓰라는 뜻. 대표적인 우선순위 큐로는 힙구조가 있음. 전에 배웠던 힙 정렬을 이용해보자.
https://imgur.com/a/xl8V2N1
*/

public class Leet_215KthLargestElementInArray {

    public static void main(String[] args) {
        //int[] nums = {3, 2, 1, 5, 6, 4};
        //int[] nums = {2,1};
        int[] nums = {3,2,3,1,2,4,5,5,6};

        Leet_215KthLargestElementInArray leet = new Leet_215KthLargestElementInArray();
        System.out.println(leet.findKthLargest(nums, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        buildHeap(nums);//완전 이진 트리를 힙 구조로 수선

        //System.out.println(Arrays.toString(nums));

        //k-1번 힙에서 루트를 꺼내고, 스며내리기를 해서 그 다음으로 큰 수를 루트로 올린다.
        for (int i = 0; i < k-1; i++) {
            //루트노드와 맨 끝 노드를 교환한다.
            /*int temp = nums[0];
            nums[0] = nums[nums.length-1-i];
            nums[nums.length-1-i] = temp;*/

            //지금 이 문제에서 해야하는 것은 정렬이 아니라, 그냥 루트(힙에서 가장 큰 원소)를 제거하는 것이기 때문에 교환을 할 필요가 없다.
            nums[0] = nums[nums.length-1-i];

            //맨 끝 부분을 제외하고 스며내리기
            percolateDown(nums,nums.length-2-i,0);//nums.length-1-1-i
            //nums.length-1에서 i를 뺴야 하는데, k를 뺴서 계속 틀리고 있었음.
        }

        //마지막으로 힙에서 루트를 꺼내서 반환.
        return nums[0];
    }

    //말단 서브트리의 루트부터 최상위 루트까지 스며내리기를 해서 완전이진트리를 힙구조로 수선한다.
    private void buildHeap(int[] nums) {
        int buildStartIdx;

        //nums의 길이가 짝수인지, 홀수인지에 따라 말단 서브트리의 루트를 구하는 공식이 다르다. <- 아님. k번째 원소의 부모는 (k-1)/2다.
        buildStartIdx = (nums.length-2)/2;//nums.length-1-1

        for (int rootIdx = buildStartIdx; rootIdx >= 0 ; rootIdx--) {
            percolateDown(nums, nums.length-1, rootIdx);
        }
    }

    //스며내리기
    private void percolateDown(int nums[], int lastNodeIdx, int rootIdx) {//lastNodeIdx는 힙의 마지막 노드의 인덱스를 가리킨다.
        if (rootIdx*2+1 <= lastNodeIdx) {//왼쪽 자식노드가 존재하면
            int biggerChildIdx = rootIdx*2+1;
            if (rootIdx*2+2 <= lastNodeIdx) {//오른쪽 자식 노드가 존재하면
                if (nums[biggerChildIdx] < nums[rootIdx*2+2]) {
                    biggerChildIdx = rootIdx*2+2;//왼쪽과 오른쪽 자식 중 더 큰 것의 인덱스를 biggerChildIdx에 저장한다.
                }
            }

            if (nums[rootIdx] < nums[biggerChildIdx]) {//루트가 자식보다 작으면
                int temp = nums[rootIdx];//루트와 자식을 교환
                nums[rootIdx] = nums[biggerChildIdx];
                nums[biggerChildIdx] = temp;

                //루트인덱스를 교환한 자식 노드의 인덱스로 바꾸고, percolateDown()을 재귀호출한다.
                rootIdx = biggerChildIdx;
                percolateDown(nums, lastNodeIdx, rootIdx);//재귀호출
            }
        }
    }
}
