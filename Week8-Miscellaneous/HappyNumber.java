https://leetcode.com/problems/happy-number/
class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<Integer>();
        while (n > 1) {
            seen.add(n);
            int temp = 0;
            while (n > 0) {
                temp += ((n%10) * (n%10));
                n=n/10;
            }
            n=temp;
            if (seen.contains(n)) {
                return false;
            }
        }
        return true;
    }  
}