//based off https://leetcode.com/problems/longest-palindromic-substring/submissions/
//I wrote out a top down recurrence and stole this code from one of the comments because I was lazy
class Solution {
    public String longestPalindrome(String s) {
    int length = s.length();

	if (s == null || length < 2) { //input checking, any string with length <2 is a palindrome
		return s;
	}
    //boolean array used to store whether or not the substring starting and index i and ending at index j is a substring
	boolean[][] isPalindromic = new boolean[length][length];

	int left = 0, right = 0; //the left and right indecies of the longest substring we have found

	for (int j = 1; j < length; j++) {
		for (int i = 0; i < j; i++) {
            //check to see if the substring from index i+1 to j-1 is a palindrome
			boolean isInnerWordPalindrome = isPalindromic[i + 1][j - 1]
					|| j - i <= 2; 
            //if the substring from index i+1 to j-1 is a plaindrome and the characters at i and j are equal 
            //then the substring from i to j is a palindrome
			if (s.charAt(i) == s.charAt(j) && isInnerWordPalindrome) {
				isPalindromic[i][j] = true;
                //if this palindrome is bigger than the biggest one we have found then store it
				if (j - i > right - left) {
					left = i;
					right = j;
				}
			}
		}
    }
        //java substring method gets cranky if you use an end index too high
        if (right == s.length()) {
            return s.substring(left);
        }
        //java syntax dictates the +1 one since substring(i,j) gives only the characters from i to j-1.
        return s.substring(left,right+1);
    }
}
