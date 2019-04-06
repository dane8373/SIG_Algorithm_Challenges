//based off https://leetcode.com/problems/stone-game/
class Solution {
    int[][] answers;
    public int helper(int[] piles, int start, int end) {
        //if we are out of stones return 0;
        if (start > end) {
            return 0;
        }
        //if there is only one left return that one
        if (start == end) {
            return piles[start];
        }
        //if we have already solved this sub problem return the answer
        if (answers[start][end] != -1) {
            return answers[start][end];
        }
        //find the best possible value we can get if we take the left pile
        //this is equal to the left pile + the min of two moves from now
        //(Min since our opponent will play optimally and give us the worst move)
        int takeLeft = piles[start] + Math.min(helper(piles,start+2,end), helper(piles,start+1,end-1));
        int takeRight = piles[end] + Math.min(helper(piles,start+1,end-1), helper(piles,start,end-2));
        answers[start][end] = Math.max(takeLeft, takeRight);
        return answers[start][end];
    }
    public boolean stoneGame(int[] piles) {
        //intitialize our memoization
        answers = new int[piles.length][piles.length];
        for (int i=0; i<piles.length; i++) {
            for (int j=0; j<piles.length; j++) {
                answers[i][j]=-1;
            }
        }
        //we can win if and only if the amount of stones we get taking from range (0, length - 1)
        // (this is the range we get to choose to take stones from)
        //is greater than either the min of the ranges (1, length-1) and (0,length-2)
        // (this is the max range our opponent can get)
        //NOTE: This works since this is a zero sum game, meaning that the lower our opponents score
        //the higher ours will be. This approach wouldnt work for a non-zero sum game
        return helper(piles, 0, piles.length-1) > Math.min(helper(piles, 1,  piles.length-1), helper(piles, 0,  piles.length-2));
    }
}