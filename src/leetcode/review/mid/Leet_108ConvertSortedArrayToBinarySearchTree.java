package leetcode.review.mid;

public class Leet_108ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Leet_108ConvertSortedArrayToBinarySearchTree leet = new Leet_108ConvertSortedArrayToBinarySearchTree();
        System.out.println(leet.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
    }

    //정렬된 배열이 주어지면 이걸 가지고 균형이 맞는 이진탐색트리름 만들어내야 한다.
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length-1);
    }

    private TreeNode process(int[] nums, int s, int e){
        if (s <= e) {
            int mid = (s+e)/2;
            TreeNode root = new TreeNode(nums[mid]);

            //mid를 기준으로 분할해서 재귀호출한다.
            root.left = process(nums, s, mid-1);
            root.right = process(nums, mid+1, e);

            return root;
        }

        return null;
    }
}
