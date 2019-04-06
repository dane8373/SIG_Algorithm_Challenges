//https://leetcode.com/problems/score-after-flipping-matrix/
class Solution {
    public int matrixScore(int[][] A) {
        int score = 0;
        //first, greedily make all the first column 1
        for (int z=0; z<A.length; z++) {
            if (A[z][0] == 1) {
                continue;
            }
            else {
                for (int i=0; i<A[z].length; i++) {
                    if (A[z][i] == 0) {
                        A[z][i]=1;
                    }
                    else {
                        A[z][i]=0;
                    }
                }
            }
        }
        score+=A.length*Math.pow(2,A[0].length-1);
        //look through all the columns and see if you should flip them
        for (int i=1; i<A[0].length; i++) {
            int count = 0;
            for (int j=0; j<A.length; j++) {
                if (A[j][i] == 0) {
                    count++;
                }
            }
            if (count > A.length/2 ) {
                for (int j=0; j<A.length; j++) {
                    if (A[j][i] == 0) {
                        score+=Math.pow(2,A[0].length-1-i);
                    }
                }
            }
            else {
                for (int j=0; j<A.length; j++) {
                    if (A[j][i] == 1) {
                        score+=Math.pow(2,A[0].length-1-i);
                    }
                }
            }
        }
        return score;
    }
}