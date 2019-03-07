//based off https://leetcode.com/problems/lemonade-change/
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fiveCount = 0;
        int tenCount = 0;
        for (int i=0; i<bills.length; i++) {
            if (bills[i] == 5) {
                fiveCount++;
            }
            else if (bills[i] == 10) {
                fiveCount--;
                if (fiveCount < 0) {
                    return false;
                }
                tenCount++;
            }
            else {
                if (fiveCount == 0) {
                    return false;
                }
                else if (tenCount == 0) {
                    if (fiveCount < 3) {
                        return false;
                    }
                    fiveCount-=3;
                }
                else {
                    fiveCount--;
                    tenCount--;
                }
            }
        }
        return true;
    }
}