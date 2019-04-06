//https://leetcode.com/problems/k-closest-points-to-origin/
//presented to give a good example of when a HashMap is NOT the best
//priority queue probably performs better than TreeMap, but I didn't want
//to overencumber the code with writing a comparator class 
//and converting all the int[] s to ArrayList<Integer> s
class Solution {
    public int dist(int x, int y) {
        return x*x + y*y;
    }
    
    public int[][] kClosest(int[][] points, int K) {
        int[][] ret = new int[K][];
        int maxDist = 0;
        //map to store a distance, point index pair
        TreeMap<Integer, Integer> t = new TreeMap<Integer,Integer>();
        for (int i=0; i<points.length; i++) {
            t.put(dist(points[i][0],points[i][1]),i);
        }
        int i = 0;
        //get the first K elements from the tree
        for (int a: t.navigableKeySet()) {
            ret[i++] = points[t.get(a)];
            if (i == K) {
                break;
            }
        }
        return ret;
    }
}