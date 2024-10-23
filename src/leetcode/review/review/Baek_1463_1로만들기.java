import java.util.Arrays;

public class Baek_1463_1로만들기 {
    public static void main(String[] args) {
        Baek_1463_1로만들기 baek = new Baek_1463_1로만들기();
        System.out.println(baek.process(10));
        System.out.println(baek.process(2));
    }

    //1에서 N을 만드는 방식으로 접근
    private int process(int amount){
        int[] processArr = new int[amount + 1];
        //processArr[1] = 1;//처음에 이 부분이 있어서 제대로 작동이 안되고 있었다. 1에서 N을 만드는 최적의 과정을 구하는건데 1에서 1을 만드는데 필요한 연산의 횟수는 0

        for (int i = 2; i < processArr.length; i++) {
            int divide3 = Integer.MAX_VALUE;//처음에 0으로 해놓아서 제대로 작동이 안되고 있었음.
            int divide2 = Integer.MAX_VALUE;
            int minus1 = processArr[i-1]+1;

            if (i % 3 == 0) {
                divide3 = processArr[i/3]+1;
            }
            if (i % 2 == 0) {
                divide2 = processArr[i/2]+1;
            }

            processArr[i] = Math.min(divide3, Math.min(divide2, minus1));
        }

        System.out.println(Arrays.toString(processArr));

        return processArr[processArr.length-1];
    }
}
