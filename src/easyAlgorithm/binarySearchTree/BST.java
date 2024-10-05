package easyAlgorithm.binarySearchTree;

import java.util.Iterator;

public class BST {
    public static void main(String[] args) {
        BST bst = new BST();

        /* 트리에 다음 키 삽입
                  50
               /      \
             30       70
            /  \     /  \
          20   40   60   80
        */
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        bst.iterate();

        System.out.println("bst.search(50) = " + bst.search(50));
        System.out.println("bst.search(10) = " + bst.search(10));
    }

    private Node root;

    public BST() {
    }

    //이진탐색트리 중위순회
    public void iterate() {
        iterateProcess(root);
        System.out.println();
    }

    private void iterateProcess(Node root){
        if (root != null) {
            iterateProcess(root.left);
            System.out.print(root.key + " ");
            iterateProcess(root.right);
        }
    }

    //이진팀색트리 삽입
    public void insert(int key){
        root = insertNodeProcess(root, key);
    }

    private Node insertNodeProcess(Node root, int key){
        //서브트리가 비어있는 경우 키로 key를 가진 새로운 노드를 생성하고, 그 참조값을 반환한다.
        if (root == null) {
            return new Node(key);
        }

        if (root.key > key) {//key가 root의 key보다 작으면 왼쪽 서브트리를 탐색한다.
            root.left = insertNodeProcess(root.left, key);//삽입할 자리를 찾으면 위의 코드에서 새로운 노드를 생성하고 참조값을 반환한다. 그 참조값이 root.left에 저장되면서 새로운 노드가 삽입된 것이다.
        } else if (root.key < key) {//key가 root의 key보다 크면 오른쪽 서브트리를 탐색한다.
            root.right = insertNodeProcess(root.right, key);//삽입할 자리를 찾으면 위의 코드에서 새로운 노드를 생성하고 참조값을 반환한다. 그 참조값이 root.left에 저장되면서 새로운 노드가 삽입된 것이다.
        }
        //key가 root의 key와 같으면 삽입을 하지 않는다. 이진 탐색 트리는 중복된 키를 허용하지 않음.

        return root;//바닥을 찍고 올라온 다음 결국 원래 처음 입력받은 root를 반환하게 된다.
    }

    //이진탐색트리 검색
    public boolean search(int key){
        return searchProcess(root, key);
    }

    private boolean searchProcess(Node root, int key){
        if (root != null) {
            if (root.key == key) {//key와 root의 key가 같다면 찾은 것.
                return true;
            } else if (root.key > key) {//key가 root의 key보다 작으면 왼쪽 서브트리를 탐색한다.
                return searchProcess(root.left, key);
            } else {//key가 root의 key보다 크면 오른쪽 서브트리를 탐색한다.
                return searchProcess(root.right, key);
            }
        }
        return false;//끝내 key와 같은 값을 가진 노드를 찾지 못하고 리프노드의 밑에 도달하면 false를 반환한다.
    }

    //이진탐색트리의 삭제는 자고 일어나서 마저 하자....
}
