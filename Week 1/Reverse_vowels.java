//based off https://leetcode.com/problems/reverse-vowels-of-a-string/
class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        int front = 0;
        int back = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (front < back) {
            //keep advancing the front pointer until we get to a vowel
            while (front < back && !vowels.contains(Character.toLower(sb.charAt(front))) {
                front++;
            }
            //keep advancing the back pointer until we get to a vowel
            while (front < back && !vowels.contains(Character.toLower(sb.charAt(back))) {
                back--;
            }
            //always swap, we either got to a vowel and want to swap
            //or there were no vowels and front == back and we do a harmless swap
            char temp = sb.charAt(front);
            sb.setCharAt(front++, sb.charAt(back));
            sb.setCharAt(back--, temp);
        }
        return sb.toString();
    }
}