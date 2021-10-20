public class Q9 {
//    public boolean isPalindrome(int x) {
//        if (x < 0) {
//            return false;
//        }
//        if (x >= 0 && x <= 9) {
//            return true;
//        }
//        // 难点在于如何从两头去值
//        String str = new String(x + "");
//        int len = str.length();
//        int left = 0;
//        int right = len - 1;
//
//        while (left < right) {
//            char left_value = str.charAt(left);
//            char right_value = str.charAt(right);
//            if (left_value != right_value){
//                return false;
//            }
//
//            left++;
//            right--;
//        }
//
//        return true;
//    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x >= 0 && x <= 9) {
            return true;
        }

        int temp_x = x;
        int result = 0;
        while (true) {
            int temp = temp_x % 10;
            temp_x = temp_x / 10;
            result = result * 10 + temp;
            if (temp_x == 0) {
                break;
            }
        }
        if (result == x) {
            return true;
        } else {
            return false;
        }
    }


}
