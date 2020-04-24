package com.company.easy;

/**
 * @author : wangzhen
 * create at:  2020/4/22  9:50 下午
 * @description: 496.下一个更大元素 1
 */
public class QNumber496 {

    // 主函数 测试调用nextGreaterElement ？
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] results =  nextGreaterElement(nums1,nums2);
        System.out.println(results);
    }

    /**
     * 下一个更大元素主函数
     * @param nums1 子集
     * @param nums2 父集
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] results = new int[nums1.length];  //根据 nums1 的长度初始化 结果数组
        // 循环 数组1 逐项检查
        int flag = 0;   //来记录数组1 的 下标
        int tag = 0;    //用来记录是否已经找到 数组1 中的值 0未找到 1已找到
        for (int number:nums1){
            //在数组2中查找 并返回 在数组2 中对应的坐标
            for (int i = 0; i < nums2.length; i++) {
                if (tag == 0 && nums2[i] == number){
                    //逐项检查数组2 是否存在右边更大项
                    tag = 1;
                }
                if (tag == 1 && nums2[i] > number){
                    // 检查是否比 数组1 中的值 大
                    results[flag] = nums2[i];
                }
            }
            if (tag == 0 || results[flag] == 0){
                results[flag] = -1;
            }
            flag++;
            tag = 0; //重置 tag
        }
        return  results;
    }

}
