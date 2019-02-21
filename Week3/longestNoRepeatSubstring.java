//based off https://leetcode.com/problems/longest-substring-without-repeating-characters/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //map to store the latest index of a particular character
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        //last index where we had to trim due to encountering a repeated character
        int lastDelete = 0;
        for (int i=0; i<s.length(); i++) {
            //if we encounter a character that is the same as one we have seen before
            //and we didn't already trim it off
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= lastDelete) {
                if (i - lastDelete > max) {
                    max = i - lastDelete;
                }
                //update the trim point
                lastDelete = map.get(s.charAt(i)) + 1;
            }
            //update the index of this character
            map.put(s.charAt(i), i);
        }
        //if we never found a repeat then we just return the length
        if (lastDelete == 0) {
            return s.length();
        }
        //otherwise return the length minus the index of where we last trimmed
        if (s.length() - lastDelete > max) {
            max = s.length() - lastDelete;
        }
        return max;
    }
}