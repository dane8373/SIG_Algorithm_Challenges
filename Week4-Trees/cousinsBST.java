//based off https://leetcode.com/problems/cousins-in-binary-tree/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    //helper to find the parent of a given node
    public TreeNode findParent(TreeNode root, int x) {
        if (root==null) {
            return null;
        }
        else {
            if (root.left != null && root.left.val == x || root.right!=null && root.right.val == x) {
                return root;
            }
            TreeNode left = findParent(root.left,x);
            if (left!= null) {
                return left;
            }
            else {
                return findParent(root.right,x);
            }
        }
    }
    
    //helper to find the depth of a given node
    public int find(TreeNode root, int x) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (x==root.val) {
            return 0;
        }
        else {
            int left = 0;
            int right = 0;
            left = 1 + find(root.left,x);
            right = 1 + find(root.right,x);
            return right > left ? right : left;
        }
    }
    public boolean isCousins(TreeNode root, int x, int y) {
        return find(root, x) == find(root,y) && findParent(root, x).val != findParent(root,y).val;
    }
}