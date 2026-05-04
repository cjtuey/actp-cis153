import java.util.*;

public class ACTP {
	private int iterations;
	
	public ACTP(int iterations) {
		setIterations(iterations);
	}
	
	private static long timeTask(Task task) {
		long startTime = System.nanoTime();
		task.run();
		return System.nanoTime() - startTime;
	}
	
	public TaskPriorityQueue testTasks(LinkedList<Task> tasks) {
		TaskPriorityQueue tpq = new TaskPriorityQueue();
		for (Task task : tasks) {
			long avgTime = timeTask(task);
			for (int i = 0; i < iterations; ++i) { // time given task once for each defined iteration, then average
				avgTime += timeTask(task);
				avgTime /= 2;
			}
			tpq.add(new Task(task.getLabel(), task.getTC(), task.getMethod(), avgTime)); // recreates each task, since avgTime shouldn't be altered outside of the constructor
		}
		return tpq;
	}
	
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	
	public int getIterations() {
		return iterations;
	}
}
