package java_study.functional_programming;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Operation_6 {
    public static void main(String[] args) {
        //stream의 종단연산

        //종단연산의 결과는 보통 한 개의 값이다. 숫자들의 총합, 평균, 최소, 최대, 리스트
        //총합을 구하는 reduce는 이미 써봤음.

        System.out.println(IntStream.range(1, 11).reduce(0, (num1, num2) -> num1 + num2)); //1부터 10까지의 합, 55
        //전에는 리스트의 숫자 총합을 구하기 위해 향상된 for문을 돌리고, 임의로 선언한 지역변수에 값을 하나씩 더하는 식으로 많을 코드를 써야 했지만,
        //함수형 프로그래밍에서는 그런 것 따위 신경쓰지 않아도 된다.

        //max
        Optional<Integer> max = List.of(23, 13, 15, 776, 234, 1, 243).stream().max((n1, n2) -> Integer.compare(n1, n2));//max의 인자로 Comparator를 구현해서 넣어줘야 함. Comparator도 함수형으로 만들어본다.
        //원래는 new Comparator(){@Override} 어쩌구 하면서 복잡하게 코드를 짜야 했지만, 여기에서는 람다표현식으로 아주 간격하게 표현했다.
        //이는 함수형 인터페이스 덕분이라고 한다. 이대 대해서는 뒤에서 알려준다고 함.

        //그런데 Optional타입이 반환된다. Optional은 자바8부터 도입된 타입이다.
        //만약 list가 비어있다면 어떤 값이 반환될까? null이 반환된다. 그런데 null 사용은 좋지 않기 때문에 자바가 Optional을 도입한 것이다.
        //결과값이 null일 수 있는 상황에 도움을 주는 것.

        System.out.println(max.get());//get()으로 Optional에서 값을 꺼낸다.

        //min
        System.out.println(List.of(23, 13, 15, 776, 234, 1, 243).stream().min((n1, n2) -> Integer.compare(n1, n2)).get());

        //collect: stream을 list로 변환(원하는 자료형으로 변환)
        //리스트에서 홀수값들만 뽑아서 리스트로 반환해보자.
        System.out.println(List.of(2, 14, 23, 13, 78, 6).stream().filter(e -> e%2==1).collect(Collectors.toList()));


        //연습
        System.out.println("리스트에서 짝수 값만 뽑아서 리스트로 반환");
        System.out.println(List.of(2, 14, 23, 13, 78, 6).stream().filter(e -> e%2==0).collect(Collectors.toList()));

        System.out.println("리스트의 값들을 제곱한 값들을 리스트로 반환");
        System.out.println(IntStream.range(1,11).map(e -> e * e).boxed().collect(Collectors.toList()));
        //IntStream은 intStream을 반환함, 그래서 boxed()로 일반적인 스트림을 변환해줘야 한다.
    }
}
