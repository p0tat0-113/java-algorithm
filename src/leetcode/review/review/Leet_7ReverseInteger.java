public class Leet_7ReverseInteger {
    public static void main(String[] args) {
        Leet_7ReverseInteger leet = new Leet_7ReverseInteger();
        System.out.println(leet.reverse(123));
        System.out.println(leet.reverse(-123));
        System.out.println(leet.reverse(Integer.MIN_VALUE));
    }

    public int reverse(int x) {
        long result = 0;

        while (x != 0) {
            int remainder = x % 10;
            result = result*10 + remainder;
            x = x/10;
        }

        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) result;
    }
}
