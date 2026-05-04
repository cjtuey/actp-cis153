import java.util.*;

class TaskComparator implements Comparator<Task> { // comparator for the priority queue, sorts by time complexity first, absolute runtime second
	@Override
	public int compare(Task o1, Task o2) {
		return (o1.getTC() == o2.getTC()) ?
			((Long) o2.getAvgTime()).compareTo((Long) o1.getAvgTime())
			: o1.getTC().compareTo(o2.getTC());
	}
}

public class TaskPriorityQueue {
	private PriorityQueue<Task> pq;
	
	public TaskPriorityQueue() {
		pq = new PriorityQueue<Task>(new TaskComparator());
	}
	
	public boolean add(Task task) {
		return pq.add(task);
	}
	
	public Task remove() {
		return pq.remove();
	}
	
	public Task peek() {
		return pq.peek();
	}
	
	public boolean isEmpty() {
		return pq.isEmpty();
	}
}
