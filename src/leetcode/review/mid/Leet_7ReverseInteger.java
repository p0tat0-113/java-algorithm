package leetcode.review.mid;

public class Leet_7ReverseInteger {
    public static void main(String[] args) {
        Leet_7ReverseInteger leet = new Leet_7ReverseInteger();
        System.out.println(leet.reverse(123));
        System.out.println(leet.reverse(-123));
        System.out.println(leet.reverse(-120));
    }

    public int reverse(int x) {
        long result = 0;

        while(x != 0) {
            int remainder = x % 10;//가장 낮은 자리 수
            x = x/10;
            result = result*10 + remainder;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {//int 형의 범위를 넘어가면
            return 0;
        }

        return (int) result;
    }
}
