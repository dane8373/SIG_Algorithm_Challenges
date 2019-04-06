//based off https://leetcode.com/problems/majority-element/
class Solution {
    
    public int majorityHelper(int[] nums, int start, int end) {
        //if there is only one element then that element is the majority element
        if (start == end) {
            return nums[start];
        }
        //divide the problem into two parts
        int middle = (end - start) / 2 + start;
        int majLeft = majorityHelper(nums,start,middle);
        int majRight = majorityHelper(nums,middle+1, end);
        //if the two majorities are equal, then that must be the
        //gloabl majority
        if (majLeft == majRight) {
            return majLeft;
        }
        else {
            //count the instances of the left majority in the full array
            int count = 0;
            for (int i=start; i<=end; i++) {
                if (nums[i] == majLeft) {
                    count++;
                }
            }
            //if this is the majority element the return it
            if (count > (end-start)/2) {
                return majLeft;
            }
            int count = 0;
            //otherwise count the right majority element
            for (int i=start; i<=end; i++) {
                if (nums[i] == majRight) {
                    count++;
                }
            }
            //if this is the majority element return it
            if (count > (end-start)/2) {
                return majRight;
            }
            //if neither were the majority element then there is no majority element
            return -1;
        }
    }
    
    public int majorityElement(int[] nums) {
        int l = 0;
        int r = 0;
        return majorityHelper(nums,0,nums.length-1);
    }
}