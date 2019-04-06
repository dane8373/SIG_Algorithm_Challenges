//based off https://leetcode.com/problems/trim-a-binary-search-tree/
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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        //null subtrees are already trimmed
        if (root == null) {
            return root;
        }
        //if this value is less than the min remove it and its left subtree
        //equivalent to returning the trimmed right subtree
        if (root.val < L) {
            root = trimBST(root.right, L, R);
        }
        //if this value is greater than the max remove it and its right subtree
        //equivalent to returning the trimmed left subtree
        else if (root.val > R) {
            root = trimBST(root.left,L,R);
        }
        //otherwise trim the right and left subtrees
        else {
            root.right = trimBST(root.right,L,R);
            root.left = trimBST(root.left,L,R);
        }
        return root;
    }
}