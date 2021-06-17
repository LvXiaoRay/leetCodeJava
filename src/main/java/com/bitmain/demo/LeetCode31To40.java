package com.bitmain.demo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import com.sun.xml.internal.ws.api.client.WSPortInfo;
import java.util.ArrayList;
import java.util.List;

public class LeetCode31To40 {

	static class Solution31 {

		public void nextPermutation(int[] nums) {
			if (nums.length <= 1) {
				return;
			}

			for (int index = nums.length - 1; index > 0; index--) {
				if (nums[index] <= nums[index - 1]) {
					continue;
				}
				int count = index - 1;
				int tempPoint = index;
				for (int aindex = index; aindex < nums.length; aindex++) {
					if (nums[aindex] > nums[count]) {
						tempPoint = aindex;
					}
				}
				exchange(nums, count, tempPoint);
				revise(nums, count + 1, nums.length - 1);
				return;
			}
			revise(nums, 0, nums.length - 1);
			return;
		}

		public void exchange(int[] nums, int indexA, int indexB) {
			int temp = nums[indexA];
			nums[indexA] = nums[indexB];
			nums[indexB] = temp;
		}

		public void revise(int[] nums, int startIndex, int endIndex) {
			while (startIndex < endIndex) {
				exchange(nums, startIndex++, endIndex--);
			}
		}

	}

	static class Solution32 {

		public int longestValidParentheses(String s) {
			if (s.length() <= 1) {
				return 0;
			}
			int leftCount = 0;
			int rightCount = 0;
			int result1 = 0;
			List<Character> stringList = new ArrayList<>();
			for (int index = 0; index < s.length(); index++) {
				if (s.charAt(index) == '(') {
					stringList.add(s.charAt(index));
					leftCount++;
				} else {
					stringList.add(s.charAt(index));
					rightCount++;
				}
				if (rightCount > leftCount) {
					result1 = result1 > stringList.size() - 1 ? result1 : stringList.size() - 1;
					stringList.removeAll(stringList);
					rightCount = 0;
					leftCount = 0;
				}

			}
			rightCount = 0;
			leftCount = 0;
			for (int index = stringList.size() - 1; index >= 0; index--) {
				if (stringList.get(index) == '(') {
					leftCount++;
				} else {
					rightCount++;
				}
				if (leftCount > rightCount) {
					result1 = result1 > rightCount * 2 ? result1 : rightCount * 2;
					rightCount = 0;
					leftCount = 0;
				}
			}
			return result1 > rightCount * 2 ? result1 : rightCount * 2;
		}


	}

	static class Solution33 {

		public int search(int[] nums, int target) {
			if (nums.length == 1) {
				return nums[0] == target ? 0 : -1;
			}
			return findTarget2(nums, target, 0, nums.length - 1);
		}

		public int findTarget(int nums[], int target, int start, int end) {
			if (start + 1 == end) {
				return nums[start] == target ? start : (nums[end] == target ? end : -1);
			}
			int middle = (start + end) / 2;
			if (nums[start] == target) {
				return start;
			}
			if (nums[end] == target) {
				return end;
			}
			if (nums[middle] == target) {
				return middle;
			}
			if (nums[start] < nums[middle]) {//翻转点在middle之后
				if (nums[start] <= target && target <= nums[middle]) {
					return findTarget(nums, target, start, middle);
				} else {
					return findTarget(nums, target, middle, end);
				}
			} else {//翻转点在middle之前
				if (nums[middle] <= target && nums[end] >= target) {
					return findTarget(nums, target, middle, end);
				} else {
					return findTarget(nums, target, start, middle);
				}
			}
		}

		public int findTarget2(int nums[], int target, int start, int end) {
			while (start < end) {
				if (start + 1 == end) {
					return nums[start] == target ? start : (nums[end] == target ? end : -1);
				}
				int middle = (start + end) / 2;
				if (nums[start] == target) {
					return start;
				}
				if (nums[end] == target) {
					return end;
				}
				if (nums[middle] == target) {
					return middle;
				}
				if (nums[start] < nums[middle]) {//翻转点在middle之后
					if (nums[start] <= target && target <= nums[middle]) {
						end = middle;
					} else {
						start = middle;
					}
				} else {//翻转点在middle之前
					if (nums[middle] <= target && nums[end] >= target) {
						start = middle;
					} else {
						end = middle;
					}
				}
			}
			return -1;
		}

	}

	static class Solution34 {

		public int[] searchRange(int[] nums, int target) {
			int[] result = new int[]{-1, -1};
			if (nums.length < 1) {
				return result;
			}
			result[0] = findLeftOne(nums, target, true);
			result[1] = findRightOne(nums, target, false);
			return result;
		}

		public int findLeftOne(int[] nums, int target, boolean isLeft) {
			int left = 0;
			int right = nums.length - 1;
			if (left == right) {
				return nums[left] == target ? left : -1;
			}
			while (left < right) {
				int middle = (left + right) / 2;
				if (left == middle && nums[left] != target) {
					return nums[right] == target ? right : -1;
				}
				if (nums[middle] == target) {
					if (middle == left || nums[middle - 1] < target) {
						return middle;
					} else {
						right = middle;
					}
				} else if (nums[middle] > target) {
					right = middle;
				} else {
					left = middle;
				}
			}
			return -1;
		}

		public int findRightOne(int[] nums, int target, boolean isLeft) {
			int left = 0;
			int right = nums.length - 1;
			if (left == right) {
				return nums[right] == target ? right : -1;
			}
			while (left < right) {
				int middle = (left + right + 1) / 2;
				if (right == middle && nums[right] != target) {
					return nums[left] == target ? left : -1;
				}
				if (nums[middle] == target) {
					if (middle == right || nums[middle + 1] > target) {
						return middle;
					} else {
						left = middle;
					}
				} else if (nums[middle] > target) {
					right = middle;
				} else {
					left = middle;
				}
			}
			return -1;
		}

	}

	static class Solution35 {

		public int searchInsert(int[] nums, int target) {
			if (nums.length < 1) {
				return 0;
			}
			if (nums.length == 1) {
				return nums[0] > target ? 0 : (nums[0] == target ? 0 : 1);
			}
			int left = 0;
			int right = nums.length - 1;
			while (left <= right) {
				int middle = (left + right) / 2;
				if (middle == left) {
					if (target <= nums[left]) {
						return left;
					} else if (target > nums[right]) {
						return right + 1;
					} else {
						return right;
					}
				}
				if (nums[middle] > target) {
					right = middle;
				} else if (nums[middle] == target) {
					return middle;
				} else {
					left = middle;
				}
			}
			return 0;
		}
	}

	static class Solution36 {

		public boolean isValidSudoku(char[][] board) {
			boolean[][] rowIndex = new boolean[9][9];
			boolean[][] columnIndex = new boolean[9][9];
			boolean[][] boxIndex = new boolean[9][9];

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] != '.') {
						int num = board[i][j] - '1';
						int boxNum = i / 3 * 3 + j / 3;
						if (rowIndex[i][num] || columnIndex[j][num] || boxIndex[boxNum][num]) {
							return false;
						}
						boxIndex[boxNum][num] = true;
						rowIndex[i][num] = true;
						columnIndex[j][num] = true;
					}
				}
			}
			return true;
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1};
		Solution35 solution35 = new Solution35();
		int nums2 = solution35.searchInsert(nums, 1);
		System.out.println(nums2);
		System.out.println(nums2);
	}

}
