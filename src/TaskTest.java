import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

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
	void testTaskStringTimeComplexityRunnableLong() {
		Task task = new Task("Sample task", Task.TimeComplexity.LINEAR, () -> {}, 100);
		assertEquals("Sample task", task.getLabel());
		assertEquals(Task.TimeComplexity.LINEAR, task.getTC());
		assertNotNull(task.getMethod());
		assertEquals(100, task.getAvgTime());
	}

	@Test
	void testTaskStringTimeComplexityRunnable() {
		Task task = new Task("Sample task", Task.TimeComplexity.LINEAR, () -> {});
		assertEquals("Sample task", task.getLabel());
		assertEquals(Task.TimeComplexity.LINEAR, task.getTC());
		assertNotNull(task.getMethod());
		assertEquals(0, task.getAvgTime());
	}

	@Test
	void testTask() {
		Task task = new Task();
		assertEquals("Unnamed task", task.getLabel());
		assertEquals(Task.TimeComplexity.CONSTANT, task.getTC());
		assertNotNull(task.getMethod());
		assertEquals(0, task.getAvgTime());
	}

	@Test
	void testToString() {
		Task task = new Task();
		assertEquals("Unnamed task: CONSTANT 0ns", task.toString());
	}

	@Test
	void testSetLabel() {
		Task task = new Task();
		task.setLabel("Sample task");
		assertEquals("Sample task", task.getLabel());
	}

	@Test
	void testSetTC() {
		Task task = new Task();
		task.setTC(Task.TimeComplexity.LINEAR);
		assertEquals(Task.TimeComplexity.LINEAR, task.getTC());
	}
}
