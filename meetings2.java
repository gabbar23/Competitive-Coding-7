/**
 * Time Complexity: O(n log n)
 * - Sorting the intervals takes O(n log n).
 * - Processing each interval with heap operations takes O(n log n) in the worst case.

 * Space Complexity: O(n)
 * - The space required for the heap, which can grow to contain all n intervals.
 */


class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // Sort the intervals by their start time in ascending order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Min-heap to keep track of the minimum end time of overlapping meetings
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        // Add the first meeting's end time to the heap
        minHeap.add(intervals[0]);
        
        // Iterate through the remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            // Check the earliest ending meeting
            int[] top = minHeap.peek();
            
            // If the current meeting starts after the earliest ending meeting ends,
            // reuse the meeting room (remove the top element)
            if (top[1] <= intervals[i][0]) {
                minHeap.poll(); // Remove the meeting room that is now free
            }
            
            // Add the current meeting's end time to the heap (either new room or reused)
            minHeap.add(intervals[i]);
        }
        
        // The size of the heap is the minimum number of meeting rooms required
        return minHeap.size();
    }
}
