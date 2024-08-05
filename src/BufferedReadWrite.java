/*Scanner하고 System.out.println()이 편하긴 한데 속도가 느림
파이썬에서 sys.input.readline()같이 빠른 애가 필요함.

그래서 대안으로 쓸 수 있는게 BufferedReader, BufferedWriter다.
얘네는 이름 그대로 버퍼를 사용한다. 하나씩 입력받고 내보내는게 아니라 버퍼에 채워놓았다가 꽉 차면 한번에 내려보냄

Scanner는 띄어쓰기와 개행문자를 경계로 하여 입력 값을 인식한다.(next() 메서드가 공백까지만 입력을 받아오는 것을 얘기하는 듯) 그래서 따로 가공할 필요가 없어서 편함.
가공할 필요가 없다는 말은 숫자를 입력받고 싶을 때 scanner.nextInt()바로 이렇게 받으면 된다는거다.
BufferedReader는 입력 받은 데이터가 String으로 고정돼서 데이터를 원하는 타입으로 가공하는 작업이 필요하다.

Scanner는 지원해주는 메소드가 많고, 사용하기 쉽기 때문에 많이 사용하지만, 버퍼 사이즈가 1024 char이기 때문에
많은 입력을 필요로 할 경우에는 성능상 좋지 못한 결과를 불러온다.*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BufferedReadWrite {
    public static void main(String[] args) throws IOException {

        //BufferedReader 인스턴스 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //읽을 때는 readLine()메서드를 사용한다. 이 메서드는 반드시 IOException 체크예외를 처리해줘야 한다.
        String s = br.readLine();
        System.out.println(s);

        //데이터 가공
        //BufferedReader를 통해 읽어온 데이터는 개행문자 단위(Line 단위)로 나누어진다.
        //만약 이를 공백 단위로 데이터를 가공하고자 하면 따로 작업을 해주어야 한다. 이때 사용하는 것이 StringTokenizer나 String.split() 함수
        s = br.readLine();

        StringTokenizer stringTokenizer = new StringTokenizer(s);
        System.out.println(stringTokenizer.nextToken());
        System.out.println(stringTokenizer.nextToken());

        System.out.println(Arrays.toString(s.split(" ")));

        //BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 선언
        s = "hello";
        bw.write(s);//출력
        bw.newLine(); // 줄바꿈
        bw.flush(); // 남아있는 데이터 모두 출력
        bw.close();//입력 스트림을 닫고, 사용하던 자원을 해제

        /*그리고 BufferedWriter의 경우 버퍼를 잡아 놓았기 때문에 반드시 사용한 후에, flush()/ close()를 해주어야 한다.
        close()를 하게되면, 출력 스트림을 아예 닫아버리기 때문에 한번 출력후, 다른 것도 출력하고자 한다면 flush()를 사용하면 된다.*/
    }
}

