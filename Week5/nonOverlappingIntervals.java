//based off https://leetcode.com/problems/non-overlapping-intervals/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0 || intervals.length == 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparing((Interval i) -> i.end));
        int currEnd = intervals[0].end;
        int count = 0;
        int i = 1;
        while (i<intervals.length) {
            while (i<intervals.length && intervals[i].start < currEnd) {
                count++;
                i++;
            }
            if (i<intervals.length) {
                currEnd=intervals[i].end;
            }
            i++;
        }
        return count;
    }
}