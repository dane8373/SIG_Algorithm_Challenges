//based off https://leetcode.com/problems/jewels-and-stones/
class Solution {
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        //add all the jewels to a hashset
        HashSet<Character> jewels = new HashSet<Character>();
        for (int i=0; i<J.length(); i++) {
            jewels.add(J.charAt(i));
        }
        //go through all our rocks
        for (int i=0; i<S.length(); i++) {
            //increase count by 1 if a given rock is a jewel
            count += jewels.contains(S.charAt(i)) ? 1 : 0;
        }
        return count;
    }
}