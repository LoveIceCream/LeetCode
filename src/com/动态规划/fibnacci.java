/**
 *
 */

package ¶¯Ì¬¹æ»®;

import java.util.ArrayList;

public class fibnacci {
    public static void main(String[] args) {
        System.out.println(fibnacci_not_recurision(100));
    }


    public static int fibnacci_recurision(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibnacci_recurision(n - 1) + fibnacci_recurision(n - 2);
        }
    }

    public static int fibnacci_not_recurision(int n) {
        ArrayList<Integer> fibArr = new ArrayList<>();
        fibArr.add(0);
        fibArr.add(1);
        fibArr.add(1);
        if (n == 1 || n == 2) {
            return fibArr.get(n);
        }
        for (int i = 3; i <= n; i++) {
            fibArr.add(fibArr.get(i - 1) + fibArr.get(i - 2));
        }
        return fibArr.get(n);

    }
}
