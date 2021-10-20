/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/10 7:03 обнГ
 */

import java.util.ArrayList;

/**
 *
 */
public class Q6 {


    public String convert(String s, int numRows) {
        if (s.length() == 1 || numRows == 1) {
            return s;
        }
        ArrayList<StringBuffer> zArraylist = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            zArraylist.add(new StringBuffer());
        }
        boolean goingDown = true;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            zArraylist.get(index).append(s.charAt(i));


            if (index == 0 & !goingDown) {
                goingDown = true;
            }
            if (index == numRows - 1 && goingDown) {
                goingDown = false;
            }
            index += goingDown ? 1 : -1;
        }
        StringBuffer stringBuffer = new StringBuffer(s.length());
        for (int i = 0; i < numRows; i++) {
            stringBuffer.append(zArraylist.get(i));
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Q6 demo = new Q6();
        String s = "AB";
        int numRows = 1;
        System.out.println(demo.convert(s, numRows));
    }
}
