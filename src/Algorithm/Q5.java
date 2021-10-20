/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/10 6:11 ����
 */

/**
 * ����һ���ַ��� s���ҵ� s ����Ļ����Ӵ���
 * <p>
 * ?
 * <p>
 * ʾ�� 1��
 * <p>
 * ���룺s = "babad"
 * �����"bab"
 * ���ͣ�"aba" ͬ���Ƿ�������Ĵ𰸡�
 * ʾ�� 2��
 * <p>
 * ���룺s = "cbbd"
 * �����"bb"
 * ʾ�� 3��
 * <p>
 * ���룺s = "a"
 * �����"a"
 * ʾ�� 4��
 * <p>
 * ���룺s = "ac"
 * �����"a"
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 1 <= s.length <= 1000
 * s �������ֺ�Ӣ����ĸ����д��/��Сд�����
 */
public class Q5 {
    public static void main(String[] args) {
        Q5 demo = new Q5();
        String input = "babad";
        System.out.println(demo.longestPalindrome_DP(input));
        System.out.println(demo.longestPalindrome(input));
    }

    /**
     * ��̬�滮��˼·  ���� ��
     * - ������
     * ���� (i ,j) ֮����ַ��������Char��i�� == Char��j�� &&(i+1 ,j-1) Ҳ�� �����ַ��� �� (i ,j)  �ǻ����ַ���
     * i �ķ�Χ ��0 - n-1
     * j �ķ�Χ ��i - n-1
     * - ��̬�滮��״̬ת�Ʒ���
     * P(i,j)=P(i+1,j?1)��(Si==Sj)
     * <p>
     * ����ַ����ĳ��� Ϊ 1��0 ���϶��ǻ����ַ���
     *
     * @param s
     * @return
     */
    public String longestPalindrome_DP(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // ��¼ dp ������� dp[i][j] == true ���� s[i...j]  �ǻ����ַ���
        boolean[][] dp = new boolean[len][len];

        // �Խ��ߣ� ������Ϊ1���ִ� ��Ϊ�����ַ���
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();

        for (int strLength = 2; strLength <= len; strLength++) {
            for (int i = 0; i < len; i++) {
                int j = i + strLength - 1;
                // �ҽ� ���
                if (j >= len) {
                    break;
                }
                if ( charArray[i] != charArray[j]){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    }else {
                        if (dp[i + 1][j - 1] == true ) {
                            dp[i][j] = true;
                        }
                    }
                }

                // ���¼�¼
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }

        }


        return s.substring(begin, begin + maxLen);
    }

    public String longestPalindrome(String s) {

        return "";

    }


}
