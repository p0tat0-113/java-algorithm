package baekjoon;/*문제
화은이는 이번 영어 시험에서 틀린 문제를 바탕으로 영어 단어 암기를 하려고 한다. 그 과정에서 효율적으로 영어 단어를 외우기 위해 영어 단어장을 만들려 하고 있다. 화은이가 만들고자 하는 단어장의 단어 순서는 다음과 같은 우선순위를 차례로 적용하여 만들어진다.

자주 나오는 단어일수록 앞에 배치한다.
해당 단어의 길이가 길수록 앞에 배치한다.
알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
 
$M$보다 짧은 길이의 단어의 경우 읽는 것만으로도 외울 수 있기 때문에 길이가
$M$이상인 단어들만 외운다고 한다. 화은이가 괴로운 영단어 암기를 효율적으로 할 수 있도록 단어장을 만들어 주자.

입력
첫째 줄에는 영어 지문에 나오는 단어의 개수
$N$과 외울 단어의 길이 기준이 되는
$M$이 공백으로 구분되어 주어진다. (
$1 \leq N \leq 100\,000$,
$1 \leq M \leq 10$)

둘째 줄부터
$N+1$번째 줄까지 외울 단어를 입력받는다. 이때의 입력은 알파벳 소문자로만 주어지며 단어의 길이는
$10$을 넘지 않는다.

단어장에 단어가 반드시 1개 이상 존재하는 입력만 주어진다.*/

/*HashMap에 key로 영어단어, value로 등장 횟수를 저장 한 다음. EntrySet으로 꺼낸 다음에
List에 담아서 Comparator를 잘 설계해서 정렬하면 되지 않을까 싶음.

처음 설계한 대로 코드는 완성했음. 근데 시간 제한 조건을 맞추려면 입출력 속도를 향상시켜야 함.*/

import java.io.*;
import java.util.*;

public class Baek20920 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());//공백 문자를 기준으로 토큰화
        int iter = Integer.parseInt(tokenizer.nextToken());
        int minimumLength = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < iter; i++) {

            String inputWord = br.readLine();

            if (inputWord.length() >= minimumLength){//minimumLength보다 짧은 단어는 패스
                hashMap.put(inputWord,hashMap.getOrDefault(inputWord,0)+1);//해당 단어가 없으면 새로 추가, 이미 있으면 갯수를 1추가
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());//EntrySet을 얻은 다음에 정렬을 위해 다시 List에 담음
        list.sort(new comparator());//구현한 Comparator를 이용해서 정렬

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : list) {
            sb.append(entry.getKey()+"\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush(); // 버퍼를 비움
        bw.close(); // BufferedWriter를 닫음
        //두 코드를 추가하니까 제대로 작동됨
    }

    private static class comparator implements Comparator<Map.Entry<String,Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {

            if (e1.getValue().compareTo(e2.getValue()) == 0){//처음에는 등장 횟수로 비교
                if(Integer.valueOf(e1.getKey().length()).compareTo(e2.getKey().length()) == 0){//단어의 길이로 비교
                    return e1.getKey().compareTo(e2.getKey());//단어의 알파벳 사전 순으로 비교
                } else {
                    return Integer.valueOf(e1.getKey().length()).compareTo(e2.getKey().length())*-1;//내림차순
                }
            } else {
                return e1.getValue().compareTo(e2.getValue())*-1;//내림차순
            }
        }
    }
}
