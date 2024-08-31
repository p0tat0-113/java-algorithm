package java_study.functional_programming;

import java.util.List;
import java.util.stream.IntStream;

public class Operation_5 {
    public static void main(String[] args) {
        //stream 중간 연산

        //정렬
        System.out.println("==sorted()==");
        List<Integer> list1 = List.of(5, 2, 1, 4, 10, 35);
        list1.stream().sorted().forEach(e -> System.out.println(e));

        //중복제거
        System.out.println("==distinct()==");
        List<Integer> list = List.of(1, 3, 2, 3, 4, 2, 5);
        list.stream().distinct().sorted().forEach(e -> System.out.println(e));

        //실행할 수 있는 중간연산의 갯수에는 제한이 없다.
        //종단연산은 마지막에 기입해서 스트립을 소모시키던가, 결과를 하나 반환해야 한다.

        //map: 각 요소에 어떤 연산을 해서 스트림으로 반환
        System.out.println("==map()==");
        list.stream().distinct().sorted().map(e -> e*e).forEach(e -> System.out.println(e));

        //연습
        System.out.println("1부터 10까지 제곱수 출력");
        IntStream.range(1,11).map(e -> e*e).forEach(e -> System.out.println(e));

        System.out.println("문자열을 소문자로 매핑");
        List<String> strings1 = List.of("Apple", "Cat", "Dog");
        strings1.stream().map(e -> e.toLowerCase()).forEach(e -> System.out.println(e));

        System.out.println("각 문자열의 길이를 출력");
        strings1.stream().map(e -> e.length()).forEach(e -> System.out.println(e));
    }
}
