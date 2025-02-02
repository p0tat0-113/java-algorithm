package leetcode.medium;

/*
문제가 좀 복잡하다. 쉽지 않음.

일단 내가 파악하기로는 이 문제의 그래프는 모든 정점들 사이에 간선이 존재한다.
그리고 몇몇 정점들 사이에 단방향의 special roads 일종의 지름길이 존재한다. <- 잠깐만 이거 쓰고 보니까 갑가지 생각이 드는게
정점들 간의 간선 가중치를 인접리스트가 아니라 인접 행렬로 관리하면 되겠네!!! 마침 또 문제 조건이
startX <= x1i, x2i <= targetX
startY <= y1i, y2i <= targetY 이렇게 되어있다. 이거 인접 행렬로 관리하라는 것 같네.
가중치는 무조건 다 양수임.

그런데 인접 행렬로 최단거리 알고리즘을 짜야되는건 또 처음이네... 일단 모든 정점들 사이에 가중치가 존재한다는 것.
그리고 특정 정점들 사이에만 단방향으로 지름길이 존재한다는게 문제를 어렵게 만든다.

아 근데 잠깐만 이거 인접행렬로 관리하려면 문제를 또 살짝 비틀어야 하네. 지금 정점들이 각 xy좌표로 되어 있어서.... 흠
각 정점을 그냥 label번호로 생각하고, 좌표는 거리 계산을 위해서만 사용하는 식으로 써야할 것 같은데...

좋아 그러면 최단거리 알고리즘이 돌아가기 전의 인접행렬은 어떤 상태더라?
대각선 방향으로 대칭인 상태인 건데, 지금 special roads들 떄문에 일부 대칭이 깨진 상태일거야.

각 정점의 distance값을 저장하는 배열은 따로 두어야 함.

--------------------------------------
어떻게 어거지로 풀긴 풀었는데 성능이 매우 좋지 못하다. 75ms로 33%를 땄음. 주류와는 한참 멀리 떨어져 있다.
다익스트라 알고리즘을 돌리기 위한 준비 과정이 너무 복잡하다. 인접행렬을 채우는 과정에서 무조건 n^2의 시간이 소요됨.
리팩토링이 필요하다....
*/

import java.util.*;

