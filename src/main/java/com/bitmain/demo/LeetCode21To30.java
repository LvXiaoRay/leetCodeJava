package com.bitmain.demo;

import com.bitmain.demo.LeetCode1To10.Solution2;
import com.sun.org.apache.xpath.internal.objects.XNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import sun.jvm.hotspot.memory.PlaceholderEntry;

public class LeetCode21To30 {

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

    class Solution21 {

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode temp = new ListNode(-1);
            ListNode temp2 = temp;
            while (l1 != null & l2 != null) {
                if (l1.val > l2.val) {
                    temp2.next = l1;
                    temp2 = temp2.next;
                    l1 = l1.next;
                } else {
                    temp2.next = l2;
                    temp2 = temp2.next;
                    l2 = l2.next;
                }
            }
            if (l1 != null) {
                temp2.next = l1;
            }
            if (l2 != null) {
                temp2.next = l2;
            }

            return temp.next;
        }
    }

    static class Solution22 {


        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            if (n == 1) {
                result.add("()");
                return result;
            }
            Map<String, Integer> tempMap = new ConcurrentHashMap<>();
            tempMap.put("(", n - 1);
            for (int index = 1; index < 2 * n; index++) {
                Map<String, Integer> tempMap1 = new ConcurrentHashMap<>();

                for (Entry<String, Integer> entry : tempMap.entrySet()) {
                    String tempString = entry.getKey();
                    Integer tempValue = entry.getValue();
                    if (entry.getValue() > 0) {
                        tempMap.remove(tempString);
                        tempMap1.put(tempString + "(", tempValue - 1);
                        if (tempString.length() < 2 * (n - tempValue)) {
                            tempMap1.put(tempString + ")", tempValue);
                        }

                    } else {
                        tempMap.remove(tempString);
                        tempMap1.put(tempString + ")", tempValue);
                    }
                }
                tempMap.putAll(tempMap1);
            }
            for (Entry<String, Integer> entry : tempMap.entrySet()) {
                result.add(entry.getKey());
            }
            return result;
        }
    }

    static class Solution23 {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null) {
                return null;
            }
            boolean isAllNull = true;
            for (ListNode listNode : lists) {
                if (listNode != null) {
                    isAllNull = false;
                    break;
                }
            }
            if (isAllNull) {
                return null;
            }
            ListNode preNode = new ListNode(0);
            ListNode head = preNode;
            int mIndex = 0;//
            int nullValue = 0;//空值数量
            while (nullValue < lists.length) {
                int minValue = Integer.MAX_VALUE;//最小值
                nullValue = 0;
                boolean flag = false;
                for (int index = 0; index < lists.length; index++) {
                    if (lists[index] == null) {//为空跳过
                        nullValue++;
                        continue;
                    }
                    if (minValue >= lists[index].val) {
                        minValue = lists[index].val;
                        mIndex = index;
                        flag = true;
                    }
                }
                if (nullValue == lists.length) {
                    break;
                }
                if (lists[mIndex] != null) {
                    lists[mIndex] = lists[mIndex].next;
                }
                if (flag) {
                    ListNode tempNode = new ListNode(minValue);
                    head.next = tempNode;
                    head = head.next;
                }
            }
            return preNode.next;
        }
    }

    static class Solution24 {

        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode headPoint = new ListNode(0);
            headPoint.next = head;
            ListNode first = headPoint;//标记头位置
            while (first != null) {
                ListNode preNode = first.next;
                ListNode nextNode = first.next.next;
                if (preNode == null || nextNode == null) {
                    return headPoint.next;
                }

                preNode.next = nextNode.next;
                nextNode.next = preNode;
                first.next = nextNode;
                first = first.next.next;
                if (first.next == null || first.next.next == null) {
                    return headPoint.next;
                }
            }
            return headPoint.next;
        }
    }

    static class Solution25 {

        public ListNode reverseKGroup(ListNode head, int k) {
            if (k <= 1) {
                return head;
            }
            List<ListNode> listNodeList = new ArrayList<>();
            ListNode newNode = new ListNode(0);
            newNode.next = head;
            ListNode firstNode = head;
            ListNode preNode = newNode;//前置点
            while (firstNode != null) {
                int n = 0;
                ListNode endNode = null;
                while (n < k & firstNode != null) {
                    listNodeList.add(firstNode);
                    firstNode = firstNode.next;
                    n++;
                }
                if (listNodeList.size() < k) {
                    return newNode.next;
                }
                if (firstNode != null) {
                    endNode = firstNode;
                }
                for (int index = k - 1; index > 0; index--) {
                    listNodeList.get(index).next = listNodeList.get(index - 1);
                }

                preNode.next = listNodeList.get(k - 1);
                preNode = listNodeList.get(0);

                listNodeList.get(0).next = endNode;
                if (endNode == null) {
                    return newNode.next;
                }
                listNodeList = new ArrayList<>();
            }

            return newNode.next;
        }
    }

    static class Solution26 {

        public int removeDuplicates(int[] nums) {
            int realIndex = 1;
            if (nums.length <= 1) {
                return realIndex;
            }
            for (int index = 1; index < nums.length; index++) {
                if (nums[index] == nums[index - 1]) {
                    continue;
                }
                nums[realIndex] = nums[index];
                realIndex++;
            }

            return realIndex;
        }
    }

    static class Solution27 {

        public int removeElement(int[] nums, int val) {
            if (nums.length < 1) {
                return 0;
            }
            int realIndex = 0;

            for (int index = 0; index < nums.length; index++) {
                if (nums[index] == val) {
                    continue;
                }
                nums[realIndex] = nums[index];
                realIndex++;
            }
            for (int index = 0; index < realIndex; index++) {
                System.out.println(nums[index]);
            }
            return realIndex;
        }
    }

    class Solution28 {

        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }

    static class Solution29 {

        public int divide(int dividend, int divisor) {
            if (dividend == 0) {
                return 0;
            }
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            boolean negative;
            negative = (dividend ^ divisor) < 0;//用异或来计算是否符号相异
            long t = Math.abs((long) dividend);
            long d = Math.abs((long) divisor);
            int result = 0;
            for (int i = 31; i >= 0; i--) {
                if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                    result += 1 << i;//将结果加上2^n
                    t -= d << i;//将被除数减去2^n*divisor
                }
            }
            return negative ? -result : result;//符号相异取反
        }
    }

    static class Solution30 {

        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> reuslt = new ArrayList<>();

            if (words.length < 1) {
                return reuslt;
            }
            int indexLenght = words[0].length();
            if (s.length() < indexLenght * words.length) {
                return reuslt;
            }
            Map<String, Integer> map = new ConcurrentHashMap<>();
            int[] unitCount = new int[words.length];

            for(int index =0;index<words.length;index++){
                if (map.containsKey(words[index])){
                    unitCount[map.get(words[index])]++;
                }else {
                    map.put(words[index],index);
                    unitCount[index] = 1;
                }
            }
            for (int index = 0; index < s.length(); index ++) {
                int tempLocation = index + indexLenght * words.length;
                if (tempLocation > s.length()) {
                    break;
                }
                if (isEqual(map, s.substring(index, tempLocation), indexLenght, words.length,unitCount.clone())) {
                    reuslt.add(index);
                }
            }
            return reuslt;

        }

        public boolean isEqual(Map<String, Integer> map, String s, int lengthOfValue, int n,int[] count) {
            if (s.length() < n) {
                return false;
            }
            for (int index = 0; index < s.length(); index = index + lengthOfValue) {
                String temp = s.substring(index, index + lengthOfValue);
                if (map.containsKey(temp)) {
                    if (count[map.get(temp)] > 0) {
                        count[map.get(temp)]--;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution25 solution22 = new Solution25();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";


        String[] words = new String[]{"fooo","barr","wing","ding","wing"};
        Solution30 solution30 = new Solution30();
        System.out.println(solution30.findSubstring(s, words));
        Solution29 solution29 = new Solution29();
        System.out.println(solution29.divide(-2147483648
            , 2));
    }
}

