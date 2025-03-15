package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
무방향 연결그래프의 deepcopy를 만들어야 한다. 말 그대로 deepcopy다. 원래의 그래프와 독립적이면서도 특징들은 똑같아야 한다.

각 노드는 val과 인접 리스트를 가진다. val은 노드의 인덱스 값과 같다. 첫번째 노드는 val로 1을 가짐.

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
이런 식으로 구성된다.

먼저 어떻게 풀지 생각해보자.

일단 input으로 첫번째 Node를 받는다. 인접리스트 neighbors를 가지고 있다.
얘의 정보들을 복사해서 새로운 첫번째 clone노드를 만들어야 한다.

지금 topic이 DFS라서 결국 재귀적으로 작동하게 되는데, DFS함수 안에서 Node인스턴스를 생성하고, 그 Node인스턴스를 위쪽으로 반환해서 올리는 식으로 해도 좋을 것 같다. <-- 이 방식이 맞는 듯.

풀긴 풀었는데 28ms로 좀 느리다. 제일 빠른게 22ms, 23ms라서 별로 신경 안써도 될 것 같기는 한데, 걔네 코드 까보니까 내 코드랑 거의 똑같음ㅋㅋ 사람 생각하는게 다 똑같네.
그냥 넘어가자. 핵심은 똑같네.

뭐지 또 다시 제출하니까 이번에는 28ms 나옴ㅋㅋㅋ
*/

public class Leet_133CloneGraph {
    public static void main(String[] args) {

    }

    private HashMap<Integer, Node> nodesMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        return dfs(node);
    }

    private Node dfs(Node node) { // 원본 그래프의 노드 참조값을 입력받는다.
        int val = node.val;
        List<Node> neighbors = node.neighbors;

        if (nodesMap.containsKey(val)) { // 이미 만든 노드라면 이미 map에 저장해놓았을 것이기 때문에 그냥 꺼내서 반환하면 된다.
            return nodesMap.get(val);
        }

        Node newNode = new Node(val); // 처음에는 이 부분이 for 반복문 나중에 들어가 있었다. 그래서 맨 노드가 맨 처음 노드와 연결되어 있는 경우, map에는 아직 첫 노드가 저장되어 있지 않다 보니까 계속 무한히 재귀가 반복되면서 스택오버플로우가 터졌었음.
        nodesMap.put(val, newNode);

        for (int i = 0; i < neighbors.size(); i++) {
            newNode.neighbors.add(dfs(neighbors.get(i))); // 새롭게 카피한 노드의 참조값이 반환된다.
        }

        return newNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
