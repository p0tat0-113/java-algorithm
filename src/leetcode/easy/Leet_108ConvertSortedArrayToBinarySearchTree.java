package leetcode.easy;

/*
처음으로 풀어보는 이진 탐색 트리 문제다.
정렬된 배열을 주면, 그걸가지고 양쪽 서브트리의 깊이가 같은 탐색트리를 만들라고 한다.
일단 처음에는 좀 당황했는데 다시 생각해보니까 그리 어렵지는 않음.
정렬된 배열이 들어오기 때문에 중간값을 루트로 해서 나머지 노드들을 삽입하면 된다.

테스트 케이스로 [1,1,]을 넣어서 확인해보니까 입력되는 배열에 중복된 숫자는 없음.

처음 시도는 너무 생각없이 무지성으로 풀었음. 문제를 잘못 이해했다. 그냥 무지성으로 배열 앞에서부터 숫자를 트리에 때려박는 식으로는 절대 풀리지 않음.
양쪽 서브트리의 깊이가 같아야 한다는 것은 트리의 균형이 맞아야 한다는 얘기다.
배열의 중앙값을 트리에 넣고, 증앙값 양쪽으로 나눠진 배열에서 중앙값을 처음에 들어간 노드의 자식으로 넣고... 하는 과정을 재귀적으로 반복해야 한다.
*/

import leetcode.TreeNode;

public class Leet_108ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, null, 0, nums.length-1);
    }

    //근데 밑에 작성한 process()메서드가 아무리 봐도 뭔가 요상해서 잘 생각해보니까 insert메서드를 쓸 필요도 없다.
    //[1,2,3,4,5]
    private TreeNode process(int[] nums, TreeNode root, int s, int e){
        if (s <= e) {
            int rootIdx = (s+e)/2;
            root = new TreeNode(nums[rootIdx]);//배열의 중앙값으로 새로운 노드를 생성

            //양쪽으로 나눠진 배열로 재귀호출
            root.left = process(nums, root, s, rootIdx-1);
            root.right = process(nums, root, rootIdx+1, e);

            return root;//만들어진 노드의 참조값을 반환한다. 그러면 이 스택을 호출한 스택의 root.left = process(...); root.right = process(...); 이 둘 중 하나의 반환값이 되어 부모 노드의 자식이 되는 것이다.
            //이진탐색트리의 삽입과정을 응용함.
        }
        return null;
    }

    //분할정복, 재귀방식으로 풀어야 한다. 중앙인덱스의 숫자를 트리에 넣고, 중앙 인덱스를 기준으로 양쪽으로 분할하는 과정을 재귀적으로 반복해야 한다.
    //[1,2,3,4,5]
    /*private TreeNode process(int[] nums, TreeNode root, int s, int e){
        if (s <= e) {
            int rootIdx = (s+e)/2;
            root = insert(root, nums[rootIdx]);

            process(nums, root, s, rootIdx-1);
            process(nums, root, rootIdx+1, e);
        }
        return root;
    }*/

    //이렇게 바보같이 푸는거 아님.
    /*public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        nums[nums.length/2] = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num == Integer.MAX_VALUE) {
                continue;
            }

            root = insert(root, num);
        }

        return root;
    }*/

    /*private TreeNode insert(TreeNode root, int key){
        if (root == null) {
            return new TreeNode(key);
        }

        if(root.val > key) {//key가 root보다 작음, 왼쪽으로 분기해야한다.
            root.left = insert(root.left, key);
        } else if (root.val < key) {
            root.right = insert(root.right, key);
        }

        return root;
    }*/
}
