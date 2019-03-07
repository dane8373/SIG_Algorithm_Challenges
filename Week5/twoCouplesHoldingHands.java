//based off https://leetcode.com/problems/couples-holding-hands/
class Solution {
    public int minSwapsCouples(int[] row) {
        //maps used to describe the pairs and locations of all numbers
        HashMap<Integer,Integer> locations = new HashMap<Integer, Integer>();
        HashMap<Integer,Integer> pairs = new HashMap<Integer, Integer>();
        locations.put(row.length,-1);
        //set up the pair/location mpas
        for (int i=0; i<row.length; i++) {
            locations.put(row[i],i);
        }
        for (int i=0; i<row.length; i+=2) {
            pairs.put(i, i+1);
            pairs.put(i+1, i);
        }
        int count = 0;
        //process people from left to right
        for (int i=0; i<row.length; i+=2) {
            //if they are already seated together do nothing
            if (i-locations.get(pairs.get(row[i])) == -1) {
                continue;
            }
            //otherwise swap the person to the right with the pair
            else {
                int myRight = row[i+1];
                row[i+1] = pairs.get(row[i]);
                row[locations.get(pairs.get(row[i]))] = myRight;
                int temp = locations.get(myRight);
                locations.put(myRight, locations.get(pairs.get(row[i])));
                locations.put(pairs.get(row[i]), temp);
                count++;
            }
        }
        return count;
    }
}