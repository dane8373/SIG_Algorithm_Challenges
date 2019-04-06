https://leetcode.com/problems/friend-circles/
class Solution {
    public int findCircleNum(int[][] M) {
        int groupCount = 0;
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        //add all rows to the stack
        for (int i=0; i<M.length; i++) {
            s.push(i);
        }
        //DFS
        while (!s.empty()) {
            int i = s.pop();
            //increase the group count if we haven't already put
            //this person in a group, make a new group for them
            if (!visited.contains(i)) {
                groupCount++;
            }
            visited.add(i);
            for (int j =0; j<M[i].length; j++) {
                if (M[i][j] == 1 && !visited.contains(j)) {
                    visited.add(j);
                    s.push(j);
                }
            }
        }
        return groupCount;
    }
}