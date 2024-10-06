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

        bst.delete(20);
        bst.delete(40);
        bst.iterate();

        bst.delete(50);
        bst.iterate();

        bst.delete(10);//존재하지 않는 key를 삭제하려는 경우 아무 일도 일어나지 않음.
        bst.iterate();
    }

    private Node root;

    public BST() {
    }

    //이진탐색트리 중위순회
    public void iterate() {
        iterateProcess(root);
        System.out.println();
    }

    private void iterateProcess(Node root) {
        if (root != null) {
            iterateProcess(root.left);
            System.out.print(root.key + " ");
            iterateProcess(root.right);
        }
    }

    //이진탐색트리 검색
    public boolean search(int key) {
        return searchProcess(root, key);
    }

    private boolean searchProcess(Node root, int key) {
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

    //이진팀색트리 삽입
    public void insert(int key) {
        root = insertNodeProcess(root, key);
    }

    private Node insertNodeProcess(Node root, int key) {
        //서브트리가 비어있는 경우 키로 key를 가진 새로운 노드를 생성하고, 그 참조값을 반환한다.
        if (root == null) {
            return new Node(key);
        }

        if (root.key > key) {//key가 root의 key보다 작으면 왼쪽 서브트리를 탐색한다.
            root.left = insertNodeProcess(root.left, key);//삽입할 자리를 찾으면 위의 코드에서 새로운 노드를 생성하고 참조값을 반환한다. 그 참조값이 root.left에 저장되면서 새로운 노드가 삽입된 것이다.
        } else if (root.key < key) {//key가 root의 key보다 크면 오른쪽 서브트리를 탐색한다.
            root.right = insertNodeProcess(root.right, key);//삽입할 자리를 찾으면 위의 코드에서 새로운 노드를 생성하고 참조값을 반환한다. 그 참조값이 root.left에 저장되면서 새로운 노드가 삽입된 것이다.
        }
        //남은 케이스는 root != null && root.key == key밖에 없음. root의 key와 같으면 삽입을 하지 않는다. 이진 탐색 트리는 중복된 키를 허용하지 않음.

        return root;//바닥을 찍고 올라온 다음 결국 원래 처음 입력받은 root를 반환하게 된다.
    }

    //이진탐색트리 삭제
    public void delete(int key) {
        root = deleteProcess(root, key);
    }

    private Node deleteProcess(Node root, int key) {
        //트리가 비어있으면 반환
        if (root == null) {
            return root;
        }

        /*
                  50
               /      \
             30       70
            /  \     /  \
          20   40   60   80
        */
        //우선 삭제할 노드를 찾는다. 삽입메서드에서 삽입할 위치를 찾는 부분과 비슷하다. 찾으면 else{}로 넘어가서 삭제작업을 하는거고,
        //찾지 못하면 if (root == null) {}에서 null이 반환되고, 그 이후부터는 코드 최하단에서 원래의 root를 그대로 반환하면서 다시 타고 올라가는거다.
        if (root.key > key) {
            root.left = deleteProcess(root.left, key);
        } else if (root.key < key) {
            root.right = deleteProcess(root.right, key);
        } else {//(root.key == key) 삭제할 노드를 찾은 경우

            //노드를 삭제하는 것은 세가지 케이스로 나뉜다.
            //1.삭제하려는 노드가 자식을 가지고 있지 않은 경우 <- 리프노드인 경우이기 때문에 그냥 버리면 된다.
            //2.삭제하려는 노드가 왼쪽과 오른쪽 중 하나의 자식만 가지고 있는 경우 <- 자식의 참조값을 반환하게 하면 된다.
            //3.삭제하려는 노드가 양쪽에 모두 자식을 가지고 있는 경우 <- 이 케이스가 좀 까다롭다.

            //이 조건문으로 케이스 1,2를 모두 걸러낸다.
            if (root.left == null && root.right == null) {//자식이 없는 경우
                return null;
            } else if (root.left != null && root.right == null) {//왼쪽 자식만 있는 경우
                return root.left;
            } else if (root.left == null && root.right != null) {//오른쪽 자식만 있는 경우
                return root.right;
            } else {//(root.left != null && root.right != null) 케이스3, 양쪽에 모두 자식을 가지고 있는 경우
                //책에 나오는 것과 똑같게 수정함.

                //삭제하려는 노드 s와 그 부모 parent를 찾고, root의 키를 s의 키로 대체
                Node s = root.right;
                Node parent = s;
                while (s.left != null) {
                    parent = s;
                    s = s.left;
                }
                root.key = s.key;

                //s를 삭제하는 과정
                if (s == root.right) {
                    root.right = s.right;
                } else {
                    parent.left = s.right;
                }
            }
        }

        return root;
    }
}
