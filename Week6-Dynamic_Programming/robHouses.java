//based off https://leetcode.com/problems/house-robber/

//TOP DOWN SOLUTION
class Solution {
    int[] answers; //Stores the best value for a subarray ending at index i
    public int helper(int[] nums, int end) {
        //base case, 0 if there are > 0 houses left
        if (end < 0) {
            return 0;
        }
        //return the 1 house if there is only 1
        if (end == 0) {
            return nums[0];
        }
        //if we have already sovled this sub problem, return the answer we found
        if (answers[end] != -1) {
            return answers[end];
        }
        //either we take the house at index end, or we do not
        //max value we can get without taking this house is just the max of the index before
        int dontTake = helper(nums, end-1);
        //max value we can get is the max value from 2 indexes ago + the current house 
        //(2 indexes ago because we can't take adjacent houses)
        int take = nums[end] + helper(nums,end-2);
        //pick the max
        answers[end]= Math.max(dontTake,take);
        return answers[end];
    }
    public int rob(int[] nums) {
        //initialize our memoization
        answers = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            answers[i] = -1;
        }
        return helper(nums, nums.length-1);
    }
}

//BOTTOM UP APPROACH
class Solution {
    public int rob(int[] nums) {
        //bunch of base cases since the algorithm only works for
        //arrays of size > 2
        if (nums.length == 0) {
            return 0;
        }
        else if (nums.length == 1) {
            return nums[0];
        } 
        int oldMax = Math.max(nums[0],nums[1]); //best we can get without taking the current house
        if (nums.length == 2) {
            return oldMax;
        }
        int oldOldMax = nums[0]; //best we can get from 2 indexes ago
        int curr = 0; //amount we can get with taking current house
        for (int i=2; i<nums.length; i++) {
            //current max = max of 2 indexes ago + current house
            curr = oldOldMax + nums[i];
            //take the max of either taking this house, or the max without it
            curr = Math.max(curr,oldMax);
            oldOldMax = oldMax;
            oldMax = curr;
        }
        return curr;
    }
}