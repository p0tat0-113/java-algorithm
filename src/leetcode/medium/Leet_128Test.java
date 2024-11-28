package leetcode.medium;

import java.util.HashSet;
import java.util.Iterator;

public class Leet_128Test {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);

        /*for (Integer integer : hashSet) {
            hashSet.remove(1);
            hashSet.remove(2);
            hashSet.remove(3);
            hashSet.remove(4);
            hashSet.remove(5);
        }*/

        Iterator<Integer> iterator = hashSet.iterator();
        while(iterator.hasNext()) {
            Integer num = iterator.next();
            if (num == 2 || num == 3) {
                iterator.remove();
            }
        }
        System.out.println(hashSet);

        System.out.println("Done!!!");
    }
}
