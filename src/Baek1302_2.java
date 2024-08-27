/*
문제
김형택은 탑문고의 직원이다. 김형택은 계산대에서 계산을 하는 직원이다. 김형택은 그날 근무가 끝난 후에, 오늘 판매한 책의 제목을 보면서 가장 많이 팔린 책의 제목을 칠판에 써놓는 일도 같이 하고 있다.

오늘 하루 동안 팔린 책의 제목이 입력으로 들어왔을 때, 가장 많이 팔린 책의 제목을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 오늘 하루 동안 팔린 책의 개수 N이 주어진다. 이 값은 1,000보다 작거나 같은 자연수이다. 둘째부터 N개의 줄에 책의 제목이 입력으로 들어온다. 책의 제목의 길이는 50보다 작거나 같고, 알파벳 소문자로만 이루어져 있다.

출력
첫째 줄에 가장 많이 팔린 책의 제목을 출력한다. 만약 가장 많이 팔린 책이 여러 개일 경우에는 사전 순으로 가장 앞서는 제목을 출력한다.

해시맵에 담고, 엔트리셋으로 뽑은 다음에 다시 리스트에 담아서 정렬하면 될 듯.
*/

//좀 더 빠르게 작동될 수 있도록 개선해보려고 함.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Baek1302_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        int maxNum = 0;
        String maxBookName = "~";

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0)+1);

            int count = map.get(book);
            if (maxNum < count){
                maxNum = count;
                maxBookName = book;
            } else if (maxNum == count) {
                if (book.compareTo(maxBookName) < 0){
                    maxBookName = book;
                }
            }
            //System.out.println(maxNum);
            //System.out.println(maxBookName);
        }

        System.out.println(maxBookName);
    }
}
