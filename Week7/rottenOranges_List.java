//based off https://leetcode.com/problems/rotting-oranges/
class Solution {
    private class Vertex {
        Vertex() {
            outgoing = new ArrayList<Vertex>();
            visited = false;
            time = Integer.MAX_VALUE;
        }
        public ArrayList<Vertex> outgoing;
        //some extra class variables used for specifics
        //of this problem
        public boolean visited;
        public int rotten;
        public int time;
    }
    public int orangesRotting(int[][] grid) {
        HashMap<Integer,Vertex> graph = new HashMap<Integer,Vertex>();
        Queue<Vertex> toVisit = new LinkedList<Vertex>();
        //go through every index of the grid
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[i].length; j++) {
                //ignore the zero grids
                if (grid[i][j] == 0) {
                    continue;
                }
                Vertex v = new Vertex();
                v.rotten = grid[i][j];
                //extra initialization for rotten oranges
                if (v.rotten == 2) {
                    v.time = 0;
                    toVisit.add(v);
                }
                //use conversion from (row,column) to greater row indecxing;
                //so I can have my hashtable have integer keys
                graph.put(i*grid[i].length+j,v);
                //if i>0, add the upper neighbor if it exists
                if (i>0) {
                    //if the key corresponding to index row i-1 column j has an orange
                    if (graph.containsKey((i-1)*grid[i-1].length+j)) {
                        //add an edge between both vertices
                        graph.get((i-1)*grid[i-1].length+j).outgoing.add(v);
                        v.outgoing.add(graph.get((i-1)*grid[i-1].length+j));
                    }
                }
                //if j>o, add the left neighbor if it exists
                if (j>0) {
                    //if the key corresponding to index row i column j-1 has an orange
                    if (graph.containsKey(i*grid[i].length+j-1)) {
                        //add an edge between both vertices
                        graph.get(i*grid[i].length+j-1).outgoing.add(v);
                        v.outgoing.add(graph.get(i*grid[i].length+j-1));
                    }
                }
                //NOTICE: If everyone adds their upper and left neighbor,
                //then we never need to worry about lower and right neighbor
                //because if you have a lower or right neighbor, they will add you
            }
        }
        //BFS
        while(toVisit.peek() != null) {
            Vertex v = toVisit.poll();
            if (v.visited) {
                continue;
            }
            v.visited = true;
            for (Vertex v2: v.outgoing) {
                //if this is a shorter distance than what he have before
                //update it and all its neighbors
                if (v2.time > 1+v.time) {
                    v2.time = 1+v.time;
                    v2.visited = false;
                    v2.rotten = 2;
                    toVisit.add(v2);
                }
            }
        }
        int max = 0;
        for (Vertex v: graph.values()) {
            //if not all the apples are rotten
            if (v.rotten == 1) {
                return -1;
            }
            if (v.time > max) {
                max = v.time;
            }
        }
        return max;
    }
}