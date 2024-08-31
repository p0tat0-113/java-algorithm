package java_study.functional_programming;

import java.util.List;

public class LambdaExpression_4 {


    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5);

        //1. 람다 표현식에 대해서 알아본다.

        list.stream().reduce(0, (num1, num2) -> num1 + num2);
        //(num1, num2) -> num1 + num2 이 부분이 람다표현식이다. 짧게 함축해 놓은 것이다. 전에 배워서 알지만 인터페이스 구현과 인스턴스 생성을 동시에 하는 익명 클래스를 더 짧게 줄여놓은 것이다.

        //이렇게 할 수도 있다.
        int sum1 = list.stream().reduce(0, (num1, num2) -> {
            System.out.println(num1 + " + " + num2);
            return num1 + num2;
        });
        //한줄 정도로 단순하다면 return도 뺴고, 괄호도 빼고, 세미콜론까지 뺄 수 있다.
        System.out.println(sum1);


        //2. stream
        //stream은 숫자 스트림, 문자열 스트림도 있고 특정 객체 스트림도 있을 수 있다.
        //stream에는 2가지 연산이 있는데 '중간연산'과 '종단연산'이다.

        //앞에서 본 filter같은게 중간연산이다. 스트림을 받아서 연산을 하고, 연산결과로 또 다시 스트림을 뱉어낸다.

        //종단연산에서는 요소를 처리하고 소모한다. forEach, reduce 같은 것, 스트림 전체를 소모하거나 "하나의 값만" 반환한다.
    }
}
