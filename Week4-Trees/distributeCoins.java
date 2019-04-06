//based off https://leetcode.com/problems/distribute-coins-in-binary-tree/
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
    //helper function to determine if a node is a leaf
    public boolean isLeaf(TreeNode root) {
        if (root == null) {
            return false;
        }
        return root.right == null && root.left == null;
    }
    
    public int distributeCoins(TreeNode root) {
        if (root == null) {
            return 0;
        }
        else if (isLeaf(root)) {
            //parents handle their children so leaf nodes don't do anything
            return 0;
        }
        else {
            int count = 0;
            //if you have leaf children, then give them coins
            if (isLeaf(root.right)) {
                root.val += (root.right.val-1);
                count += Math.abs(root.right.val-1);
            } 
            else {
                //otherwise count the moves it takes your children
                //to distribute their coins
                count += distributeCoins(root.right);
                if (root.right!=null) {
                    //distribute coins to your children
                    root.val += (root.right.val-1);
                }
            }
            if (isLeaf(root.left)) {
                root.val += (root.left.val-1);
                count += Math.abs(root.left.val-1);
            }
            else {
                count += distributeCoins(root.left);
                if (root.left!=null) {
                    root.val += (root.left.val-1);
                }
            }
            return count + Math.abs(root.val-1);
        }
    }
}