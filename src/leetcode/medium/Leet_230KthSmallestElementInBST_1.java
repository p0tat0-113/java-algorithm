package leetcode.medium;

import leetcode.TreeNode;

/*
이미 푼 문제이지만, 알고리즘 실습 수업 과제로 나와서 다시 한 번 풀어본다.
이진탐색트리에서 k번째로 작은 수를 찾으면 되는 문제다.
푸는 방법을 모르면 이진탐색트리의 특성과 이걸 탐색하는 방법을 생각하느라 좀 시간이 걸릴 수 있지만,
'중위순회'에 대해서 알고있으면 쉽게 풀 수 있다.

1 <= k <= n <= 10^4
*/

public class Leet_230KthSmallestElementInBST_1 {
    public static void main(String[] args) {

    }

    int k = 0;
    int result = 0;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        process(root);//얘가 끝나면 result에 결과값이 들어가있음. 그대로 반환하면 된다.
        return result;
    }


    private void process(TreeNode root){
        if (root != null) {
            process(root.left);
            //이 부분이 sout가 아닌 것을 제외하면 중위순회로 트리의 값 순서대로 출력하는 것과 똑같다.
            k--;
            if (k == 0) {
                result = root.val;
                return;
            }
            process(root.right);
        }
    }
}

