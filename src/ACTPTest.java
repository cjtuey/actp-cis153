import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ACTPTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testACTP() {
		ACTP actp = new ACTP(10);
		assertNotNull(actp);
		assertEquals(10, actp.getIterations());
	}

	@Test
	void testTestTasks() {
		ACTP actp = new ACTP(10);
		LinkedList<Task> tasks = new LinkedList<Task>();
		TaskPriorityQueue tpq;
		tasks.add(new Task("Slow O(1)", Task.TimeComplexity.CONSTANT, () -> { for (int i = 0; i < 10000; ++i); }));
		tasks.add(new Task("Fast O(1)", Task.TimeComplexity.CONSTANT, () -> {}));
		tasks.add(new Task("Fast O(log N)", Task.TimeComplexity.LOGARITHMIC, () -> {}));
		tasks.add(new Task("Fast O(N)", Task.TimeComplexity.LINEAR, () -> {}));
		tasks.add(new Task("Fast O(N^2)", Task.TimeComplexity.EXPONENTIAL, () -> {}));
		tpq = actp.testTasks(tasks);
		assertEquals("Fast O(N^2)", tpq.remove().getLabel());
		assertEquals("Fast O(N)", tpq.remove().getLabel());
		assertEquals("Fast O(log N)", tpq.remove().getLabel());
		assertEquals("Slow O(1)", tpq.remove().getLabel());
		assertEquals("Fast O(1)", tpq.remove().getLabel());
	}

	@Test
	void testSetIterations() {
		ACTP actp = new ACTP(10);
		actp.setIterations(20);
		assertEquals(20, actp.getIterations());
	}
}
