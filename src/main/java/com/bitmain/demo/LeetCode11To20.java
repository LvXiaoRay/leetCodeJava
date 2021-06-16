package com.bitmain.demo;

import com.bitmain.demo.LeetCode1To10.Solution2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeetCode11To20 {

    class Solution11 {

        public int maxArea(int[] height) {
            if (height.length <= 1) {
                return 0;
            }
            int left = 0;
            int right = height.length - 1;
            int max = 0;
            while (left < right) {
                max = Math.max(max, Math.min(height[right], height[left]) * (right - left));
                if (height[left] < height[right]) {
                    left++;
                } else {
                    right--;
                }
            }
            return max;
        }
    }

    static class Solution12 {

        public String intToRoman(int num) {
            if (num < 1 || num > 3999) {
                return "";
            }

            int thousand = num / 1000 * 1000;
            int hundred = (num - thousand) / 100 * 100;
            int ten = (num - thousand - hundred) / 10 * 10;
            int one = num - thousand - hundred - ten;
            StringBuffer result = new StringBuffer();
            result.append(getRoman(thousand));
            result.append(getRoman(hundred));
            result.append(getRoman(ten));
            result.append(getRoman(one));
            return result.toString();

        }

        public String getRoman(int a) {
            switch (a) {
                case 1:
                    return "I";
                case 2:
                    return "II";
                case 3:
                    return "III";
                case 4:
                    return "IV";
                case 5:
                    return "V";
                case 6:
                    return "VI";
                case 7:
                    return "VII";
                case 8:
                    return "VIII";
                case 9:
                    return "IX";
                case 10:
                    return "X";
                case 20:
                    return "XX";
                case 30:
                    return "XXX";
                case 40:
                    return "XL";
                case 50:
                    return "L";
                case 60:
                    return "LX";
                case 70:
                    return "LXX";
                case 80:
                    return "LXXX";
                case 90:
                    return "XC";
                case 100:
                    return "C";
                case 200:
                    return "CC";
                case 300:
                    return "CCC";
                case 400:
                    return "CD";
                case 500:
                    return "D";
                case 600:
                    return "DC";
                case 700:
                    return "DCC";
                case 800:
                    return "DCCC";
                case 900:
                    return "CM";
                case 1000:
                    return "M";
                case 2000:
                    return "MM";
                case 3000:
                    return "MMM";
                default:
                    return "";
            }
        }


    }

    static class Solution13 {

        public int romanToInt(String s) {
            s = s.replace("IV", "a").
                replace("IX", "b").
                replace("XL", "q").
                replace("XC", "p").
                replace("CD", "e").
                replace("CM", "f");
            int result = 0;
            for (int index = 0; index < s.length(); index++) {
                result += switchToInt(String.valueOf(s.charAt(index)));
            }

            return result;
        }

        public int switchToInt(String num) {
            switch (num) {
                case "I":
                    return 1;
                case "V":
                    return 5;
                case "X":
                    return 10;
                case "L":
                    return 50;
                case "C":
                    return 100;
                case "D":
                    return 500;
                case "M":
                    return 1000;
                case "a":
                    return 4;
                case "b":
                    return 9;
                case "q":
                    return 40;
                case "p":
                    return 90;
                case "e":
                    return 400;
                case "f":
                    return 900;
                default:
                    return 0;

            }
        }

    }

    static class Solution14 {

        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }
            int i = 0;
            for (; i < strs[0].length(); i++) {
                for (int j = 0; j < strs.length; j++) {
                    if (strs[j].length() <= i) {
                        return strs[0].substring(0, i);
                    }
                    if (strs[j].charAt(i) != strs[0].charAt(i)) {
                        return strs[0].substring(0, i);
                    }
                }
            }

            return strs[0].substring(0, i);

        }
    }

    static class Solution15 {

        public static List<List<Integer>> threeSum(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();
            if (nums.length <= 2) {
                return result;
            }
            Arrays.sort(nums);

            Map<Integer, Integer> map = new ConcurrentHashMap<>();
            for (int index = 0; index < nums.length; index++) {
                if (map.containsKey(nums[index])) {
                    map.replace(nums[index], map.get(nums[index]) + 1);
                } else {
                    map.put(nums[index], 1);
                }
            }
            for (int a = 0; a < nums.length - 2; a++) {
                if (nums[a] >= 0) {
                    if (map.containsKey(0) && map.get(0) >= 3) {
                        result.add(getList(0, 0, 0));
                    }
                    return result;
                }
                for (int aNext = a + 1; aNext < nums.length - 1; aNext++) {
                    int target = 0 - (nums[aNext] + nums[a]);
                    if (target < nums[aNext]) {
                        continue;
                    }
                    if (target != nums[aNext] && target != nums[a]) {
                        if (map.containsKey(target)) {
                            result.add(getList(nums[a], nums[aNext], target));
                        }
                    } else if (target == nums[aNext]) {
                        if (map.get(nums[aNext]) > 1) {
                            result.add(getList(nums[a], nums[aNext], target));
                        }
                    } else {
                        if (map.get(nums[a]) > 1) {
                            result.add(getList(nums[a], nums[aNext], target));
                        }
                    }
                    while (aNext < nums.length - 1 && nums[aNext] == nums[aNext + 1]) {
                        aNext++;
                    }
                }
                while (a < nums.length - 2 && nums[a] == nums[a + 1]) {
                    a++;
                }
            }
            return result;
        }

        public static List<Integer> getList(Integer a, Integer b, Integer c) {
            List<Integer> temp = new ArrayList<>();
            temp.add(a);
            temp.add(b);
            temp.add(c);
            return temp;
        }
    }

    static class Solution16 {

        public int threeSumClosest(int[] nums, int target) {
            if (nums.length < 3) {
                return 0;
            }
            if (nums.length == 3) {
                return nums[0] + nums[1] + nums[2];
            }
            Arrays.sort(nums);
            int sum = 0;
            int tempSum = nums[0] + nums[1] + nums[nums.length - 1];
            for (int index = 0; index < nums.length - 2; index++) {
                int left = index + 1, right = nums.length - 1;
                for (; left < nums.length - 1 && left < right; ) {
                    sum = nums[index] + nums[left] + nums[right];
                    tempSum = Math.abs(tempSum - target) < Math.abs(sum - target) ? tempSum : sum;
                    if (sum > target) {
                        right--;
                    } else if (target > sum) {
                        left++;
                    } else {
                        return sum;
                    }
                }
                tempSum = Math.abs(tempSum - target) < Math.abs(sum - target) ? tempSum : sum;
            }
            return tempSum;
        }
    }

    class Solution17 {

        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            digits.replace("1", "");
            if (digits.length() == 0) {
                return result;
            }
            if (digits.length() == 1) {
                return Arrays.asList(getStringArrayByNum(digits));
            }

            String[] chars = digits.split("");
            String[] temp = getStringArrayByNum(chars[0]);
            for (int index = 1; index < chars.length; index++) {
                String[] aggg = getStringArrayByNum(chars[index]);
                temp = multiplyString(temp, aggg);

            }
            return Arrays.asList(temp);
        }

        public String[] getStringArrayByNum(String num) {
            switch (num) {
                case "2":
                    return new String[]{"a", "b", "c"};
                case "3":
                    return new String[]{"d", "e", "f"};
                case "4":
                    return new String[]{"g", "h", "i"};
                case "5":
                    return new String[]{"j", "k", "l"};
                case "6":
                    return new String[]{"m", "m", "o"};
                case "7":
                    return new String[]{"p", "q", "r", "s"};
                case "8":
                    return new String[]{"t", "u", "v"};
                case "9":
                    return new String[]{"w", "x", "y", "z"};
                default:
                    return null;
            }
        }

        public String[] multiplyString(String[] a, String[] b) {
            String[] result = new String[a.length * b.length];
            for (int aIndex = 0; aIndex < a.length; aIndex++) {
                for (int bIndex = 0; bIndex < b.length; bIndex++) {
                    result[aIndex * b.length + bIndex] = new StringBuffer().append(a[aIndex]).append(b[bIndex]).toString();
                }
            }
            return result;
        }
    }

    static class Solution18 {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            if (nums.length < 4) {
                return res;
            }
            for (int index = 0; index < nums.length - 3; index++) {
                if (nums[index] > 0 & target < 0) {
                    break;
                }
                int newTarget = target - nums[index];
                for (int newIndex = index + 1; newIndex < nums.length - 2; newIndex++) {
                    if (newTarget * nums[newIndex] < 0 && nums[newIndex] > 0) {
                        break;
                    }
                    for (int left = newIndex + 1, right = nums.length - 1; left < right; ) {
                        int tempValue = nums[newIndex] + nums[right] + nums[left];
                        if (tempValue > newTarget) {
                            right--;
                        } else if (tempValue < newTarget) {
                            left++;
                        } else {
                            List<Integer> tempRes = new ArrayList<>();
                            tempRes.add(nums[index]);
                            tempRes.add(nums[newIndex]);
                            tempRes.add(nums[left]);
                            tempRes.add(nums[right]);
                            res.add(tempRes);
                            while (nums[left] == nums[left + 1] & left < right & left < nums.length - 2) {
                                left++;
                            }
                            left++;
                            right--;

                        }
                    }
                    while (newIndex < nums.length - 1 && nums[newIndex] == nums[newIndex + 1]) {
                        newIndex++;
                    }
                }

                while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
                    index++;
                }
            }
            return res;
        }

    }


    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    static class Solution19 {

        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode pre = new ListNode(0);
            pre.next = head;
            ListNode start = pre;
            ListNode end = pre;
            if (head.next == null) {
                return null;
            }
            while (n > 0) {
                start = start.next;
                n--;
            }
            while (start.next != null) {
                start = start.next;
                end = end.next;
            }
            end.next = end.next.next;
            return pre.next;
        }
    }

  static   class Solution20 {

        public boolean isValid(String s) {
            if (s.length() % 2 != 0) {
                return false;
            }
            if (!(s.startsWith("(") || s.startsWith("{") || s.startsWith("["))) {
                return false;
            }
            char[] sArray = s.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();

            for (int index = 0; index < sArray.length; index++) {
                if (isFirst(sArray[index])) {
                    stringBuffer.append(sArray[index]);
                } else {
                    if (stringBuffer.length() == 0) {
                        return false;
                    } else {
                        if (getPare(stringBuffer.charAt(stringBuffer.length() -1)) == sArray[index]) {
                            stringBuffer.deleteCharAt(stringBuffer.length() -1);
                        }else {
                            return false;
                        }
                    }
                }
            }
            if (stringBuffer.length() == 0) {
                return true;
            }
            return false;
        }

        public char getPare(char s) {
            switch (s) {
                case '(':
                    return ')';
                case '[':
                    return ']';
                case '{':
                    return '}';
            }
            return 's';
        }

        public boolean isFirst(char s) {
            switch (s) {
                case '(':
                    return true;
                case '[':
                    return true;
                case '{':
                    return true;
                default:
                    return false;
            }
        }
    }

    public static void main(String[] args) {
        Solution19 solution16 = new Solution19();
        int[] agr = new int[]{0, 4, -5, 2, -2, 4, 2, -1, 4};
        int[] at = new int[]{-3, -2, -1, 0, 0, 1, 2, 3};
        int[] tt = new int[]{1, -5, 3, -3, 4, 5, 2};
        ListNode head = new ListNode(2);
        ListNode head2 = new ListNode(5);
        head.next = head2;
        String test = "([}}])";
        Solution20 solution20 = new Solution20();
        head = solution16.removeNthFromEnd(head, 1);
        solution20.isValid(test);

    }

}
