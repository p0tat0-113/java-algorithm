package baekjoon;/*문제
온라인 저지에 가입한 사람들의 나이와 이름이 가입한 순서대로 주어진다. 이때, 회원들을 나이가 증가하는 순으로, 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 온라인 저지 회원의 수 N이 주어진다. (1 ≤ N ≤ 100,000)
둘째 줄부터 N개의 줄에는 각 회원의 나이와 이름이 공백으로 구분되어 주어진다. 나이는 1보다 크거나 같으며, 200보다 작거나 같은 정수이고, 이름은 알파벳 대소문자로 이루어져 있고, 길이가 100보다 작거나 같은 문자열이다. 입력은 가입한 순서로 주어진다.

출력
첫째 줄부터 총 N개의 줄에 걸쳐 온라인 저지 회원을 나이 순, 나이가 같으면 가입한 순으로 한 줄에 한 명씩 나이와 이름을 공백으로 구분해 출력한다.*/


//앞서 만든 코드들이 계속 메모리초과가 발생함. 그리고 생각을 해보니까 이 경우는 나이의 범위가 굉장히 좁다는 것을 이용해서 풀면 아주 빠르게 정렬하는 것도 가능할 것 같음.
//1살부터 200살까지의 각 나이를 대표하는 큐들을 담은 리스트를 만들고, 거기에 하나씩 넣는 식으로 하면 될 듯.
//약간 카운트 정렬 비스무리 함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Baek10814_4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        Queue<String[]>[] list = new Queue[200];//각 큐는 1살부터 200살까지의 나이의 사람을 담는다.
        for (int i = 0; i < 200; i++) {
            list[i] = new ArrayDeque<>();
        }

        for (int i = 0; i < num; i++) {
            String[] input = br.readLine().split(" ");
            list[Integer.parseInt(input[0])-1].offer(input);
        }

        StringBuilder sb = new StringBuilder();

        for (Queue<String[]> queue : list) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String[] strings = queue.poll();
                sb.append(strings[0]).append(" ").append(strings[1]).append("\n");
            }
        }

        System.out.println(sb);
    }
}
