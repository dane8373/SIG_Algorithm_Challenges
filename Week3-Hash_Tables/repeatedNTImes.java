//based off https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
class Solution {
    public int repeatedNTimes(int[] A) {
        //map that stores the count of each number
        HashSet<Integer> numbers = new HashSet<Integer>();
        for (int i=0; i<A.length; i++) {
            //if we have already seen a number then it must be the repeated one
            if (numbers.contains(A[i])) {
                return A[i];
            }
            numbers.add(A[i]);
        }
        return 0;
    }
}