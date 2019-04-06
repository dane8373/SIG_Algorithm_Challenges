//based off https://leetcode.com/problems/sort-characters-by-frequency/
class Solution {
    public String frequencySort(String s) {
        //step 1: count the frequency of all elements with a hashmap
        HashMap<Character,Integer> counts = new HashMap<Character,Integer>();
        for (int i=0; i<s.length(); i++) {
            int count = 1;
            if (counts.containsKey(s.charAt(i))) {
                count += counts.get(s.charAt(i));
            }
            counts.put(s.charAt(i), count);
        }
        //step 2: build an ordered map of Integer Character pairs ordered by the counts from the hashmap
        //since there can be more than one character with the same count, we need to use an
        //integer, ArrayList<character> pair, so we can store all unique pairs.
        //count probably use a priority queue for this and be a little faster but I am more familiar with Treemaps
        TreeMap<Integer,ArrayList<Character>> order = new TreeMap<Integer,ArrayList<Character>>();
        for (Character key: counts.keySet()) {
            if (!order.containsKey(counts.get(key))) {
                order.put(counts.get(key),new ArrayList<Character>());
            }
            ArrayList<Character> temp = order.get(counts.get(key));
            temp.add(key);
            order.put(counts.get(key), temp);
        }
        //Step 3: from smallest to biggest add the characters to the end of the string
        //rebuild the original string's letters by putting in the same count of each letter
        String ret = "";
        //for each frequency
        for (int i: order.navigableKeySet()) {
            ArrayList<Character> chars = order.get(i);
            //for each character at this frequency
            for (int k=0; k<chars.size(); k++) {
                //put in the same count of each letter
                for (int z=0; z<i; z++) {
                    ret = chars.get(k) + ret;
                }
            }
        }
        return ret;
    }
}