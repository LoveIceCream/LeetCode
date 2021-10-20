/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/7/4 10:20 上午
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
     * 645. 错误的集合
     * <p>
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
     * <p>
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * <p>
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     * 示例 2：
     * <p>
     * 输入：nums = [1,1]
     * 输出：[1,2]
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
                // 遇见重复的了
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
