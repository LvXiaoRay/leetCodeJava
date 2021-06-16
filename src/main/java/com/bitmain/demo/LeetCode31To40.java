package com.bitmain.demo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
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

//        class Solution {
//
//            public int search(int[] nums, int target) {
//                if (nums.length <= 0) {
//                    return -1;
//                }
//                if (nums.length == 1) {
//                    return nums[0] == target ? 0 : -1;
//                }
//
//            }
//
//            public int findKValue(int[] nums, int leftIndex, int rightIndex, int target) {
//
//                if (compare(nums[leftIndex], nums[(rightIndex + leftIndex) / 2]) >= 0) {
//                    nums[rightIndex]
//                }
//            }
//
//            public int compare(int a, int b) {
//                return a > b ? 1 : (a == b ? 0 : -1);
//            }
//
//            public int find
//        }

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 5, 4, 2};
        Solution31 solution31 = new Solution31();
        solution31.nextPermutation(nums);
        Solution32 solution32 = new Solution32();
        System.out.println(solution32.longestValidParentheses("))))())()()(()"));
    }

}
