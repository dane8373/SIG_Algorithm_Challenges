//based off https://leetcode.com/problems/unique-binary-search-trees/
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
    public int numTrees(int n) {
        //only one tree of size 0
        if (n == 0) {
            return 1;
        }
        int count = 0;
        //cont the number of trees rooted at n
        //for any tree, rooted at n, you can add the number of trees of the form n+1->N to the right subtree
        //Note that the number of trees from n+1->N is the same as 1->n-1
        //multiply these together to get the total    
        for (int i=n; i>=1; i--) {
            count += numTrees(n-i) * numTrees(i-1);
        }
        return count;
    }
}