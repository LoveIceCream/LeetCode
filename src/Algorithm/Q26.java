/**
 * @author wangzhen
 * @version 1.0
 * @description
 * @date 2021/8/12 11:59 обнГ
 */
public class Q26 {
    public Q26() {

    }

    public static void main(String[] args) {
        Q26 demo = new Q26();
        int[] nums = {1 };
        int result = demo.removeDuplicates(nums);
        System.out.println(result);
        System.out.println("...................");
        for (int i = 0; i < result; i++) {
            System.out.println(nums[i]);
        }
    }

    public int removeDuplicates(int[] nums) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                nums[++left] = nums[right];
            }
        }
        return left + 1;
    }

}
