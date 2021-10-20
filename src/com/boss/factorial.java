/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/19 8:01 ÏÂÎç
 */
public class factorial {
    int[] history = new int[100];

    public factorial() {
    }

    public static void main(String[] args) {
        factorial demo = new factorial();
        int result = demo.getResult(5);
        System.out.println(result);
    }

    public boolean isPrimeNumbers(int number) {
        int i = 2;
        for (; i < number; i++) {
            if (number % i == 0) {
                // ²»ÊÇ
                return false;
            }
        }
        if (number == i) {
            return true;
        }
        return false;
    }

    public int getResult(int n) {
        if (n < 2) {
            return 0;
        }
        int result = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrimeNumbers(i)) {
                int temp = getFactorial(i);
                result += temp;
                history[i] = temp;
            }
        }

        return result;
    }

    public int getFactorial(int n) {
        if (n == 2 || n == 1) {
            return n;
        }
        if (history[n] != 0) {
            return history[n];
        }
        return n * getFactorial(--n);
    }

}
