package baekjoon;/*
문제
수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다. 이 좌표에 좌표 압축을 적용하려고 한다.

Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.

X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.

입력
첫째 줄에 N이 주어진다.

둘째 줄에는 공백 한 칸으로 구분된 X1, X2, ..., XN이 주어진다.

출력
첫째 줄에 X'1, X'2, ..., X'N을 공백 한 칸으로 구분해서 출력한다.
*/

/*배열에서 중복을 제거한 후, 오름차순으로 정렬
* 그리고 정렬된 배열에서 검색을 해서 각 숫자의 정렬 인덱스를 출력해야 함. 검색하는 과정이 문제임.
* 그런데 문제 조건을 보니 메모리가 512MB로 굉장히 널널함. 이걸 어떻게 잘 이용하며 될 것 같기도 한데
*
* 일단 내 계획은 입력받으면서 바로 set에 넣어서 중복을 제거하고, arraylist로 꺼낸 다음에 정렬을 돌림.
* 그리고 하나씩 값:인덱스 쌍으로 HashMap에 저장해서 검색을 빠르게 할 수 있게 함.
* 일단 이 방식으로 만들어 본 다음에 다른 방식으로도 만들어서 성능 비교를 해보면 좋을 듯.
*
* 다 만들었는데 개느림ㅋㅋ
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baek18870_1 {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> inputArr = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        String[] strings = br.readLine().split(" ");
        for (String string : strings) {
            inputArr.add(Integer.parseInt(string));
        }

        HashSet<Integer> set = new HashSet<>(inputArr);
        ArrayList<Integer> sortedList = new ArrayList<>(set);
        sortedList.sort(null);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <sortedList.size(); i++) {
            map.put(sortedList.get(i), i);
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : inputArr) {
            sb.append(map.get(integer)).append(" ");
        }
        System.out.println(sb);
    }
}
