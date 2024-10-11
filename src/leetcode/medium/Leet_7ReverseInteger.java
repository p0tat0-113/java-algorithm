package leetcode.medium;

import java.util.Arrays;

/*
이 문제를 문자열 방식으로도 접근할 수 있겠지만, 나는 수학적으로 접근함.

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21
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
        System.out.println(leet.reverse(-259723));
    }

    //다른 사람 코드 참고해서 만들었는데 이런 개쩌는 코드도 있네.
    public int reverse(int x) {
        //우선 계산은 long으로 한다. int형으로 하면 Math.abs()연산만으로도 오버플로우가 발생하는 문제가 있음.
        long xLong = x;
        long result = 0;

        while (xLong != 0) {
            long r = xLong % 10;//어떤 수를 10으로 나눈 나머지는 그 수의 1의 자리수다.
            result = result * 10 +r;//result에 result*10 과 r을 더한 값을 저장한다. 처음에는 왜 result*10을 하는지 이해가 안됐는데 잘 생각해보니까 원래 있던 수의 자릿수를 하나 증가시키는 작업이다!
            xLong = xLong/10;
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) result;
    }

    //좀 더 직관적이게 문자열로도 접근해보자. 시험때 숫자가지고 머리 싸매기 싫음. 근데 이렇게 하니까 느림.... 대부분이 1ms 나오는데 나는 10ms 나옴...
    /*public int reverse(int x) {
        //우선 계산은 long으로 한다. int형으로 하면 Math.abs()연산만으로도 오버플로우가 발생하는 문제가 있음.
        long xLong = x;
        String[] xStringArr = String.valueOf(Math.abs(xLong)).split("");//x의 절대값을 문자열로 만들었음.
        //System.out.println(Arrays.toString(xStringArr));//디버깅용

        //이제 문자열을 뒤집는다.
        for (int i = 0; i < xStringArr.length/2; i++) {
            String temp = xStringArr[i];
            xStringArr[i] = xStringArr[xStringArr.length-1-i];
            xStringArr[xStringArr.length-1-i] = temp;
        }

        long result = Long.parseLong(String.join("",xStringArr));
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }

        if (x < 0){
            result = result * -1;
        }

        return (int) result;
    }*/

    /*public int reverse(int x) {
        //우선 계산은 long으로 한다. int형으로 하면 Math.abs()연산만으로도 오버플로우가 발생하는 문제가 있음.
        long num = x;
        long result = 0;

        int digitLength = (int) Math.floor(Math.log10(Math.abs(num))) + 1;//숫자 x의 자릿수를 구함.

        if (num < 0) {
            for (int digitIdx = 0; digitIdx < digitLength; digitIdx++) {//해당 자릿수만큼 반복문을 돌림, 3자릿수이면 digitIdx에 0~2의 숫자를 할당함.
                result -= (((long) Math.floor(Math.abs(num) / Math.pow(10, digitIdx)) % 10) * ((long) (Math.pow(10,digitLength-digitIdx-1))));
                //result -= 인덱스에 해당하는 숫자 * 10^(x의 자릿수 - digitIdx - 1)
                //Math.floor()을 하는게 메모리를 더 적게 소모한다. 이유는 모르겠지만...
            }
        } else {
            for (int digitIdx = 0; digitIdx < digitLength; digitIdx++) {//해당 자릿수만큼 반복문을 돌림, 3자릿수이면 digitIdx에 0~2의 숫자를 할당함.
                result += (((long) Math.floor(Math.abs(num) / Math.pow(10, digitIdx)) % 10) * ((long) (Math.pow(10,digitLength-digitIdx-1))));
                //result += 인덱스에 해당하는 숫자 * 10^(x의 자릿수 - digitIdx - 1)
            }
        }

        //result가 int형의 범위를 벗어났으면 0을 반환한다.
        if (result < -2147483648 || result > 2147483647) {
            return 0;
        }

        return (int) result;
    }*/

    //오버플로우가 발생했을 때는 0을 반환해야 한다고 함. 그 부분을 고려하지 않아서 틀림.
    //-2147483648이 인수로 들어왔을 때 digitLength가 1로 계산되어버림. Math.abs(-2147483648)연산을 할때 오버플로우가 발생해서 그런 듯. 인수로 들어온 숫자도 long으로 바꿔버리면 될 듯.
    //답은 맞았는데, 수행시간이 1ms로 살짝 아쉬움. 좀 더 최적화를 해보자.
    /*public int reverse(int x) {
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
    }*/
}
