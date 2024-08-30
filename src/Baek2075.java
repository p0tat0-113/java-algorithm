/*문제
N×N의 표에 수 N2개 채워져 있다. 채워진 수에는 한 가지 특징이 있는데, 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것이다. N=5일 때의 예를 보자.

12	7	9	15	5
13	8	11	19	6
21	10	26	31	16
48	14	28	35	25
52	20	32	41	49
이러한 표가 주어졌을 때, N번째 큰 수를 찾는 프로그램을 작성하시오. 표에 채워진 수는 모두 다르다.

입력
첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다. 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다. 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.

출력
첫째 줄에 N번째 큰 수를 출력한다.
*/

//입력으로 들어오는 테이블이 되게 독특하다. 모든 수는 자신의 한 칸 위에 있는 수보다 크다는 것, 즉 각 열을 이미 정렬된 상태라는 것이다.
//이 특성을 이용하면 효율적으로 정렬할 수 있을 듯. 재귀적으로 병합하면 될 듯 함. 우선순위큐를 쓰면 그냥 날먹 할 수 있을 것 같기도 한데...
//풀긴 풀었는데 뭔가 찝찝하네..

//개같이 시간 초과됨. 오버헤드를 줄여야 함. 스위칭 병합 정렬같이 주배열과 보조배열을 바꿔가면서 하면 되지 않을까...?
//그리고 비교횟수를 최대한 줄여야 하는데.. 지금의 방법은 잘못됐음.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Baek2075 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        ArrayList<ArrayDeque<Integer>> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new ArrayDeque<>());
        }

        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");

            for (int j = 0; j < num; j++) {
                list.get(j).offer(Integer.parseInt(input[j]));
            }
        }

        //System.out.println(list);

        ArrayDeque<Integer> temp;
        ArrayList<Integer> result = new ArrayList<>();

        for (ArrayDeque<Integer> deque : list) {
            temp = new ArrayDeque<>(result);
            result.clear();

            while(!temp.isEmpty() && !deque.isEmpty()) {
                if (temp.peek() < deque.peek()){
                    result.add(temp.poll());
                } else {
                    result.add(deque.poll());
                }
            }

            while(!temp.isEmpty()) {
                result.add(temp.poll());
            }
            while(!deque.isEmpty()) {
                result.add(deque.poll());
            }
        }

        //System.out.println(result);

        System.out.println(result.get(result.size()-num));
    }
}