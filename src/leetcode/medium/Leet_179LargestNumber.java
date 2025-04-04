package leetcode.medium;

/*
양수들이 담긴 배열을 넘겨주면 그 숫자들을 잘 조합을 해서 가장 큰 수를 만들어내야 하는 문제다.
만들어진 숫자가 매우 클 수 있기 때문에 문자열로 반환을 하라고 한다.
조교님이 DP방식으로 풀 수 있을거라고 하긴 함. 근데 문제 토픽을 보면 그리디 알고리즘이라고 나와있다. 그리디 알고리즘이 뭐지...?
일단 좀 고민을 해보고, 안되면 그리디 알고리즘을 공부해서 풀어보자.

숫자들이 다 한 자릿수라면 그냥 제일 배열에서 제일 큰 수를 갖다 붙이는 식이면 아주 간단하겠지만, 그렇지 않기 때문에 상당히 복잡하다.

nums = [432,43243] <- 이 케이스에서 개같이 틀림. 지금의 알고리즘으로 이 상황에 대처 불가함.... 그리고 내가 하던 방식은 DP도 아니다.... 그리디 알고리즘에 가까움.
*/

import java.util.*;
import java.util.stream.Collectors;

public class Leet_179LargestNumber {
    public static void main(String[] args) {
        largestNumber(new int[] {10,2});
        largestNumber(new int[] {3,30,34,5,9});
    }

