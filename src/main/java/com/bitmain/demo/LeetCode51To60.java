package com.bitmain.demo;

public class LeetCode51To60 {

	class Solution53 {

		public int maxSubArray(int[] nums) {
			if (nums.length == 1) {
				return nums[0];
			}
			int[] result = new int[nums.length];
			int resultNum = nums[0];
			for (int index = 0; index < nums.length; index++) {
                if (index == 0){
                	result[index] = nums[0];
                }else {
                	result[index]= result[index -1]> 0 ? nums[index] + result[index - 1] : nums[index];
                }
                resultNum = resultNum > result[index] ? resultNum : result[index];
			}
			return resultNum;
		}
	}
}
