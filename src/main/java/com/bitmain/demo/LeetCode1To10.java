package com.bitmain.demo;

import com.bitmain.demo.LeetCode1To10.Solution1;
import com.bitmain.demo.LeetCode1To10.Solution8.Solution9;
import com.sun.xml.internal.fastinfoset.util.CharArray;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import java.util.Map;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sun.plugin2.gluegen.runtime.StructAccessor;


public class LeetCode1To10 {

    static class Solution1 {

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> result = new HashMap<>();
            for (int index = 0; index < nums.length; index++) {
                if (result.containsKey(target - nums[index])) {
                    return new int[]{result.get(target - nums[index]), index};
                } else {
                    result.put(nums[index], index);
                }
            }
            return null;
        }
    }

    class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    ;

    class Solution2 {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode result = new ListNode();
            StringBuffer l1Value = new StringBuffer();
            StringBuffer l2Value = new StringBuffer();
            for (; l1 != null; l1 = l1.next) {
                l1Value.append(l1.val);
                if (l1.next == null) {
                    break;
                }
            }
            for (; l2 != null; l2 = l2.next) {
                l2Value.append(l2.val);
                if (l2.next == null) {
                    break;
                }
            }
            char[] chars1 = l1Value.toString().toCharArray();
            char[] chars2 = l2Value.toString().toCharArray();
            StringBuffer resultSbb = new StringBuffer();
            int temp1 = 0;
            int i;
            for (i = 0; i < chars1.length && i < chars2.length; i++) {
                int tempResult = Integer.parseInt(String.valueOf(chars1[i])) + Integer.parseInt(String.valueOf(chars2[i])) + temp1;
                if (tempResult >= 10) {
                    resultSbb.append(tempResult % 10);
                    temp1 = 1;
                } else {
                    resultSbb.append(tempResult);
                    temp1 = 0;
                }
            }
            chars1 = chars1.length >= chars2.length ? chars1 : chars2;
            for (; i < chars1.length; i++) {
                int tempResult = Integer.parseInt(String.valueOf(chars1[i])) + temp1;
                if (tempResult >= 10) {
                    resultSbb.append(tempResult % 10);
                    temp1 = 1;
                } else {
                    resultSbb.append(tempResult);
                    temp1 = 0;
                }
            }
            if (temp1 != 0) {
                resultSbb.append(temp1);
            }
            chars1 = resultSbb.toString().toCharArray();
            ListNode temp = result;
            for (int index = 0; index < chars1.length; index++) {
                temp.val = Integer.parseInt(String.valueOf(chars1[index]));
                if (index != chars1.length - 1) {
                    temp.next = new ListNode();
                }
                temp = temp.next;
            }
            return result;
        }
    }

    ;


    static class Solution3 {

        public int lengthOfLongestSubstring3(String s) {
            // 记录字符上一次出现的位置
            int[] last = new int[128];
            for (int i = 0; i < 128; i++) {
                last[i] = -1;
            }
            int n = s.length();

            int res = 0;
            int start = 0; // 窗口开始位置
            for (int i = 0; i < n; i++) {
                int index = s.charAt(i);
                start = Math.max(start, last[index] + 1);
                res = Math.max(res, i - start + 1);
                last[index] = i;
            }

            return res;
        }

        public int lengthOfLongestSubstring(String s) {
            if ("".equals(s) || s == null) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }
            int maxLength = 0;
            String[] ss = s.split("");
            StringBuffer valueMap = new StringBuffer();
            for (int index = 0; index < ss.length - 1; index++) {
                valueMap.append(ss[index]);
                for (int indexY = index + 1; indexY < ss.length; indexY++) {
                    if (valueMap.toString().contains((ss[indexY]))) {
                        maxLength = maxLength > valueMap.length() ? maxLength : valueMap.length();
                        if (maxLength >= (ss.length - index - 1)) {
                            return maxLength;
                        }
                        valueMap.delete(0, valueMap.length());
                        break;
                    } else {
                        valueMap.append(ss[indexY]);
                        if (indexY == ss.length - 1) {
                            return valueMap.length();
                        }
                    }
                }
            }

            return maxLength > valueMap.length() ? maxLength : valueMap.length();
        }

        public int lengthOfLongestSubstring2(String s) {
            if (s.length() <= 1) {
                return s.length();
            }
            int maxLength = 0;
            int left = 0;
            Map<Character, Integer> valueMap = new HashMap<>();
            for (int index = 0; index < s.length(); index++) {
                if (valueMap.containsKey(s.charAt(index))) {
                    maxLength = Math.max(maxLength, index - left);
                    left = Math.max(left, valueMap.get(s.charAt(index)) + 1);
                }
                valueMap.put(s.charAt(index), index);

            }
            return Math.max(maxLength, s.length() - left);
        }

    }

    static class Solution4 {

        public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int k1 = (nums1.length + nums2.length + 1) / 2;
            int k2 = (nums1.length + nums2.length + 2) / 2;
            if (nums1.length == 0 || nums2.length == 0) {
                nums1 = nums1.length == 0 ? nums2 : nums1;
                if (nums1.length == 0) {
                    return (double) 0;
                }
                return (double) (nums1[k1 - 1] + nums1[k2 - 1]) / 2;
            }
            return (findKth(nums1, 0, nums2, 0, k1) + findKth(nums1, 0, nums2, 0, k2)) / 2.0;
        }

        public static int findKth(int[] nums1, int i, int[] nums2, int j, int k) {//nums1 和nums2中找第k个数
            if (i >= nums1.length) {
                return nums2[j + k - 1];//
            }
            if (j >= nums2.length) {
                return nums1[i + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[i], nums2[j]);
            }
            int midValue1 = (i + k / 2 - 1) >= nums1.length ? Integer.MAX_VALUE : nums1[i + k / 2 - 1];
            int midValue2 = (j + k / 2 - 1) >= nums2.length ? Integer.MAX_VALUE : nums2[j + k / 2 - 1];

            if (midValue1 < midValue2) {
                return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
            } else {
                return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
            }
        }

    }

    static class Solution5 {

        public static String longestPalindrome(String s) {
            if (s.length() <= 1) {
                return s;
            }
            StringBuffer sb = new StringBuffer();
            sb.append("^");
            for (int i = 0; i < s.length(); i++) {
                sb.append("#");
                sb.append(s.charAt(i));
            }
            sb.append("#$");
            s = sb.toString();
            int maxLenght = 0;
            String maxSubString = new String();
            for (int i = 2; i < s.length(); i++) {
                if (maxLenght * 2 - 1 > 2 * (s.length() - i)) {
                    return maxSubString.replaceAll("#", "");
                }
                int length = 1;
                for (int j = 1; i - j >= 0 && i + j < s.length(); j++) {
                    if (s.charAt(i - j) == s.charAt(i + j)) {
                        length += 2;
                    } else {
                        if (maxLenght < length / 2) {
                            maxSubString = s.substring(i - j + 1, i + j - 1);
                            maxLenght = length / 2;
                        }
                        break;
                    }
                }
            }
            return maxSubString.replaceAll("#", "");
        }
    }

    static class Solution6 {

        public static String convert(String s, int numRows) {
            if (numRows <= 1) {
                return s;
            }
            StringBuffer[] result = new StringBuffer[numRows];
            for (int i = 0; i < numRows; i++) {
                result[i] = new StringBuffer();
            }
            StringBuffer resultR = new StringBuffer();

            boolean forward = true;
            int to = 0;
            for (int i = 0; i < s.length(); i++) {
                result[to].append(s.charAt(i));
                if (to == numRows - 1 || to == 0 && i != 0) {
                    forward = forward == true ? false : true;
                }
                if (forward) {
                    to++;
                } else {
                    to--;
                }

            }

            for (StringBuffer stringBuffer : result) {
                resultR.append(stringBuffer);
            }
            return resultR.toString();
        }
    }

    static class Solution7 {

        public static int reverse(int x) {
            if (x == 0) {
                return x;
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (x / 10 * 10 == x) {
                x = x / 10;
            }
            if (x < 0) {
                x = -x;
                stringBuffer.append(x);
                stringBuffer.reverse();
                stringBuffer.insert(0, '-');
            } else {
                stringBuffer.append(x);
                stringBuffer.reverse();
            }
            try {
                return Integer.parseInt(stringBuffer.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    static class Solution8 {

        public static int myAtoi(String s) {
            while (s.startsWith(" ")) {
                s = s.substring(1, s.length());
            }
            if (s.length() == 0) {
                return 0;
            }
            StringBuffer result = new StringBuffer();
            StringBuffer stringBuffer = new StringBuffer(s);
            for (int i = 0; i < stringBuffer.length(); i++) {
                if (hasChar(stringBuffer.charAt(i))) {
                    if (i == 0) {
                        result.append(stringBuffer.charAt(i));
                    } else if (i >= 1 && stringBuffer.charAt(i) != '-' && stringBuffer.charAt(i) != '+') {
                        result.append(stringBuffer.charAt(i));
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            try {
                if (result.length() == 0) {
                    return 0;
                }
                return Integer.parseInt(result.toString());
            } catch (NumberFormatException e) {
                if (result.length() <= 1) {
                    return 0;
                }
                if (result.charAt(0) == '-') {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }

        public static boolean hasChar(char a) {
            switch (a) {
                case '0':
                    return true;
                case '1':
                    return true;
                case '2':
                    return true;
                case '3':
                    return true;
                case '4':
                    return true;
                case '5':
                    return true;
                case '6':
                    return true;
                case '7':
                    return true;
                case '8':
                    return true;
                case '9':
                    return true;
                case '-':
                    return true;
                case '+':
                    return true;
                default:
                    return false;

            }
        }

        static class Solution9 {

            public static boolean isPalindrome(int x) {
                if (x < 0 || (x % 10 == 0 && x != 0)) {
                    return false;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(x);
                stringBuffer.reverse();
                try {
                    if (x == Integer.parseInt(stringBuffer.toString())) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }

        class Solution {

            public boolean isMatch(String s, String p) {

                return true;
            }
        }
    }

    public static void main(String[] args) {
        Thread testThread = new Thread(() -> {
            System.out.println("testThread当前线程组名字：" +
                Thread.currentThread().getThreadGroup().getName());
            System.out.println("testThread线程名字：" +
                Thread.currentThread().getName());
        });
        testThread.start();
        try {
            Thread.sleep(20);
            System.out.println("end");
        } catch (Exception e) {

        }
        System.out.println("执行main方法线程名字：" + Thread.currentThread().getName());
        String[] plants = new String[]{"mercury", "venus", "earth", "mars", "jupiter", "saturn", "uranus", "neptune"};
        System.out.println(Arrays.toString(plants));
        Arrays.sort(plants, Comparator.comparingInt(String::length));
        System.out.println(Arrays.toString(plants));
    }
}
