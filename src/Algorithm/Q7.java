public class Q7 {
    public static int reverse(int x) {
        long result = 0;
        long minValue = -(long) Math.pow(2, 31);
        long maxValue = (long) Math.pow(2, 31) - 1;
        // check
        if (x == minValue) {
            return 0;
        }
        int flag = 1;
        if (x < 0) {
            x = -x;
            flag = 0;
        }

        while (true) {
            int temp = x % 10;
            x = x / 10;
            result = result * 10 + temp;
            if (x == 0) {
                break;
            }
        }
        if (flag == 0) {
            result = 0 - result;
        }
        if ( (int)result != result){
            return 0;
        }
        return (int) result;
    }


    /**
     * int 是否越界检验算法
     *
     * @param number        需要校验的数字
     * @param valueOfCharAt 需要校验的最低位
     * @param flag          正负标志
     * @return
     */

    private int verification(int number, int valueOfCharAt, int flag) {
        long shangjie = (long) Math.pow(2, 31) - 1;     // 上限
        long xiajie = -(long) Math.pow(2, 31);          // 下限
        int result = 0;
        if (flag == -1) {                               // 负数校验
            if (((-number) < xiajie / 10) || (-number == (xiajie / 10) && valueOfCharAt > 8)) {
                result = 1;
            }
        } else {                                        // 正数校验
            if ((number > shangjie / 10) || ((number == shangjie / 10) && valueOfCharAt > 7)) {
                result = 1;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int x = 1534236469;
        System.out.println(x);
        System.out.println(-x);
    }
}
