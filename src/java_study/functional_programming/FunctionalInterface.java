package java_study.functional_programming;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionalInterface {
    public static void main(String[] args) {
        //람다 표현식을 쓸 때 화면 뒤에서 무슨 일이 일어나는지 알아본다.

        //리스트에서 홀수만 출력
        List.of(23,13,52,62).stream()
                .filter(e -> e%2==0)
                .forEach(e -> System.out.println(e));
        //화면 뒤에서 무슨 일이 일어나고 있을까?
        //어떻게 'e -> e%2==1'이 코드 조각이 filter()메서드에 전달되고 있을까?

        //filter의 인수 타입을 보면 Predicate라고 되어있다.
        //Stream<T> filter(Predicate<? super T> predicate);
        //Predicate인터페이스로 가보면 @FunctionalInterface라는 애노테이션이 붙어있고, boolean test(T t)라는 메서드를 하나 가지고 있다.
        //test()는 Predicate인터페이스에 있는 유일한 추상메서드이다.(바디가 없는 메서드)


        List.of(23,13,52,62).stream()
                .filter(new EvenNumverPredicate())//Predicate인터페이스를 구현한 클래스의 인스턴스를 집어넣음.
                .forEach(new PrintConsumer());

        //.filter(e -> e%2==0) 이 코드를 쓸 때 위와 같은 현상이 똑같이 일어난 것이다. 인터페이스 구현과 인스턴스 생성을 동시에 하는 익명클래스에서 더 발전한 것이 람다라고 배운적이 있지.
        //Predicate인터페이스는 테스트를 통과했는지 여부를 반환해주는 역할을 한다.
    }

    //Predicate인터페이스를 구현해본다. Predicate인터페이스에서 유일한 추상메서드인 test를 오버라이딩함.
    private static class EvenNumverPredicate implements Predicate<Integer>{
        @Override
        public boolean test(Integer number) {
            return number%2==0;
        }
    }

    //이번에는 forEach()가 인수로 받는 Consumer인터페이스를 구현해본다. Consumer인터페이스에서 유일한 추상메서드인 accept를 오버라이딩함.
    private static class PrintConsumer implements Consumer<Integer>{
        @Override
        public void accept(Integer number) {
            System.out.println(number);
        }
    }
}
