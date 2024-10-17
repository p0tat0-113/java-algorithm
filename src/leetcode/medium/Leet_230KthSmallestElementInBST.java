package leetcode.medium;

/*
이진탐색트리에서 k번째로 작은 작은 원소를 찾는 문제다.
어떻게 풀어야할지 잠깐 고민을 했는데 중위순회 방식을 쓰면 쉽게 풀릴 것 같다.
이제까지 중위순회를 쓴건 BST를 출력할 때 뿐이었지만 중위순회를 쓰면 가장 작은 수부터 순서대로 출력하게됨. 이걸 이용하면 될 듯.
1 <= k <= n <= 104
*/

import leetcode.TreeNode;

public class Leet_230KthSmallestElementInBST {
    public static void main(String[] args) {

    }

    int k = 0;
    int result;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        process(root);
        return result;
    }

    //처음에는 process에서 직접 k == 0일 때의 key를 반환하게 하려고 했는데 그렇게 하려니까 리턴하는게 너무 복잡한 것 같아서 그냥 인스턴스 변수를 이용해서 해결했다.
    //중간에 sout대신 k--하는 과정이 들어간 것을 제외하면 내가 이제까지 쓰던 중위순회 코드랑 똑같다.
    private void process(TreeNode root) {
        if (root != null) {
            process(root.left);
            k--;
            if (k == 0) {//k == 0이 되면, 그러니까 k번째로 작은 수를 찾으면 result에 현재 key를 저장한다. 그리고 바로 return;
                result = root.val;
                return;
            }
            process(root.right);
        }
    }
}

