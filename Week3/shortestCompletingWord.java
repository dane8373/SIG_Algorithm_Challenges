//based off https://leetcode.com/problems/shortest-completing-word/
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        //make a hash map of all the letters in the liscense plate
        HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
        for (int i=0; i<licensePlate.length(); i++) {
            if (Character.isLetter(licensePlate.charAt(i))) {
                int count = 1;
                Character lowerChar = Character.toLowerCase(licensePlate.charAt(i));
                if (letters.containsKey(lowerChar)) {
                    count += letters.get(lowerChar);
                }
                letters.put(lowerChar, count);
            }
        }
        String shortest = "";
        //go through the words and see if they complete the plate
        for (int z=0; z<words.length; z++) {
            //if this word is larger than our shortest dont bother checking
            if (shortest != "" && words[z].length() >= shortest.length()) {
                continue;
            }
            //clone the hashmap from before
            HashMap<Character, Integer> tempLetters = (HashMap)letters.clone();
            for (int i=0; i<words[z].length(); i++) {
                Character lowerChar = Character.toLowerCase(words[z].charAt(i));
                if (tempLetters.containsKey(lowerChar)) {
                    int count = tempLetters.get(lowerChar);
                    if (count == 1) {
                        //remove the item from the map if we have at least the same amount
                        //of that character as the license plate
                        tempLetters.remove(lowerChar);
                    }
                    else {
                        tempLetters.put(lowerChar, count-1);
                    }
                }
            }
            //if we got all the letters, then we update shortest
            if (tempLetters.size() == 0) {
                shortest = words[z];
            }
        }
        return shortest;
    }
}