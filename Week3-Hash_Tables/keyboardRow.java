//based off https://leetcode.com/problems/keyboard-row/
class Solution {
    public String[] findWords(String[] words) {
        //hashmap that store the relationship between a letter and its row
        HashMap<Character, Integer> rows = new HashMap<Character,Integer>();
        //arduous process of encoding the character, keyboard row relationships
        rows.put('q',1);
        rows.put('w',1);
        rows.put('e',1);
        rows.put('r',1);
        rows.put('t',1);
        rows.put('y',1);
        rows.put('u',1);
        rows.put('i',1);
        rows.put('o',1);
        rows.put('p',1);
        rows.put('a',2);
        rows.put('s',2);
        rows.put('d',2);
        rows.put('f',2);
        rows.put('g',2);
        rows.put('h',2);
        rows.put('j',2);
        rows.put('k',2);
        rows.put('l',2);
        rows.put('z',3);
        rows.put('x',3);
        rows.put('c',3);
        rows.put('v',3);
        rows.put('b',3);
        rows.put('n',3);
        rows.put('m',3);
        ArrayList<String> ret = new ArrayList<String>();
        //go through all the words and see if they can be spelled with one row
        for (int i=0; i<words.length; i++) {
            if (words[i].length() <= 0) {
                continue;
            }
            //see what row the first character is in
            int currRow = rows.get(Character.toLowerCase(words[i].charAt(0)));
            boolean good = true;
            for (int j=1; j<words[i].length(); j++) {
                //if any characters are in different rows stop looking
                if (rows.get(Character.toLowerCase(words[i].charAt(j))) != currRow) {
                    good = false;
                    break;
                }
            }
            if (good) {
                ret.add(words[i]);
            }
        } 
        //need to convery my cool ArrayList<String> into a lame String[]
        //probably could use the .toArray() method here but that is forbidden magic
        String[] retArray = new String[ret.size()];
        int i = 0;
        for (String s: ret) {
            retArray[i++] = s;
        }
        return retArray;
    }
}