public class Leet_2662MinimumCostOfAPathWithSpecialRoads {
    public static void main(String[] args) {
        Leet_2662MinimumCostOfAPathWithSpecialRoads leet = new Leet_2662MinimumCostOfAPathWithSpecialRoads();
        leet.minimumCost(new int[] {1,1}, new int[] {4,5}, new int[][] {{1,2,3,3,2}, {3,4,4,5,1}});
        leet.minimumCost(new int[] {3,2}, new int[] {5,7}, new int[][] {{5,7,3,2,1}, {3,2,3,4,4}, {3,3,5,5,5}, {3,4,5,6,6}});
        leet.minimumCost(new int[] {1,1}, new int[] {10,4},
                new int[][] {
                {4, 2, 1, 1, 3},
                {1, 2, 7, 4, 4},
                {10, 3, 6, 1, 2},
                {6, 1, 1, 2, 3}
                }
        );

        leet.minimumCost(new int[] {1,1}, new int[] {8,7},
                new int[][] {
                        {7, 7, 8, 7, 2},
                        {6, 5, 1, 7, 4},
                        {6, 1, 7, 7, 1},
                        {8, 6, 4, 7, 2}
                }
        );
    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        //start와 target이 같은 경우 예외 처리
        if (start[0] == target[0] && start[1] == target[1]) {
            return 0;
        }

        int vertexCount = 0;//정점의 수를 세는 카운터 변수
        HashMap<List<Integer>, Integer> xyMap = new HashMap<>();//[x,y]:레이블 쌍의 해시맵. 어떤 좌표가 이미 등록되어있는지 확인한다.
        ArrayList<List<Integer>> xyList = new ArrayList<>();//레이블 순서대로 좌표를 등록해놓은 리스트
        HashMap<Integer, List<int[]>> specialWeights = new HashMap<>();//special weights를 기록해놓은 해시맵, special roads출발 노드레이블:[도착레이블,가중치] 쌍의 해시맵이다.

        //우선 start와 target부터 등록한다.
        xyList.add(List.of(start[0], start[1]));
        xyMap.put(xyList.getLast(), vertexCount++);
        xyList.add(List.of(target[0], target[1]));
        xyMap.put(xyList.getLast(), vertexCount++);

        //specialRoads에서 좌표들을 뗴어내서 등록하고, special weight는 또 따로 specialWeights맵에 기록한다.
        for (int[] specialRoad : specialRoads) {
            List<Integer> specialStart = List.of(specialRoad[0], specialRoad[1]);
            List<Integer> specialTarget = List.of(specialRoad[2], specialRoad[3]);
            int startNum = 0;
            int targetNum = 0;

            //스타트와 타겟 각각 등록된 적 없는 좌표면 새로 등록한다.
            if (xyMap.containsKey(specialStart)) {
                startNum = xyMap.get(specialStart);
            } else {
                xyList.add(specialStart);
                xyMap.put(xyList.getLast(), vertexCount);
                startNum = vertexCount++;
            }

            if (xyMap.containsKey(specialTarget)) {
                targetNum = xyMap.get(specialTarget);
            } else {
                xyList.add(specialTarget);
                xyMap.put(xyList.getLast(), vertexCount);
                targetNum = vertexCount++;
            }

            //지름길 special weight등록
            if (specialWeights.get(startNum) == null) {
                specialWeights.put(startNum, new ArrayList<>());
            }
            specialWeights.get(startNum).add(new int[] {targetNum, specialRoad[4]});
        }

        //System.out.println(xyMap);
        //System.out.println(xyList);
        //System.out.println(specialWeights);
        //System.out.println(vertexCount);

        //인접행렬
        int[][] matrix = new int[vertexCount][vertexCount];

        //인접행렬 초기화 과정
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                matrix[i][j] = Math.abs(xyList.get(j).get(0) - xyList.get(i).get(0)) + Math.abs(xyList.get(j).get(1) - xyList.get(i).get(1));//거리 계산해서 집어넣음.
            }
        }

        //specialRoads를 적용한다.
        //special weight를 무지성으로 덮어씌우면 안되는 듯...? <- 맞다!!!!!!!!!!!! 틀리던게 이 이유였음ㅋㅋ
        //special weight가 더 작은 경우에만 덮어씌워야 한다. main에서 세번째 테스트케이스가 이거 때문에 틀리던 거였음.
        for (Map.Entry<Integer, List<int[]>> integerListEntry : specialWeights.entrySet()) {
            for (int[] ints : integerListEntry.getValue()) {
                if (matrix[integerListEntry.getKey()][ints[0]] > ints[1]) {
                    matrix[integerListEntry.getKey()][ints[0]] = ints[1];
                }
            }
        }

        //printMatrix(matrix);

        //여기까지는 해냈으니까 이제 그냥 최단거리 알고리즘 돌리듯이 하면 됨.
        int[] distances = new int[vertexCount];//각 정점들의 distance값을 저장하는 배열.
        Arrays.setAll(distances, e -> Integer.MAX_VALUE);
        distances[0] = 0;//시작정점은 0, 나머지는 INF로 초기화

        HashSet<Integer> setS = new HashSet<>();//이미 시작정점과 최단거리로 연결된 정점들을 집어넣은 set
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));//최소힙. 현재 상황에서 가장 낮은 distance값을 가진 [레이블:distance] 쌍의 배열을 반환함.
        minHeap.add(new int[] {0,0});
        
        while (!minHeap.isEmpty()) {
            int[] polled = minHeap.poll();
            //System.out.println(Arrays.toString(polled));
            //System.out.println(Arrays.toString(distances));
            
            int u = polled[0];
            int distanceU = polled[1];
            
            if (u == 1) {
                System.out.println("answer:" + distanceU);
                return distanceU;
            }

            //생각해보니까 이 부분을 빠져도 됨. 아래 쪽에서 setS에 들어가있는지 검사해서 막아주는 코드가 있기 때문에ㄱㅊ함. 이걸 넣는 습관이 어디에서 생겼는지 모르겠네.
//            if (setS.contains(u)) {
//                continue;
//            }
            setS.add(u);

            for (int v = 0; v < vertexCount; v++) {
                if (setS.contains(v)) {
                    continue;
                }

                int Wuv = matrix[u][v];
                int distanceV = distances[v];

                if (distanceV > distanceU + Wuv) {
                    distanceV = distanceU + Wuv;
                    minHeap.add(new int[] {v, distanceV});
                    distances[v] = distanceV;
                }
            }
            //System.out.println(Arrays.toString(distances));
            //System.out.println("----------------");
        }
        return 0;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
