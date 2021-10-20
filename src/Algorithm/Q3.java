/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/10 12:02 上午
 */

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的?最长子串?的长度。
 * <p>
 * ?
 * <p>
 * 示例?1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是?"wke"，所以其长度为 3。
 * ?    请注意，你的答案必须是 子串 的长度，"pwke"?是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * ?
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * 由英文字母、数字、符号和空格组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q3 {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1){
            return 1;
        }
        int length = 0;
        int left = 0, right = 0;
        // 当最rigt 遍历到最后一个字符 遍历结束
        while (right <= s.length() - 1) {
            Character temp = s.charAt(right);
            // 判断当前 right 所在字符，是否在 left 到right之间，
            // 需要 charInTheHistory 返回的长度为 left + offset 之后，直接left 定位为重复字符的下一位
            int offset = charInTheHistory(s, left, right, temp);
            if (offset > 0) {
                // 如果在，说明需要移动left 记录此时的长度，如果 大于 length 替换
                int tempLenght = right - left;
                if (tempLenght > length) {
                    length = tempLenght;
                }
                left += offset;
            } else {
                // 如果不在 继续右移 right
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
     * 判断 字符 charcater 是否在 字符串的 left 和 right 区间范围呢
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
