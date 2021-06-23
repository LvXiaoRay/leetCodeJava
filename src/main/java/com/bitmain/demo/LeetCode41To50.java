package com.bitmain.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.util.StringUtils;

public class LeetCode41To50 {

	static class Solution41 {

		public int firstMissingPositive(int[] nums) {
			for (int index = 0; index < nums.length; index++) {
				if (nums[index] <= 0) {
					nums[index] = nums.length + 1;
				}
			}
			for (int index = 0; index < nums.length; index++) {
				int num = Math.abs(nums[index]);
				if (num <= nums.length) {
					nums[num - 1] = -Math.abs(nums[num - 1]);
				}

			}
			for (int index = 0; index < nums.length; index++) {
				if (nums[index] > 0) {
					return index + 1;
				}

			}
			return nums.length + 1;
		}
	}

	static class Solution42 {

		public int trap(int[] height) {
			if (height.length <= 1) {
				return 0;
			}
			int vol = 0;
			for (int index = 0; index < height.length; index++) {
				if (height[index] == 0) {
					continue;
				}
				boolean MaxOne = true;
				int tempSum = 0;
				int secondOne = index + 1;
				for (int rightIndex = index + 1; rightIndex < height.length; rightIndex++) {
					secondOne = height[secondOne] <= height[rightIndex] ? rightIndex : secondOne;
					if (rightIndex - index > 1) {
						tempSum += height[rightIndex - 1];
					}
					if (height[rightIndex] >= height[index]) {
						vol += height[index] * (rightIndex - index - 1);
						vol = vol - tempSum;
						MaxOne = false;
						index = rightIndex - 1;
						break;
					}
				}
				if (MaxOne & secondOne < height.length) {
					tempSum = 0;
					for (int rightIndex = index + 1; rightIndex < secondOne; rightIndex++) {
						tempSum += height[rightIndex];
					}
					vol += height[secondOne] * (secondOne - index - 1);
					vol = vol - tempSum;
					index = secondOne - 1;
				}
			}
			return vol;
		}
	}

	static class Solution43 {

		public String multiply(String num1, String num2) {
			if ("0".equals(num1) || "0".equals(num2)) {
				return "0";
			}
			String result = "0";
			char[] numsList1 = num1.toCharArray();
			for (int index1 = numsList1.length - 1; index1 >= 0; index1--) {
				int tempR = 0;
				StringBuffer tt = new StringBuffer();
				for (int i = 0; i < num2.length(); i++) {
					int resultT = (num1.charAt(index1) - '0') * (num2.charAt(num2.length() - i - 1) - '0') + tempR;
					int left = resultT % 10;
					tempR = resultT / 10;
					tt.insert(0, left);
				}
				if (tempR > 0) {
					tt.insert(0, tempR);
				}
				for (int i = 0; i < numsList1.length - 1 - index1; i++) {
					tt.append("0");
				}
				result = getAddResult(result, tt.toString());
			}
			return result;
		}

		public String getAddResult(String a, String b) {
			if (a.equals("0")) {
				return b;
			}
			int maxLength = a.length() > b.length() ? a.length() : b.length();
			StringBuffer buffer = new StringBuffer();
			int temp = 0;

			for (int i = 0; i < maxLength; i++) {
				if (a.length() >= i + 1) {
					temp += (a.charAt(a.length() - i - 1) - '0');
				}
				if (b.length() >= i + 1) {
					temp += (b.charAt(b.length() - i - 1) - '0');
				}
				buffer.insert(0, temp % 10);
				temp = temp / 10;
			}
			if (temp != 0) {
				buffer.insert(0, temp);

			}
			return buffer.toString();
		}
	}

	class Solution44 {

		public boolean isMatch(String s, String p) {
			return false;
		}
	}

	public static void main(String[] args) {
		Solution42 solution41 = new Solution42();
		int[] nums = new int[]{4, 2, 0, 3, 2, 5};
		//System.out.println(solution41.trap(nums));
		Solution43 solution43 = new Solution43();
		System.out.println(solution43.multiply("123", "456"));
	}
}
