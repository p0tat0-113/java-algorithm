package java_study.functional_programming;

import java.util.List;

public class FunctionalProgrammingRunner {

    public static void main(String[] args) {
        List<String> list = List.of("Apple", "Banana", "Cat");

        printBasic(list);

        printWithFP(list);
    }

    //배열의 원소를 모두 출력하는 간단한 메서드
    private static void printBasic(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }

    //위의 메서드를 함수형 프로그래밍 방식으로 만들 수 있음.
    //지금 단계에서는 코드가 잘 이해되지 않을 수 있음.
    private static void printWithFP(List<String> list) {
        //.stream으로 리스트의 데이터로 흐름을 만듦
        //이 흐름 안에서 각각의 요소들을 print하는 것.
        list.stream().forEach(
                element -> System.out.println(element)
            ); //element -> 이건 람다 표현이라는 문법이다.
    }

    //for루프를 돌리면서 매 문자열을 가지고 무엇을 할지 말해주는 것이 아니라,
    //리스트를 스트림(흐름)으로 만들어준 다음에 forEach를 호출해서 각 요소들을 가지고 무엇을 할지 통과시켜 주는 것

    //내가 forEach안에 넣은 것은 데이터가 아니다. 함수다. 코드 조각을 넣은 것이다.
    //당장 문법은 신경쓰지 말자. 중요한 것은 함수 안에 함수를 매개변수로 넣었다는 것이다.

    //이것이 함수형 프로그래밍의 중요한 개념이다.
}
