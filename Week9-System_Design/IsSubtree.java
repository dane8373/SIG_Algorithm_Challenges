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
    public boolean equals(TreeNode s, TreeNode t) { 
        if (t == null && s == null) {
            return true;
        }
        else if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return equals(s.right, t.right) && equals(s.left,t.left);
    }
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null && s == null) {
            return true;
        }
        else if (s == null || t == null) {
            return false;
        }
        boolean ret = false;
        if (s.val == t.val) {
            ret =  equals(s.right, t.right) && equals(s.left,t.left);
            if (ret) {
                return ret;
            }
        }
        return isSubtree(s.right, t) || isSubtree(s.left,t);
    }
}