    public static String largestNumber(int[] nums) {
        //우선 nums의 모든 숫자들의 자릿수의 총 합을 구해야 한다.
        //int resultLength = 0;
        //int count = 0; //근데 그냥 배열로 안하고 리스트로 해도 될 것 같기도 하고...? 일단 리스트로 진행

        //우선 nums를 정렬하자. 정렬하지 않으면, 큰 숫자가 덮어버린 다음에 작은 숫자들을 가지고 비교를 하게 되는데 이미 큰 숫자가 덮어버린 상태이기 때문에 제대로 비교를 하는게 불가능함.
        //그리고 일단 편의를 위해서 배열을 리스트로 변환, 연결리스트로 하는게 더 좋을지도?
        ArrayList<Integer> numsList = new ArrayList<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        numsList.sort(Collections.reverseOrder());

        ArrayList<Integer> processList = new ArrayList<>();//가장 큰 수를 만드는 경우를 메모하는 리스트
        HashMap<Integer, Integer> map = new HashMap<>();//리스트의 각 인덱스에 어떤 숫자가 들어가 있는지를 저장해두는 map. 키는 숫자가 들어가기 시작한 인덱스, 값은 그 숫자다.
        int lastNumIdx = -1;

        int currentLength = 1;//현재까지 만들어진 숫자의 길이
        while(!numsList.isEmpty()) {//numsList에 숫자가 남아있는 동안 계속 반복
            processList.add(0);//일단 한 자릿수 더 늘려줌.

            System.out.println("현재 numsList: " + numsList);
            System.out.println("현재 processList: " + processList);

            boolean isChanged = false;
            int max = 0;//현재까지 만들어진 가장 큰 숫자.
            int maxNum = 0;//max가 만들어지게 이번에 추가된 숫자.
            int maxNumIdx = 0;
            for (int i = 0; i < currentLength; i++) {
                max += processList.get(i) * (int)Math.pow(10, currentLength - i -1);
            }
            System.out.println("현재까지 만들어진 가장 큰 숫자 max: " + max);

            int numsListSize = numsList.size();
            for (int i = 0; i < numsListSize; i++) {//numsList를 순회
                int currentNum = numsList.get(i);//numsList를 순회하면서 고른 숫자
                System.out.println("currentNum: " + currentNum);

                int numDigit = getDigit(currentNum);//숫자의 자릿수를 구함.
                System.out.println("currentNum의 자릿수: "+numDigit);

                if (currentLength - numDigit < 0) {//숫자의 자릿수가 너무 길면 이번에는 패스함.
                    continue;
                }
                //이 부분을 고쳐야 함.
                /*if (!map.containsKey(currentLength - numDigit -1) && currentLength - numDigit != 0) {//원래 있던 숫자를 잘라먹는 경우에는 패스함.
                    continue;
                }*/

                //지금의 문제, 원래있던 숫자를 잘라먹지 않는 것에 너무 치중한 나머지 검사를 해야할 때도 하지 않고 continue를 때려버림. 숫자를 잘라먹지만 않는다면 currentLength - numDigit <= lastNumIdx이더라도 그대로 검사를 진행해야 한다.
                //이 문제를 해결하려면 map에 저장해놓은 숫자의 맨 끝 인덱스 정보를 활용을 해야한다. map.contains(currentLength - numDigit - 1)연산결과가 true면 숫자를 잘라먹지 않은 것이다.
                //근데 더 큰 current를 만들어내지 못해서 0만 붙여서 자릿수만 늘려놓은 상태에서는 어떻게 하지? 또 무한 continue가 걸릴텐데?
                //map에 키(인덱스) : 값(-1)로 저장을 해두자. 그리고 나중에 덮어씌워지면서 회수할 때 값이 -1인 애들은 걸러내게 하자. <- 이렇게 하니까 이제 진짜 고쳐졌다ㅋㅋㅋㅋㅋㅋ
                /*if (currentLength - numDigit <= lastNumIdx) {
                    continue;
                }*/
                if (currentLength - numDigit <= lastNumIdx) {
                    if (!map.containsKey(currentLength - numDigit - 1)) {//map.contains(currentLength - numDigit - 1)가 false이면 숫자를 잘라먹은 것. continue해야 한다.
                        continue;
                    }
                }

                int current = 0;//currentNum을 이용해서 만든 숫자.
                for (int j = 0; j < currentLength-numDigit; j++) {
                    current += processList.get(j) * (int)Math.pow(10, currentLength - j - 1);
                }
                current += currentNum;
                System.out.println("currentNum으로 만든 current: " + current);

                if (current > max) {
                    isChanged = true;
                    max = current;
                    maxNum = currentNum;
                    maxNumIdx = i;
                }
            }
            System.out.println("반복 검사 후 나온 결과 maxNum: " + maxNum);

            //이 부분도 고쳐야 함. 좀 더 정교하게 할 필요가 있음.
            if (!isChanged) {//isChanged가 false인 경우는 더 좋은 방법을 찾지 못했고, 일단 자릿수를 늘려야 하는 상황이다.
                map.put(currentLength-1, -1);
                currentLength++;
                continue;
            }

            int maxNumDigit = getDigit(maxNum);
            String tempMaxNum = String.valueOf(maxNum);
            for (int i = currentLength - maxNumDigit; i < currentLength; i++) {//숫자를 삽입하기 시작하는 위치부터 반복
                if (map.containsKey(i)) {
                    if (map.get(i) != -1) {
                        numsList.add(map.get(i));
                    }
                    map.remove(i);
                }
                processList.set(i, Character.getNumericValue(tempMaxNum.charAt(i - (currentLength - maxNumDigit))));
            }
            numsList.remove(maxNumIdx);
            map.put(currentLength - 1, maxNum);//각 숫자가 끝나는 인덱스를 map에 저장을 해놓았음.
            lastNumIdx = currentLength - 1;

            numsList.sort(Collections.reverseOrder());

            System.out.println(processList);
            System.out.println(map);
            System.out.println("-------------");

            currentLength++;//만들어진 숫자의 길이는 1 늘림.
        }

        System.out.println(processList);

        StringBuilder result = new StringBuilder();
        for (Integer integer : processList) {
            result.append(integer);
        }
        return result.toString();
    }

    //숫자의 자릿수를 구하는 메서드
    private static int getDigit(int num){
        //System.out.println("자릿수 구하기");
        int digit = 0;
        do {
            num = num/10;
            digit++;
        } while (num != 0);

        return digit;
    }
}
