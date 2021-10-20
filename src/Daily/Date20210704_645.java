/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/4 10:20 ����
 */
public class Date20210704_645 {
    public Date20210704_645() {
    }

    public static void main(String[] args) {
        Date20210704_645 demo = new Date20210704_645();
        int[] nums = {1, 1};
        int[] res = demo.findErrorNums(nums);
        System.out.println(res[0] + "\t" + res[1]);
    }

    /**
     * 645. ����ļ���
     * <p>
     * ���� s ������ 1 �� n �����������ҵ��ǣ���Ϊ���ݴ��󣬵��¼�������ĳһ�����ָ����˳��˼������������һ�����ֵ�ֵ�����¼��� ��ʧ��һ������ ���� ��һ�������ظ� ��
     * <p>
     * ����һ������ nums �����˼��� S ���������Ľ����
     * <p>
     * �����ҳ��ظ����ֵ����������ҵ���ʧ�����������������������ʽ���ء�
     * <p>
     * <p>
     * <p>
     * ʾ�� 1��
     * <p>
     * ���룺nums = [1,2,2,4]
     * �����[2,3]
     * ʾ�� 2��
     * <p>
     * ���룺nums = [1,1]
     * �����[1,2]
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        int[] copy = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            if (copy[nums[i]] == 0) {
                copy[nums[i]] = nums[i];
            } else {
                // �����ظ�����
                result[0] = nums[i];
            }
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (copy[i] == 0) {
                result[1] = i;
                break;
            }
        }

        return result;
    }
}
