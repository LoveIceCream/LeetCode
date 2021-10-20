import java.util.HashMap;

/**
 * ����һ���������� nums?��һ������Ŀ��ֵ target�������ڸ��������ҳ� ��ΪĿ��ֵ target? ����?����?���������������ǵ������±ꡣ
 * <p>
 * ����Լ���ÿ������ֻ���Ӧһ���𰸡����ǣ�������ͬһ��Ԫ���ڴ��ﲻ���ظ����֡�
 * <p>
 * ����԰�����˳�򷵻ش𰸡�
 * <p>
 * ?
 * <p>
 * ʾ�� 1��
 * <p>
 * ���룺nums = [2,7,11,15], target = 9
 * �����[0,1]
 * ���ͣ���Ϊ nums[0] + nums[1] == 9 ������ [0, 1] ��
 * ʾ�� 2��
 * <p>
 * ���룺nums = [3,2,4], target = 6
 * �����[1,2]
 * ʾ�� 3��
 * <p>
 * ���룺nums = [3,3], target = 6
 * �����[0,1]
 * ?
 * <p>
 * ��ʾ��
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * ֻ�����һ����Ч��
 * <p>
 * ��Դ�����ۣ�LeetCode��
 * ���ӣ�https://leetcode-cn.com/problems/two-sum
 * ����Ȩ������������С���ҵת������ϵ�ٷ���Ȩ������ҵת����ע��������
 * <p>
 * һ��
 * ����뵽����˫ѭ����
 * <p>
 * ����
 * �뵽������ת�浽map�У�Ȼ������������ǰֵ��target �Ĳ�ֵ��map�У�˵���ҵ��˽�������Ǵ������⣬��case3��ʱ�򣬲�����
 * <p>
 * ����
 * �Զ����Ż�������������Ϣת�浽map�У������ͽ���� case3������
 */
public class Q1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> Key2IndexMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (Key2IndexMap.containsKey(target - nums[i])) {
                // ��� target �� ��ǰֵ���ֵ �� map�У��ҵ��������ֵ������
                result[0] = Key2IndexMap.get(target - nums[i]);
                result[1] = i;
                break;
            } else {
                // ���� ����ǰֵ���뵽map��
                Key2IndexMap.put(nums[i], i);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Q1 demo = new Q1();
        int[] input = {3,3};
        int target = 6;
        int[] result = demo.twoSum(input, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

}
