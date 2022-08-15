import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //Given an array of meeting time intervals intervals
        // where intervals[i] = [starti, endi],
        // return the minimum number of conference rooms required.


        Interval i = new Interval(5, 10);
        Interval j = new Interval(15, 20);
        Interval k = new Interval(0, 30);

        Interval[] intervals = new Interval[]{i, j, k};

        //Output: 2
        System.out.println(minRoom(intervals));

    }

    public static int minRoom(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // [0, 30], [5, 10], [15, 20];
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);

        minHeap.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval current = intervals[i];
            Interval earliest = minHeap.remove();

            if (current.start >= earliest.end) {
                earliest.end = current.end;
            } else {
                minHeap.add(current);
            }

            minHeap.add(earliest);
        }

        return minHeap.size();
    }
}
