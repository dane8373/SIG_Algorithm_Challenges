//based off https://leetcode.com/problems/uncommon-words-from-two-sentences/
//decent amount of string manipulation to extract the words
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        //maps for all words in A and B
        HashMap<String, Integer> aWords = new HashMap<String, Integer>();
        HashMap<String, Integer> bWords = new HashMap<String, Integer>();
        String temp = "";
        int count = 1;
        //find all the words spearated by spaces and add them to the map
        for (int i=0; i<A.length(); i++) {
            if (A.charAt(i) == ' ') {
                count = 1;
                if (aWords.containsKey(temp)) {
                    count += aWords.get(temp);
                }
                aWords.put(temp,count);
                temp = "";
            }
            else {
                temp += A.charAt(i);
            }
        }
        //add the last word that wasnt followed by a space
        count = 1;
        if (aWords.containsKey(temp)) {
            count += aWords.get(temp);
        }
        aWords.put(temp,count);
        temp = "";
        //do the same stuff for B
        for (int i=0; i<B.length(); i++) {
            if (B.charAt(i) == ' ') {
                count = 1;
                if (bWords.containsKey(temp)) {
                    count += bWords.get(temp);
                }
                bWords.put(temp,count);
                temp = "";
            }
            else {
                temp += B.charAt(i);
            }
        }
        count = 1;
        if (bWords.containsKey(temp)) {
            count += bWords.get(temp);
        }
        bWords.put(temp,count);
        temp = "";
        ArrayList<String> words = new ArrayList<String>();
        //go through all the words in A and see if they are uncommon
        for (String s: aWords.keySet()) {
            if (aWords.get(s) == 1 && !bWords.containsKey(s)) {
                words.add(s);
            }
        }
        same with B
        for (String s: bWords.keySet()) {
            if (bWords.get(s) == 1 && !aWords.containsKey(s)) {
                words.add(s);
            }
        }
        //convert my cool arraylist to a less cool array
        String[] ret = new String[words.size()];
        for (int i=0; i<words.size(); i++) {
            ret[i]=words.get(i);
        }
        return ret;
    }
}