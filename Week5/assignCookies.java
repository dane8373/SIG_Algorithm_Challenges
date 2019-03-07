//based off https://leetcode.com/problems/assign-cookies/
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        int cookie = 0;
        int child = 0;
        //sort the arrays
        Arrays.sort(g);
        Arrays.sort(s);
        //while we still have children or cookies left
        while (cookie < s.length && child < g.length) {
            //keep advancing until we have a cookie that the
            //least greedy child will accept
            while (cookie < s.length && s[cookie] < g[child]) {
                cookie++;
            }
            //check to see if we got out of the loop because
            //of a match or because we ran out of cookies
            if (cookie < s.length) {
                child++;
                cookie++;
            }
        }
        return child;
    }
}