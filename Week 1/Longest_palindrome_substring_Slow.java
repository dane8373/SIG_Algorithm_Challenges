//based off https://leetcode.com/problems/longest-palindromic-substring/
class Solution {
    public String longestPalindrome(String s) {
        String testString = ""; //string we will check to see if is palindrome
        String maxString = ""; //longest palindrome so far
        for (int i=0; i< s.length(); i++) {
            testString = ""; //reset the palindrome for every start index
            for (int j=i; j<s.length(); j++) {
                testString+=s.charAt(j); //add one chacaracter to the test string
                if (testString.length() > maxString.length()) { //don't bother testing if the test string wouldnt be longest anyway
                    boolean isPalindrome = true;
                    for (int k=0; k<testString.length(); k++) { //this for loop checks to see if the teststring is a palindrome
                        if (testString.charAt(k) != testString.charAt(testString.length()-1-k)) {
                            isPalindrome = false;
                            break;
                        }
                    }
                    if (isPalindrome) { //if this is a plaindrome it is the longest
                        maxString = testString;
                    }
                }
            }
        }
        return maxString;
    }
}