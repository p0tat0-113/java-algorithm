package java_study.functional_programming;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MethodReferences_9 {
    public static void main(String[] args) {
        //메서드 참조

        List.of("Dog", "Bat", "Cat", "Elephant").stream()
                .map(e -> e.length())
                .forEach(e -> System.out.println(e));

        List.of("Dog", "Bat", "Cat", "Elephant").stream()
                .map(String::length)
                //.forEach(System.out::println);//람다 표현식을 메서드 참조로 축약, 왼쪽에는 클래스 오른쪽에는 메서드
                .forEach(MethodReferences_9::print);//이렇게 쓰는 것, 스태틱 메서드 뿐만 아니라 인스턴스 메서드도 축약 가능

        //이렇게 메서드 참조를 하는 이유는 코드의 가독성을 높이기 위함이라고 한다.

        //연습
        Integer max1 = Stream.of(23, 12, 44, 33)
                .filter(e -> e % 2 == 0)
                .max((n1, n2) -> Integer.compare(n1, n2)).orElse(0);
        System.out.println(max1);

        //위 코드를 메서드 참조를 이용하게 바꿔보자.
        Integer max2 = Stream.of(23, 12, 44, 33)
                .filter(MethodReferences_9::isEven)
                .max(Integer::compare).orElse(0);
        System.out.println(max2);
        //이름 그대로 메서드를 직접 사용하는게 아니라, 메서드를 참조, 연결만 해놓는 느낌이네.


        /*이 시점에서 강의 맨 처음에 들었던 1등객 객체의 정의를 다시 한 번 짚어보자.
        1. 함수의 인자로 넣을 수 있다. <- filter(e -> e % 2 == 0)이런 식으로 이미 많이 사용했음.
        2. 변수에 저장할 수 있다.
        3. 함수의 리턴값으로 사용할 수 있다.

        이제 2,3번을 해결해보자.
        */

        //변수에 담는 것은 아래와 같다.
        Predicate<Integer> predicate1 = e -> e % 2 == 0;
        //함수의 리턴값으로 사용하는 것은 아래 returnPredicate메서드와 같다.
        Predicate<Integer> predicate2 = returnPredicate();

        //근데 이게 정말 함수를 변수에 담는거라고 할 수 있나? FunctionalInterface_8에서 람다표현식은 함수형 인터페이스를 구현하고 인스턴스를 생성하는 과정을 간략화 시킨 것이라고 확인하지 않았나?
        //맞다. 엄밀히 말하면 Predicate를 구현해서 test메서드를 오버라이딩한 클래스의 인스턴스를 담은 것이다. 아래코드와 같음.
        Predicate<Integer> predicate3 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num % 2 == 0;
            }
        };

        //그런데 함수형 프로그래밍 관점에서는, 이 과정을 "함수를 변수에 담았다"라고 이해하는 것이 더 직관적이다. 코드만 보면 진짜 그냥 함수를 변수에 넣고, 리턴값으로 던지는 것 같아 보인다.

        //자바에서 이게 가능한 이유는 다 함수형 인터페이스 덕분이다. 자바는 람다표현식에 맞춰서 내부적으로 함수형 인터페이스의 구현체를 생성한다.
        //https://chatgpt.com/share/cf1d8f4f-2230-40d2-b4e0-7a5c9fab3899
    }

    //임의로 출력 메서드를 하나 만들었음.
    public static void print(Integer num){
        System.out.println(num);
    }

    private static boolean isEven (Integer num){
        return num % 2 == 0;
    }

    private static Predicate<Integer> returnPredicate() {
        return e -> e % 2 == 0;
    }
}
