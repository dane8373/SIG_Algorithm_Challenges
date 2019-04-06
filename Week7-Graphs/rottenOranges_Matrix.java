//based off https://leetcode.com/problems/rotting-oranges/
class Solution {
    private static class Pair {
        public int first;
        public int second;
    }
    public int toOneD(Pair p, int length) {
        return p.first*length + p.second;
    }
    public static ArrayList<Pair> getNeighbors(Pair p, int length, int width) {
        ArrayList<Pair> ret = new ArrayList<Pair>();
        if (p.first>0) {
            Pair temp = new Pair();
            temp.first = p.first-1;
            temp.second = p.second;
            ret.add(temp);
        }
        if (p.second>0) {
            Pair temp = new Pair();
            temp.first = p.first;
            temp.second = p.second-1;
            ret.add(temp);
        }
        if (p.first<width-1) {
            Pair temp = new Pair();
            temp.first = p.first+1;
            temp.second = p.second;
            ret.add(temp);
        }
        if (p.second<length-1) {
            Pair temp = new Pair();
            temp.first = p.first;
            temp.second = p.second+1;
            ret.add(temp);
        }
        return ret;
    }
    public int orangesRotting(int[][] grid) {
        Queue<Pair> toVisit = new LinkedList<Pair>();
        HashMap<Integer,Integer> costs = new HashMap<Integer,Integer>();
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                Pair temp = new Pair();
                temp.first = i;
                temp.second = j;
                if (grid[i][j] == 1) {
                    costs.put(toOneD(temp,grid[i].length),Integer.MAX_VALUE); 
                }
                else if (grid[i][j] == 2) {
                    costs.put(toOneD(temp,grid[i].length),0);
                    toVisit.add(temp);
                } 
                else {
                    costs.put(toOneD(temp,grid[i].length),-1); 
                }
            }
        }
        //HashSet<Pair> visited = new HashSet<Pair>();
        while(toVisit.peek() != null) {
            Pair p = toVisit.poll();
            /*if (visited.contains(p)) {
                continue;
            }
            visited.add(p);*/
            for (Pair v: getNeighbors(p,grid[0].length,grid.length)) {
                if (costs.get(toOneD(v,grid[0].length)) > 0 && costs.get(toOneD(v,grid[0].length))
                    > 1+costs.get(toOneD(p,grid[0].length))) {
                    costs.put(toOneD(v,grid[0].length),1+costs.get(toOneD(p,grid[0].length)));
                    //v2.visited = false;
                    toVisit.add(v);
                }
            }
        }
        int max = 0;
        for (int i: costs.values()) {
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}