/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/10 12:02 ����
 */

/**
 * ����һ���ַ��� s �������ҳ����в������ظ��ַ���?��Ӵ�?�ĳ��ȡ�
 * <p>
 * ?
 * <p>
 * ʾ��?1:
 * <p>
 * ����: s = "abcabcbb"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "abc"�������䳤��Ϊ 3��
 * ʾ�� 2:
 * <p>
 * ����: s = "bbbbb"
 * ���: 1
 * ����: ��Ϊ���ظ��ַ�����Ӵ��� "b"�������䳤��Ϊ 1��
 * ʾ�� 3:
 * <p>
 * ����: s = "pwwkew"
 * ���: 3
 * ����: ��Ϊ���ظ��ַ�����Ӵ���?"wke"�������䳤��Ϊ 3��
 * ?    ��ע�⣬��Ĵ𰸱����� �Ӵ� �ĳ��ȣ�"pwke"?��һ�������У������Ӵ���
 * ʾ�� 4:
 * <p>
 * ����: s = ""
 * ���: 0
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 0 <= s.length <= 5 * 104
 * ��Ӣ����ĸ�����֡����źͿո����
 * <p>
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Q3 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1){
            return 1;
        }
        int length = 0;
        int left = 0, right = 0;
        // ����rigt ���������һ���ַ� ��������
        while (right <= s.length() - 1) {
            Character temp = s.charAt(right);
            // �жϵ�ǰ right �����ַ����Ƿ��� left ��right֮�䣬
            // ��Ҫ charInTheHistory ���صĳ���Ϊ left + offset ֮��ֱ��left ��λΪ�ظ��ַ�����һλ
            int offset = charInTheHistory(s, left, right, temp);
            if (offset > 0) {
                // ����ڣ�˵����Ҫ�ƶ�left ��¼��ʱ�ĳ��ȣ���� ���� length �滻
                int tempLenght = right - left;
                if (tempLenght > length) {
                    length = tempLenght;
                }
                left += offset;
            } else {
                // ������� �������� right
                right++;
            }
        }
        int tempLenght = right - left;
        if (tempLenght > length) {
            length = tempLenght;
        }
        return length;
    }

    /**
     * �ж� �ַ� charcater �Ƿ��� �ַ����� left �� right ���䷶Χ��
     *
     * @param str
     * @param left
     * @param right
     * @param character
     * @return
     */
    public int charInTheHistory(String str, Integer left, Integer right, Character character) {
        if (left < 0 || right < 0 || left > right || left >= str.length() || right >= str.length()) {
            return -1;
        }
        for (int i = left; i < right; i++) {
            Character c = str.charAt(i);
            if (c.equals(character)) {
                return i - left + 1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Q3 demo = new Q3();
        String str = "au";
        System.out.println(demo.lengthOfLongestSubstring(str));
    }
}
