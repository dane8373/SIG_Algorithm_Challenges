//based off https://www.hackerrank.com/challenges/ctci-merge-sort/problem
public class Solution {
    static long countInversions(int[] arr) {
        /* base cases for lengths of 0,1,2 */
        if (arr.length <= 1) {
            return 0;
        }
        else if (arr.length == 2) {
            if (arr[0] > arr[1]) {
                int temp = arr[1];
                arr[1] = arr[0];
                arr[0] = temp;
                return 1;
            }
            return 0;
        }
        /* split the array in two */
        int start = 0;
        int end = arr.length-1;
        int middle = (end - start)/2 + start;
        int [] left = new int[middle-start+1];
        int j = 0;
        for (int i=start; i<=middle; i++) {
            left[j++] = arr[i];
        }
        int [] right = new int[end-(middle+1)+1];
        j = 0;
        for (int i=middle+1; i<=end; i++) {
            right[j++] = arr[i];
        }
        /* count the inversions in both halves */
        long count = countInversions(left);
        count += countInversions(right);
        int mainIndex = 0;
        int leftIndex = 0;
        int rightIndex = 0;
        /* while both of the arrays are not finished */
        while(rightIndex < right.length && leftIndex < left.length) {
            //if the smallest thing on the right is 
            //smaller than the smallest on the left
            if (right[rightIndex] < left[leftIndex]) {
                //this element on the right was after 
                //left.length - leftIndex items that are greater than it
                count += left.length - leftIndex;
                arr[mainIndex] = right[rightIndex];
                mainIndex++;
                rightIndex++;
                //if the right is fully processed put 
                // all the left elements in
                if (rightIndex >= right.length) {
                    while (leftIndex < left.length) {
                        arr[mainIndex] = left[leftIndex];
                        leftIndex++;
                        mainIndex++;
                    }
                }
            }
            //if the smallest thing on the left is the 
            //smallest then we don't have an inversion for this element
            else {
                arr[mainIndex] = left[leftIndex];
                mainIndex++;
                leftIndex++;
                //if the left is fully processed empty the right
                if (leftIndex >= left.length) {
                    while (rightIndex < right.length) {
                        arr[mainIndex] = right[rightIndex];
                        rightIndex++;
                        mainIndex++;
                    }
                }
            }
        }
        return count;
    }
}