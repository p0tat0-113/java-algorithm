import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BufferedWritePerformanceTest {
    public static void main(String[] args) throws IOException {
        //100000만 개의 문자를
        //BufferdWrite로 개별적으로 출력하는게 빠른지,
        //아니면 StringBuilder로 append 한 다음에 한 번에 출력하는게 빠른지 테스트

        int COUNT = 10000;

        Long startTime = System.currentTimeMillis();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < COUNT; i++) {
            bufferedWriter.write("string");
            bufferedWriter.newLine();
        }
        Long endTime = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        Long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            sb.append("strings"+"\n");
        }
        bufferedWriter.write(sb.toString());
        Long endTime1 = System.currentTimeMillis();

        System.out.println("소요시간: "+(endTime-startTime)+"ms");
        System.out.println("소요시간: "+(endTime1-startTime1)+"ms");

        //실험 결과 둘이 또이또이 함.
    }
}
