package leetcode.easy;

/*
처음으로 풀어보는 이진 탐색 트리 문제다.
정렬된 배열을 주면, 그걸가지고 양쪽 서브트릐의 깊이가 같은 탐색트리를 만들라고 한다.
일단 처음에는 좀 당황했는데 다시 생각해보니까 그리 어렵지는 않음.
정렬된 배열이 들어오기 때문에 중간값을 루트로 해서 나머지 노드들을 삽입하면 된다.

테스트 케이스로 [1,1,]을 넣어서 확인해보니까 입력되는 배열에 중복된 숫자는 없음.
*/

import leetcode.TreeNode;

public class Leet_108ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        nums[nums.length/2] = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num == Integer.MAX_VALUE) {
                continue;
            }
        }

        return root;
    }

    private TreeNode insert(TreeNode root, int key){
        if (root == null) {
            return new TreeNode(key);
        }


    }
}
