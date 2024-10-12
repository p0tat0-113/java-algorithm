package leetcode.medium;

import leetcode.TreeNode;
import java.util.HashMap;

/*
https://imgur.com/a/NgiUUHg
전위순회와 중위순회 탐색의 결과 배열을 서로 보완시켜서 원래의 트리를 만들어내야 하는 문제다.
어떻게 할까 고민하다가 분할정복 방식으로 하니까 풀렸음.
좀 더 고민하다가 map을 도입하니까 속도도 빨라졌음.
*/

public class Leet_105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {

    }

    int[] preorder;
    int[] inorder;
    HashMap<Integer,Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);//inorder배열의 각 숫자들이 어떤 인덱스에 위치해있는지 기록한다. 문제 조건 상 각 숫자들이 unique하기 때문에 이렇게 저장하는 것이 가능하다.
        }

        return process(0, 0, inorder.length-1);
    }

    private TreeNode process(int rootIdx, int s, int e){
        if (s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            //inorder에서 root의 위치를 확인한다.
            int rootIdxInInorder = map.get(preorder[rootIdx]);//매번 배열을 순회하면서 찾지 않고, 그냥 처음에 HashMap을 하나 만들어서 접근을 시키니까 1ms까지 성능개선에 성공함. 내 접근방식에는 문제가 없었음.
            int k = rootIdxInInorder-s+1;//root의 inorder부분 배열 내에서의 등수

            root.left = process(rootIdx + 1, s, rootIdxInInorder-1);
            root.right = process(rootIdx + k, rootIdxInInorder+1, e);

            return root;
        }
        return null;
    }

    //처음의 코드
    /*private TreeNode process(int rootIdx, int s, int e){
        if (rootIdx < preorder.length && s <= e) {
            TreeNode root = new TreeNode(preorder[rootIdx]);

            root.left = process(rootIdx + 1, s, rootIdx-1);
            root.right = process(rootIdx + 2, rootIdx+1, e);

            return root;
        } else {
            return null;
        }
    }*/
}
