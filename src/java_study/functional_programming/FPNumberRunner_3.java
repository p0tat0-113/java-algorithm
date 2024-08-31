package java_study.functional_programming;

import java.util.List;

public class FPNumberRunner_3 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);

        //이번에 해볼 것은 리스트에 있는 숫자들의 총 합을 구하고 일반 절차형 프로그래밍과 함수형 프로그래밍을 비교해보는 것이다.

        //원래 하던대로 하면 이렇다.
        int sum1 = normalSum(nums);
        System.out.println(sum1);

        //이번에는 함수형 프로그래밍으로 만들어보자.
        /*int sum = 0;
        nums.stream().forEach(element -> sum += element);*/ //이런 방식으로는 안됨!

        int sum2 = FPSum(nums);
        System.out.println(sum2);

        //연습: 홀수들의 총합을 구해보자. reduce 전에 filter를 호출하면 됨.
        int sum3 = nums.stream().filter(element -> element % 2 == 1).reduce(0, (num1, num2) -> num1 + num2);
        System.out.println(sum3);
    }

    private static int normalSum(List<Integer> nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num; //sum의 값이 반복문을 수행할 때마다 매번 바뀐다. Mutation
        }
        return sum;
    }

    private static int FPSum(List<Integer> nums) {
        //괄호가 2개일 때는 꼭 괄호를 붙여줘야 한다.
        return nums.stream().reduce(0, (num1, num2) -> num1 + num2);//0은 초기값, 두 숫자를 더하는 연산을 계속 반복하는 방식이다. (((0+num2)+num2)+num2)... 이 과정을 반복하는 것
        //자바스크립트에서도 똑같은 reduce연산이 있었음.
    }

    //normalSum에서는 반복문을 통해 지역변수에 값을 하나씩 더하고 있댜. '무엇을' 할지와, '어떻게' 해야하는지도 기술하고 있는 것이다.

    //함수형 프로그래밍에서는 지역변수와 루프문을 하나도 신경쓰지 않아도된다.
    //그저 요소를 가지고 '무엇을' 할지만 기술하면 된다.

    //함수형 프로그래밍의 차이점
    //1.변수 변이를 피한다. 변하는 변수를 넣지 않음.
    //2.'무엇을'할지만 기술하면 된다. 과정은 기술하지 않아도 됨.
}
