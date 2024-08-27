/*문제
정보초등학교의 연아는 여러 개의 자연수가 주어졌을 때, 이를 대표할 수 있는 대표 자연수에 대하여 연구하였다. 그 결과 어떤 자연수가 다음과 같은 성질을 가지면 대표 자연수로 적당할 것이라고 판단하였다.

“대표 자연수는 주어진 모든 자연수들에 대하여 그 차이를 계산하여 그 차이들 전체의 합을 최소로 하는 자연수이다.”

예를 들어 주어진 자연수들이 [4, 3, 2, 2, 9, 10]이라 하자. 이때 대표 자연수는 3 혹은 4가 된다. 왜냐하면 (4와 3의 차이) + (3과 3의 차이) + (2와 3의 차이) + (2와 3의 차이) + (9와 3의 차이) + (10과 3의 차이) = 1+0+1+1+6+7 = 16이고, (4와 4의 차이) + (3과 4의 차이) + (2와 4의 차이) + (2와 4의 차이) + (9와 4의 차이) + (10과 4의 차이) = 0+1+2+2+5+6 = 16으로 같으며, 이 두 경우가 차이들의 합을 최소로 하기 때문이다. 비교를 위하여 평균값인 5의 경우를 생각하여 보면, (4와 5의 차이) + (3과 5의 차이) + (2와 5의 차이) + (2와 5의 차이) + (9와 5의 차이) + (10과 5의 차이) = 1+2+3+3+4+5 = 18로 위의 두 경우보다 차이들의 합이 더 커짐을 볼 수 있다.

연아를 도와서 위의 성질을 만족하는 대표 자연수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 자연수의 개수 N이 입력된다. N은 1 이상 20,000 이하이다. 둘째 줄에는 N개의 자연수가 빈칸을 사이에 두고 입력되며, 이 수들은 모두 1 이상 10,000 이하이다.

출력
첫째 줄에 대표 자연수를 출력한다. 대표 자연수가 두 개 이상일 경우 그 중 제일 작은 것을 출력한다.*/

//부르트포스 및 정렬 문제.. 인 줄 알았으나, 이 문제를 훨씬 빠르게 푼 사람들이 있는 것을 확인했고, 어떻게 대표 자연수가 될 수 있을까를 잘 생각해보니까 답이 나왔음.
//입력받은 숫자들을 정렬한 다음에 중간 인덱스의 숫자를 뱉으면 됨!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baek2548_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        String[] strings = br.readLine().split(" ");

        //문자열 배열을 정수형 배열로 치환
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        Arrays.sort(arr);

        System.out.println(arr[(num-1)/2]);
    }
}
