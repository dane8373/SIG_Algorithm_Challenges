//based off https://leetcode.com/problems/verifying-an-alien-dictionary/
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        //encoding the alien ordering into the hashmap
        for (int i=0; i<order.length(); i++) {
            map.put(order.charAt(i),i);
        }
        //use transitive property of alphabetical ordering
        //only need to check if a string is out of place with respect
        //to its immediate neighbor
        for (int i = 0; i<words.length-1; i++) {
            for (int j=0; j<words[i].length(); j++) {
                //if the next word is a proper prefix or has a character that is
                //alphabetically smaller after a prefix
                if (j >= words[i+1].length() || 
                    map.get(words[i].charAt(j)) > map.get(words[i+1].charAt(j))) {
                    return false;
                }
                //if the other word has an alphabetically higher prefix
                //exit
                else if (j > words[i+1].length() || 
                    map.get(words[i].charAt(j)) < map.get(words[i+1].charAt(j))) {
                    break;
                }
                //otherwise try a longer prefix
            }
        }
        //if we found no violations then the list is sorted
        return true;
    }
}