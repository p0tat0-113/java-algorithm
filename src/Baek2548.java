import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baek2548 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minSum = 2100000000;
        int minNum = 10000;

        int num = Integer.parseInt(br.readLine());

        String[] strings = br.readLine().split(" ");

        //문자열 배열을 정수형 배열로 치환
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(strings[i]);
        }

        //부르트 포스 연산 및 최소값 구하기
        for (int i = 0; i < num; i++) {
            int sum = 0;
            int pivot = arr[i];
            for (int j = 0; j < num; j++) {
                sum += (arr[j] - pivot) >= 0 ? arr[j] - pivot : (arr[j] - pivot)*-1;
            }

            if (sum < minSum){
                minSum = sum;
                minNum = pivot;
            } else if (sum == minSum) {
                if (pivot < minNum){
                    minNum = pivot;
                }
            }
        }

        System.out.println(minNum);
    }
}
