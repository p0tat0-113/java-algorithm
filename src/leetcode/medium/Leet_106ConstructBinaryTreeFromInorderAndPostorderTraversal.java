package leetcode.medium;

import leetcode.TreeNode;
import java.util.HashMap;

/*
https://imgur.com/a/X4ox12J
105번 문제와 거의 유사하다. 105번 문제는 전위순회와 중위순회 결과를 던져줬지만, 이 문제는 후위순회와 중위순회를 던져줌.
그거 빼고는 똑같은 문제라고 보면 된다.
*/

public class Leet_106ConstructBinaryTreeFromInorderAndPostorderTraversal {

    int[] inOrder;
    int[] postOrder;
    HashMap<Integer, Integer> map = new HashMap<>();

    //이번에는 postOrder를 굳이 뒤집지 않고 구현해본다. 간단한 수정만으로도 가능하다.
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postOrder = postorder;
        this.inOrder = inorder;
        //각 숫자들이 inorder배열 내에서 몇 번 인덱스에 위치하는지 map에 저장한다. 문제 조건 상 숫자들이 unique하기 때문에 이런식으로 하는게 가능함.
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(postorder.length-1,0,inorder.length-1);
    }

    private TreeNode process(int rootIdx, int s, int e) {
        if (s <= e) {
            //postOrder[rootIdx]를 키로 하는 새로운 노드를 생성.
            TreeNode root = new TreeNode(postOrder[rootIdx]);

            //postOrder[rootIdx]가 분할된 inorder 부분 배열 내에서 '뒤에서' 몇 등인지 알아낸다.
            int rootIdxInInorder = map.get(postOrder[rootIdx]);
            int k = e - rootIdxInInorder + 1;

            //분할해서 재귀호출한다.
            root.left = process(rootIdx - k, s, rootIdxInInorder-1);
            root.right = process(rootIdx - 1, rootIdxInInorder+1, e);

            return root;
        }
        return null;
    }

    /*public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inOrder = inorder;

        //postorder를 뒤집는다. 이렇게 하면 preorder와 거의 비슷해짐. 루트를 먼저 찍고 오른쪽 자식, 그 다음에 왼쪽 자식으로 간다. 이 정도면 105번 문제와 거의 유사해짐.
        //근데 다시 생각해보니까 굳이 뒤집을 필요가 있나 싶기는 하네... 그냥 이대로 써도 되기 함. 뒤집는게 105번 문제와의 유사성을 유지하기에 더 좋긴 하지만...
        this.postOrder = new int[postorder.length];
        for (int i = 0; i < postOrder.length; i++) {
            postOrder[i] = postorder[postorder.length-1-i];
        }

        //각 숫자들이 inorder배열 내에서 몇 번 인덱스에 위치하는지 map에 저장한다. 문제 조건 상 숫자들이 unique하기 때문에 이런식으로 하는게 가능함.
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return process(0,0,inorder.length-1);
    }

    private TreeNode process(int rootIdx, int s, int e) {
        if (s <= e) {
            //postOrder[rootIdx]를 키로 하는 새로운 노드를 생성.
            TreeNode root = new TreeNode(postOrder[rootIdx]);

            //postOrder[rootIdx]가 분할된 inorder 부분 배열 내에서 '뒤에서' 몇 등인지 알아낸다.
            int rootIdxInInorder = map.get(postOrder[rootIdx]);
            int k = e - rootIdxInInorder + 1;

            //분할해서 재귀호출한다.
            root.left = process(rootIdx + k, s, rootIdxInInorder-1);
            root.right = process(rootIdx + 1, rootIdxInInorder+1, e);

            return root;
        }
        return null;
    }*/
}
