//based off https://leetcode.com/problems/different-ways-to-add-parentheses/
class Solution {
    //helper function to determine if something is an operation
    public boolean isOp(Character c) {
        return c=='-' || c =='+' || c=='*';
    }
    
    //helper function to convert a character into an operation
    public int doOp(int a, int b, Character c) {
        if (c == '+') {
            return a+b;
        }
        else if (c == '-') {
            return a-b;
        }
        else {
            return a*b;
        }
    }

    //recursive helper function to evaluate all possible configureations
    public List<Integer> evalTree(List<Integer> numbers, List<Character> ops, int startOp, int endOp) {
        List<Integer> retList = new ArrayList<Integer>();
        //startOp > endOp, there are no operations left, just return the number after the last op we did
        if (startOp > endOp) {
            retList.add(numbers.get(endOp+1));
            return retList;
        }
        //otherwise, for every remaining operation, calulate an operation tree with that operation as the root
        for (int i=startOp; i<=endOp; i++) {
            //evaluate all possible values of the left subtree of this operation
            List<Integer> left = evalTree(numbers,ops,startOp,i-1);
            //evaluate all possible values of the right subtree of this operation
            List<Integer> right = evalTree(numbers,ops,i+1,endOp);
            //perform all possible combinations of left (op) right
            for (Integer l: left) {
                for (Integer r: right) {
                    retList.add(doOp(l,r,ops.get(i)));
                }
            }
        }
        return retList;
    }
    
    public List<Integer> diffWaysToCompute(String input) {
        //parse the input into numbers and operations
        if (input.length() == 0) {
            return new ArrayList<Integer>();
        }
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<Character> ops = new ArrayList<Character>();
        int start = 0;
        int end = 0;
        //for the whole input
        while (end < input.length()) {
            //if we hit an op, then all the stuff before was a number
            if (isOp(input.charAt(end))) {
                //parse the number and ad it
                numbers.add(Integer.parseInt(input.substring(start,end)));
                //put the operator in
                ops.add(input.charAt(end));
                end++;
                start = end;
            }
            else {
                end++;
            }
        }
        //add the last number
        numbers.add(Integer.parseInt(input.substring(start)));
        if (ops.size() == 0) {
            return numbers;
        }
        //recursively solve
        List<Integer> ret = evalTree(numbers, ops, 0, ops.size()-1);
        Collections.sort(ret);
        return ret;
    }
}