package com.bitmain.demo;

import java.util.ArrayList;
import java.util.List;

public class LeetCode91To100 {

	public class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}


	class Solution94 {

		public List<Integer> inorderTraversal(TreeNode root) {
			List<Integer> result = new ArrayList<>();
			dd(root, result);
			return result;
		}

		public void dd(TreeNode root, List<Integer> list) {
			if (root == null) {
				return;
			}
			dd(root.left, list);
			list.add(root.val);
			dd(root.right, list);
		}
	}

}
