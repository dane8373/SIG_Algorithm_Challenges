//from https://leetcode.com/problems/reverse-only-letters/
class Solution {
    public String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder(S);
        int front = 0;
        int back = S.length() - 1;
        while (front < back) {
            if (!Character.isLetter(sb.charAt(front))) {
                front++;
            }
            else if (!Character.isLetter(sb.charAt(back))) {
                back--;
            }
            else {
                char temp = sb.charAt(front);
                sb.setCharAt(front++, sb.charAt(back));
                sb.setCharAt(back--, temp);
            }
        }
        return sb.toString();
    }
}