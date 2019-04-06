//based off https://leetcode.com/problems/two-sum/
//possibly the most common hash map interview problem, I personally have had it ~5 times
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //map that stores all the numbers with their indecies
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i=0; i<nums.length; i++) {
            //if we found two of the same number, and that happens to sum up 
            //to k, then we need to stop now and return those indecies
            if (map.containsKey(nums[i]) && 2*nums[i] == target) {
                int[] ret = new int[2];
                ret[0] = map.get(nums[i]);
                ret[1] = i;
                return ret;
            }
            map.put(nums[i],i);
        }
        //go through everything in the map
        for (int key: map.keySet()) {
            //look for the number we need to add to this number to get K
            int other = target - key;
            //if we find it return the indecies
            if (map.containsKey(other) 
                && map.get(key) != map.get(other)) {
                int[] ret = new int[2];
                ret[0] = map.get(key);
                ret[1] = map.get(other);
                return ret;
            }
        }
        return null;
    }
}