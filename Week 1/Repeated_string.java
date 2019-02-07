//based off https://leetcode.com/problems/repeated-substring-pattern
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String testString = ""; //candidate for repeated string
        for (int i=0; i<s.length()/2; i++) { //try all repeated strings of length <= n/2
            testString += s.charAt(i); //append a character to the candidate string
            if(s.length() % testString.length() != 0) { //if the strings original length isn't divisible by the candidate length
                continue;
            }
            int currentLetter = 0; //store the location in the repeated string we are checking
            boolean ret = false;
            for (int j=i+1; j<s.length(); j++) { //check all the letters after the candidate string
                ret = true;
                if(s.charAt(currentLetter) != s.charAt(j)) { //if any character breaks the pattern of the repeated string
                    ret = false;
                    break;
                }
                currentLetter++;
                if (currentLetter >= testString.length()) { //if we reach the end of the repeated string
                    currentLetter = 0;
                }
            }
            if (ret) { //if we didn't fail at any point in the above test
                return true;
            }
        }
        return false;
    }
}