//based off https://leetcode.com/problems/k-th-symbol-in-grammar/
class Solution {
    public int kthGrammar(int N, int K) {
        //the 1st row is only zero
        if (N==1) {
            return 0;
        }
        //otherwise, find the node that led to this item
        int predescessor = kthGrammar(N-1,K/2+K%2);;
        if (predescessor == 0) {
            //if pred is 0, and K is even, then return 1 otherwise 0
            return K%2 == 0 ? 1 : 0;
        }
        else {
            //if pred is 0, and K is even, then return 0 otherwise 1
            return K%2 == 0 ? 0 : 1;
        }
    }
};
