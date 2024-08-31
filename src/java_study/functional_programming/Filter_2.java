package java_study.functional_programming;

//함수형 프로그래밍 filter

import java.util.List;

public class Filter_2 {
    public static void main(String[] args) {
        List<String> list = List.of("Apple", "Bat", "Cat", "Dog");

        System.out.println("===Normal===");
        //printBasic(list);
        printBasicWithFiltering(list);

        System.out.println("===FP===");
        //printWithFP(list);
        printWithFPWithFiltering(list);


        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);

        System.out.println("홀수만 출력");
        printOddNums(nums);

        System.out.println("짝수만 출력");
        printEvenNums(nums);

        //stream을 출력해보자.
        System.out.println(list.stream().toString());
        //java.util.stream.ReferencePipeline$Head@34c45dca 이런 것만 나옴.
    }

    //배열의 원소를 모두 출력하는 간단한 메서드

    private static void printBasic(List<String> list) {
        for (String string : list) {
            System.out.println(string);
        }
    }
    private static void printWithFP(List<String> list) {
        list.stream().forEach(
                element -> System.out.println(element)
        );
    }

    //'at'로 끝나는 단어만 출력하고 싶음
    private static void printBasicWithFiltering(List<String> list) {
        for (String string : list) {
            if (string.endsWith("at")) {
                System.out.println(string);
            }
        }
    }

    //위의 메서드를 함수형 프로그래밍으로 만들어보자.
    //filter라는 것을 추가하면 된다.
    private static void printWithFPWithFiltering(List<String> list){
        list.stream()
                .filter(element -> element.endsWith("at"))//이 조건을 만족시키는 것들만 통과시킨다. 논리가 참이라면 통과, 아니라면 아무것도 하지 않음.
                .forEach(element -> System.out.println(element));
    }
    //함수형 프로그래밍에서는 루프를 돌리는 것이 아니라, 각 요소 element에 무엇을 할지를 얘기해 주는 것이다.


    //함수형 프로그래밍 방식으로 홀수와 짝수만 걸러내서 출력하는 메서드를 만들어보자.
    private static void printEvenNums(List<Integer> list) {
        list.stream().filter(element -> element%2 == 1).forEach(element -> System.out.println(element));
    }

    private static void printOddNums(List<Integer> list) {
        list.stream().filter(element -> element%2 == 0).forEach(element -> System.out.println(element));
    }

}
