/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/10 6:11 下午
 */

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * ?
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * ?
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class Q5 {
    public static void main(String[] args) {
        Q5 demo = new Q5();
        String input = "babad";
        System.out.println(demo.longestPalindrome_DP(input));
        System.out.println(demo.longestPalindrome(input));
    }

    /**
     * 动态规划的思路  整理 ：
     * - 子问题
     * 对于 (i ,j) 之间的字符串，如果Char（i） == Char（j） &&(i+1 ,j-1) 也是 回文字符串 则 (i ,j)  是回文字符串
     * i 的范围 ：0 - n-1
     * j 的范围 ：i - n-1
     * - 动态规划的状态转移方程
     * P(i,j)=P(i+1,j?1)∧(Si==Sj)
     * <p>
     * 如果字符串的长度 为 1或0 ，认定是回文字符串
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

        // 记录 dp 字问题解 dp[i][j] == true 代表 s[i...j]  是回文字符串
        boolean[][] dp = new boolean[len][len];

        // 对角线， 即长度为1的字串 均为回文字符串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();

        for (int strLength = 2; strLength <= len; strLength++) {
            for (int i = 0; i < len; i++) {
                int j = i + strLength - 1;
                // 右界 溢出
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

                // 更新记录
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
