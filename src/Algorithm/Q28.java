/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/13 12:13 ����
 */

/**
 * ʵ��?strStr()?������
 * <p>
 * ���������ַ���?haystack �� needle �������� haystack �ַ������ҳ� needle �ַ������ֵĵ�һ��λ�ã��±�� 0 ��ʼ������������ڣ��򷵻�? -1 ��
 * <p>
 * ?
 * <p>
 * ˵����
 * <p>
 * ��?needle?�ǿ��ַ���ʱ������Ӧ������ʲôֵ�أ�����һ���������кܺõ����⡣
 * <p>
 * ���ڱ�����ԣ���?needle?�ǿ��ַ���ʱ����Ӧ������ 0 ������ C ���Ե�?strstr()?�Լ� Java ��?indexOf()?���������
 * <p>
 * ?
 * <p>
 * ʾ�� 1��
 * <p>
 * ���룺haystack = "hello", needle = "ll"
 * �����2
 * ʾ�� 2��
 * <p>
 * ���룺haystack = "aaaaa", needle = "bba"
 * �����-1
 * ʾ�� 3��
 * <p>
 * ���룺haystack = "", needle = ""
 * �����0
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack �� needle ����СдӢ���ַ����
 * <p>
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/implement-strstr
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
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
