import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskPriorityQueueTest {

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
	void testAdd() {
		TaskPriorityQueue tpq = new TaskPriorityQueue();
		assertTrue(tpq.isEmpty());
		tpq.add(new Task());
		assertFalse(tpq.isEmpty());
	}

	@Test
	void testRemove() {
		TaskPriorityQueue tpq = new TaskPriorityQueue();
		tpq.add(new Task());
		Task poppedTask = tpq.remove();
		assertNotNull(poppedTask);
		assertTrue(tpq.isEmpty());
	}

	@Test
	void testPeek() {
		TaskPriorityQueue tpq = new TaskPriorityQueue();
		tpq.add(new Task());
		Task poppedTask = tpq.peek();
		assertNotNull(poppedTask);
		assertFalse(tpq.isEmpty());
	}
}
