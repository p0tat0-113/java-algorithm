package easyAlgorithm.dynamicProgramming;

/*책에 나오는 행렬 곱셈 순서 문제를 먼저 재귀로 구현해본다.
점화식은 아래와 같다.

c(i,j) = {
if i=j -> 0
if i<j -> min{c(i,k) + c(k+1,j) + p(i-1)*p(k)*(j)} <- i <= k <= j-1
}
*/

public class 행렬곱셈순서문제_Recursion {
    public static void main(String[] args) {

        int[] p = {10, 30, 5, 60, 10};//A1 ~ A4, 각 행렬의 크기를 기록해놓은 배열이다.
        System.out.println(recursion(1, 4, p));
    }

    //점화식대로 구현하니까 잘 작동된다. 그런데 현재 중복된 재귀호출이 많이 일어나 비효율적인 상태다.
    //opitmal substructure이면서, 중복된 재귀호출이 많이 일어나서 비효율적인 상태이므로 DP를 적용하기에 좋은 케이스다.
    private static int recursion(int i, int j, int[] p){
        if (i == j) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {//<- i <= k <= j-1 분할된 수 있는 모든 경우의 수를 다 체크한다.
            int temp = recursion(i, k, p) + recursion(k+1, j, p) + p[i-1]*p[k]*p[j];//<- p[i-1]*p[k]*p[j] 이 부분은 (Ai*...Ak)와 (Ak+1*...Aj)를 곱하는 비용이다. 
            if (min > temp) {
                min = temp;
            }
        }
        return min;
    }
}
