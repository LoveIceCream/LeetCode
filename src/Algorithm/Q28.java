/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/13 12:13 上午
 */

/**
 * 实现?strStr()?函数。
 * <p>
 * 给你两个字符串?haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回? -1 。
 * <p>
 * ?
 * <p>
 * 说明：
 * <p>
 * 当?needle?是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当?needle?是空字符串时我们应当返回 0 。这与 C 语言的?strstr()?以及 Java 的?indexOf()?定义相符。
 * <p>
 * ?
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：haystack = "", needle = ""
 * 输出：0
 * ?
 * <p>
 * 提示：
 * <p>
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q28 {
    public Q28() {
    }

    public static void main(String[] args) {
        Q28 demo = new Q28();
        String haystack = "1";
        String needle = "1";
        System.out.println(demo.strStr(haystack, needle));
    }

    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        int left = 0;
        while (left <= haystack.length() - needle.length()) {
            if (haystack.charAt(left) == needle.charAt(0)) {
                boolean flag = true;
                for (int i = 1;i<needle.length();i++){
                    if (haystack.charAt(left+i) != needle.charAt(i)){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return left;
                }
            } else {
                left++;
            }
        }
        return -1;
    }
}
