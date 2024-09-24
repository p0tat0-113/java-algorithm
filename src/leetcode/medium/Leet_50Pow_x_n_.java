package leetcode.medium;

/*
x의 n승을 구하는 문제다. 그런데 n의 범위가 매우 크다. int형의 범위다. -21억부터 +21까지 라는 것이다.
처음에는 아주 단순하게 접근했다. 재귀문제이길래 그냥 재귀호출로 x를 계속 곱하는 방식을 사용했었다.
이러니까 n이 매우 큰 케이스에서 스택오버플로우가 터져버림. 스택이 넘치지 않았더라도 수행시간이 매우 느렸을 것이다.
그래서 일단 이렇게 하는게 맞는가를 검증하기 위해서 for루프를 돌려서 풀어보려고 계속 시도를 했는데 시간초과 문제를 해결하지 못함.
이 문제는 무식한 O(n)알고리즘으로는 풀 수 없는 문제인 것이다.

그래서 그냥 solutions를 봤는데, 아주 훌륭한 코드를 발견했다.
이 코드는 예를들어 2^4문제를 4^2, 16^1의 문제로 만들어버린다.
또다른 예로 2^5문제를 2 * 2^4, 2 * 4^2, 2* 16^1문제로 만들어버린다.
이 방식을 사용하면 시간복잡도가 O(logn)인 매우 효율적인 알고리즘이 나온다.
*/

public class Leet_50Pow_x_n_ {
    public static void main(String[] args) {
        //System.out.println(myPow(2, -2147483648));
        System.out.println(myPow(2, 4));

        /*System.out.println(Math.pow(2, -2147483648));
        System.out.println(-2147483648);*/
    }

    public static double myPow(double x, int n) {
        return binaryExp(x, n);
    }

    //2^4 문제를 재귀적으로 4^2로 만들고, 이걸 다시 16^1로 만들어서 해결한다. O(logn)의 시간복잡도를 가짐. 진짜 똑똑한 사람들 많네.
    private static double binaryExp(double x, long n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 1 / binaryExp(x, -n);
        }

        if (n % 2 == 0) {//n이 짝수면, 이 코드의 메인 재귀호출을 계속 반복함.
            return binaryExp(x * x, n / 2);
        } else {
            return  x * binaryExp(x * x, n/2);
        }
    }

    /*public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (x == 1) {
            return 1;
        }

        if (x == -1) {
            return (n % 2 == 0) ?  1 : -1;
        }

        double result = process(x, Math.abs(n));
        return (n > 0) ? result : 1 / result;
    }

    //이 방식대로 하니 stackOverflow가 발생한다. 문제 조건을 다시 보니까 n의 범위가 int형의 범위와 같다.
    //반복문으로 풀어보니까 시간 초과가 뜸. 재귀로 한다고 속도가 빠른 것도 아닌데 이걸 어떻게 풀어야 할까?
    private static double process(double x, int n) {
        //얘는 문제가 있음.
        *//*if (n > 1) {
            return x * process(x, n-1);
        }
        return x;*//*

        *//*double num = x;
        for (int i = 0; i < n-1; i++) {
            num = num * x;
        }
        return num;*//*
    }*/
}
