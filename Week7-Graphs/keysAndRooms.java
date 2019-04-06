//based off https://leetcode.com/problems/keys-and-rooms/
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //hashset to keep track of which rooms we visited
        HashSet<Integer> visited = new HashSet<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        s.add(0);
        //DFS
        while (!s.empty()) {
            int curr = s.pop();
            visited.add(curr);
            for (int i: rooms.get(curr)) {
                if (!visited.contains(i)) {
                    s.add(i);
                }
            }
        }
        //if the visited hashset is the same size as the room list
        //then all rooms were visited
        return visited.size() == rooms.size();
    }
}