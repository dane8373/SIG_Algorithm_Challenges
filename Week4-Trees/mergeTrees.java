//based off https://leetcode.com/problems/merge-two-binary-trees/
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //if one of the trees is null then just return the other
        if (t1 == null) {
            return t2;
        }
        else if (t2 == null) {
            return t1;
        }
        //otherwise add the two values at the root together and merge
        //the right and left subtrees recursively
        else {
            TreeNode T = new TreeNode(t1.val + t2.val);
            T.left = mergeTrees(t1.left, t2.left);
            T.right = mergeTrees(t1.right, t2.right);
            return T;
        }
    }
}