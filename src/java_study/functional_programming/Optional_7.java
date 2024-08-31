package java_study.functional_programming;

import java.util.List;
import java.util.Optional;

public class Optional_7 {
    public static void main(String[] args) {
        //Optional클래스 소개

        //nullPointerException을 피하고자 할 때 사용한다.

        //앞에서도 봤지만 max1()는 Optional을 반환함.
        Optional<Integer> max1 = List.of(23, 14, 626, 123, 23).stream().filter(e -> e % 2 == 0).max((n1, n2) -> Integer.compare(n1, n2));
        System.out.println(max1.get());

        //Optional에 대해 검사를 할 수도 있다.
        System.out.println(max1.isPresent());

        //이번에는 null이 나오게끔 해보겠음
        Optional<Integer> max2 = List.of(23, 123, 23).stream().filter(e -> e % 2 == 0).max((n1, n2) -> Integer.compare(n1, n2));
        //System.out.println(max2.get());//NoSuchElementException: No value 예외 발생

        System.out.println(max2.isPresent());//false

        //값이 없으면 기본값을 반환하게 할 수 있음.
        System.out.println(max2.orElse(0));

        //값이 있다면 최대값을, 아니면 기본값이 0을 반환하게 함.
        Integer max3 = List.of(23, 14, 626, 123, 23).stream().filter(e -> e % 2 == 0).max((n1, n2) -> Integer.compare(n1, n2)).orElse(0);


        //정리하자면 Optional은 null을 반환받을 수도 있는 상황에서 null을 대신해서 받는 타입이다.
        //값을 꺼내기 전에 검사할 수도 있고, null일 경우 반환할 기본값을 지정할 수도 있으며, 확신이 있다면 바로 get()으로 꺼내도 된다.
    }
}
