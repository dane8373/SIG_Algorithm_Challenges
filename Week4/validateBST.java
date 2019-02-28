//based off https://leetcode.com/problems/validate-binary-search-tree/
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
    //helper function to find max Node in a subtree
    public long maxNode(TreeNode root) {
        if (root == null) {
            //return a really small number for null trees
            return Long.MIN_VALUE;
        }
        //otherwise keep going right unttil we cant, the farthest right node is the max
        if (root.right == null) {
            return root.val;
        }
        return maxNode(root.right);
    }
    
    //helper function to find min Node in a subtree
    public long minNode(TreeNode root) {
        if (root == null) {
            //return a really big number for null trees
            return Long.MAX_VALUE;
        }
        //otherwise keep going left unttil we cant, the farthest left node is the min
        if (root.left == null) {
            return root.val;
        }
        return minNode(root.left);
    }
    
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        else {
            return isValidBST(root.right) && isValidBST(root.left) 
                && (root.val < minNode(root.right))
                && (root.val > maxNode(root.left));
        }
    }
}