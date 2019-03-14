//based off https://leetcode.com/problems/knight-dialer/
class Solution {
    //answers mod 10^9 +7
    final int bound = (int)(Math.pow(10,9)+7);
    int[][] answers;
    HashMap<Integer,ArrayList<Integer>> reachableNumbers;
    public int helper(int number, int N) {
        //only 1 number we can make with 0 moves is the current number
        if(N == 0) {
            return 1;
        }
        //if we have already computed this subproblem return the answer
        if (answers[number][N] != -1) {
            return answers[number][N];
        }
        int count = 0;
        //otherwise add all the numbers we can get from all the other
        //reachable numbers in N-1 moves
        for (int num: reachableNumbers.get(number)) {
            //DO NOT use += with mod
            //it won't work right
            count = (count + helper(num, N-1))% bound;
        }
        //set the meoization
        answers[number][N] = count;
        return count;
    }
    public int knightDialer(int N) {
        int count = 0;
        //intialize the list of locations each number the knight can reach starting from the others
        reachableNumbers = new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0; i<=9; i++) {
            reachableNumbers.put(i, new ArrayList<Integer>());
        }
        reachableNumbers.get(0).add(4);
        reachableNumbers.get(0).add(6);
        reachableNumbers.get(1).add(6);
        reachableNumbers.get(1).add(8);
        reachableNumbers.get(2).add(7);
        reachableNumbers.get(2).add(9);
        reachableNumbers.get(3).add(4);
        reachableNumbers.get(3).add(8);
        reachableNumbers.get(4).add(3);
        reachableNumbers.get(4).add(9);
        reachableNumbers.get(4).add(0);
        //5 can't reach anybody
        reachableNumbers.get(6).add(1);
        reachableNumbers.get(6).add(7);
        reachableNumbers.get(6).add(0);
        reachableNumbers.get(7).add(2);
        reachableNumbers.get(7).add(6);
        reachableNumbers.get(8).add(1);
        reachableNumbers.get(8).add(3);
        reachableNumbers.get(9).add(2);
        reachableNumbers.get(9).add(4);
        answers = new int[10][N+1];
        //initialize our memoization
        for (int i=0; i<=9; i++) {
            for (int j = 0; j<=N; j++) {
                answers[i][j]=-1;
            }
        }
        //loop through all possible starting numbers;
        for (int i=0; i<=9; i++) {
            //DO NOT use += with mod
            count =  (count + helper(i, N-1)) %bound;
        }
        return count;
    }
}