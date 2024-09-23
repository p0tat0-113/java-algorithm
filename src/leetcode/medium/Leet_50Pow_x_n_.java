package leetcode.medium;

public class Leet_50Pow_x_n_ {
    public static void main(String[] args) {
        System.out.println(myPow(2, -2147483648));

        /*System.out.println(Math.pow(2, -2147483648));
        System.out.println(-2147483648);*/
    }

    public static double myPow(double x, int n) {
        return binaryExp(x, (long) n);
    }

    private static double binaryExp(double x, long n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {//n이 음수면 처음에 -1을 곱해서 다시 호출한다.
            return 1.0 / binaryExp(x, -n);
        }

        if (n % 2 == 1) {
            return x * binaryExp(x * x, (n - 1) / 2);
        } else {
            return binaryExp(x * x, n / 2);
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
