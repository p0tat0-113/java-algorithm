package leetcode.medium;

import java.util.Arrays;

/*
이 문제를 문자열 방식으로도 접근할 수 있겠지만, 나는 수학적으로 접근함.
*/

public class Leet_7ReverseInteger {
    public static void main(String[] args) {
        /*int num = -15;
        int index = 0;
        System.out.println((int) Math.floor(Math.log10(Math.abs(num))) + 1);//num의 자릿수를 구함.
        System.out.println((int) (Math.pow(10, Math.floor(Math.log10(Math.abs(num))))) * (num/Math.abs(num)));//10^(해당 숫자의 자릿수-1), 숫자의 부호에 맞춰서 반환함.
        System.out.println((int) Math.floor(Math.abs(num) / Math.pow(10, index)) % 10);//해당 인덱스의 숫자를 가져옴.*/

        Leet_7ReverseInteger leet = new Leet_7ReverseInteger();
        System.out.println(leet.reverse(-2147483648));
    }

    //오버플로우가 발생했을 때는 0을 반환해야 한다고 함. 그 부분을 고려하지 않아서 틀림.
    //-2147483648이 인수로 들어왔을 때 digitLength가 1로 계산되어버림. Math.abs(-2147483648)연산을 할때 오버플로우가 발생해서 그런 듯. 인수로 들어온 숫자도 long으로 바꿔버리면 될 듯.
    //답은 맞았는데, 수행시간이 1ms로 살짝 아쉬움. 좀 더 최적화를 해보자.
    public int reverse(int x) {
        //우선 계산은 long으로 한다. int형으로 하면 Math.abs()연산만으로도 오버플로우가 발생하는 문제가 있음.
        long num = x;
        long result = 0;

        int digitLength = (int) Math.floor(Math.log10(Math.abs(num))) + 1;//숫자 x의 자릿수를 구함.

        for (int digitIdx = 0; digitIdx < digitLength; digitIdx++) {//해당 자릿수만큼 반복문을 돌림, 3자릿수이면 digitIdx에 0~2의 숫자를 할당함.
            result += (((long) Math.floor(Math.abs(num) / Math.pow(10, digitIdx)) % 10) * ((long) (Math.pow(10,digitLength-digitIdx-1)) * (num/Math.abs(num))));
            //result += 인덱스에 해당하는 숫자 * 10^(x의 자릿수 - digitIdx - 1) * x의 부호
        }

        //result가 int형의 범위를 벗어났으면 0을 반환한다.
        if (result < -2147483648 || result > 2147483647) {
            return 0;
        }

        return (int) result;
    }
}
