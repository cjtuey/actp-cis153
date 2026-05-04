public class Task {
	static enum TimeComplexity {
		EXPONENTIAL,
		LINEAR,
		LOGARITHMIC,
		CONSTANT
	}
	
	private String label;
	private TimeComplexity tc;
	private Runnable method;
	private long avgTime;
	
	public Task(String label, TimeComplexity tc, Runnable method, long avgTime) {
		setLabel(label);
		setTC(tc);
		setMethod(method);
		this.avgTime = avgTime;
	}
	
	public Task(String label, TimeComplexity tc, Runnable method) {
		setLabel(label);
		setTC(tc);
		setMethod(method);
		this.avgTime = 0;
	}
	
	public Task() {
		setLabel("Unnamed task");
		setTC(TimeComplexity.CONSTANT);
		setMethod(() -> {});
		this.avgTime = 0;
	}
	
	public void run() {
		method.run();
	}
	
	@Override
	public String toString() {
		return getLabel() + ": " + getTC() + " " + getAvgTime() + "ns";
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setTC(TimeComplexity tc) {
		this.tc = tc;
	}
	
	public void setMethod(Runnable method) {
		this.method = method;
	}
	
	public String getLabel() {
		return label;
	}
	
	public TimeComplexity getTC() {
		return tc;
	}
	
	public Runnable getMethod() {
		return method;
	}
	
	public long getAvgTime() {
		return avgTime;
	}
}
