import java.util.HashMap;

/**
 * 给定一个整数数组 nums?和一个整数目标值 target，请你在该数组中找出 和为目标值 target? 的那?两个?整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * ?
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * ?
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 一、
 * 最简单想到暴力双循环，
 * <p>
 * 二、
 * 想到将数组转存到map中，然后遍历，如果当前值和target 的差值在map中，说明找到了结果，但是存在问题，即case3的时候，不行了
 * <p>
 * 三、
 * 对二的优化，将遍历过信息转存到map中，这样就解决了 case3的问题
 */
public class Q1 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> Key2IndexMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (Key2IndexMap.containsKey(target - nums[i])) {
                // 如果 target 和 当前值额差值 在 map中，找到结果，赋值并返回
                result[0] = Key2IndexMap.get(target - nums[i]);
                result[1] = i;
                break;
            } else {
                // 不在 将当前值加入到map中
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
