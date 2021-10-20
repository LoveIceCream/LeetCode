/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/12 11:37 ����
 */

/**
 * ����һ������ nums?��һ��ֵ val������Ҫ ԭ�� �Ƴ�������ֵ����?val?��Ԫ�أ��������Ƴ���������³��ȡ�
 * <p>
 * ��Ҫʹ�ö��������ռ䣬������ʹ�� O(1) ����ռ䲢 ԭ�� �޸��������顣
 * <p>
 * Ԫ�ص�˳����Ըı䡣�㲻��Ҫ���������г����³��Ⱥ����Ԫ�ء�
 * <p>
 * ?
 * <p>
 * ˵��:
 * <p>
 * Ϊʲô������ֵ��������������Ĵ���������?
 * <p>
 * ��ע�⣬�����������ԡ����á���ʽ���ݵģ�����ζ���ں������޸�����������ڵ������ǿɼ��ġ�
 * <p>
 * ����������ڲ���������:
 * <p>
 * // nums ���ԡ����á���ʽ���ݵġ�Ҳ����˵������ʵ�����κο���
 * int len = removeElement(nums, val);
 * <p>
 * // �ں������޸�����������ڵ������ǿɼ��ġ�
 * // ������ĺ������صĳ���, �����ӡ�������� �ó��ȷ�Χ�� ������Ԫ�ء�
 * for (int i = 0; i < len; i++) {
 * ? ? print(nums[i]);
 * }
 * ?
 * <p>
 * ʾ�� 1��
 * <p>
 * ���룺nums = [3,2,2,3], val = 3
 * �����2, nums = [2,2]
 * ���ͣ�����Ӧ�÷����µĳ��� 2, ���� nums �е�ǰ����Ԫ�ؾ�Ϊ 2���㲻��Ҫ���������г����³��Ⱥ����Ԫ�ء����磬�������ص��³���Ϊ 2 ���� nums = [2,2,3,3] �� nums = [2,2,0,0]��Ҳ�ᱻ������ȷ�𰸡�
 * ʾ�� 2��
 * <p>
 * ���룺nums = [0,1,2,2,3,0,4,2], val = 2
 * �����5, nums = [0,1,4,0,3]
 * ���ͣ�����Ӧ�÷����µĳ��� 5, ���� nums �е�ǰ���Ԫ��Ϊ 0, 1, 3, 0, 4��ע�������Ԫ�ؿ�Ϊ����˳���㲻��Ҫ���������г����³��Ⱥ����Ԫ�ء�
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 * <p>
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/remove-element
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 */
public class Q27 {
    public Q27() {

    }

    public static void main(String[] args) {

        Q27 demo = new Q27();
        int[] nums = {3, 2, 2, 3};
        int result = demo.removeElement(nums, 3);
        System.out.println(result);
        for (int i = 0; i < result; i++) {
            System.out.println(nums[i] + "\t");
        }
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                // �����Ԫ����Ҫǰ�� count λ
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        return nums.length - count;
    }

    public int removeElementDoublePointer(int[] nums, int val) {
        int left = 0;
        int right = 0;

        for (; right < nums.length; right++) {
            if (nums[right] == val) {
                right++;
            } else {
                left++;
                right++;
            }
        }

        return left;
    }

}
