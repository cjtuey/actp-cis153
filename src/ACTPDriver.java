/** A program for testing the efficiency of algorithms
 * @author Cian Tuey
 * @version 1.0
 * @since 1.0
*/
/*  
* OS: Devuan GNU/Linux 6
* IDE: eclipse 4.39.0
* Copyright : This is my own original work 
* based on specifications issued by our instructor
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or
* unmodified, nor used generative AI as a final draft. 
* I have not given other fellow student(s) access to my program.
*/

import java.io.IOException;
import java.util.*;

public class ACTPDriver {
	static void selectionSort(int[] array) { // basic selection sort implementation
		for (int i = 0; i < array.length; ++i) {
			int min = array[i];
			int minIndex = i;
			for (int j = i + 1; j < array.length; ++j) {
				if (array[j] < min) {
					min = array[j];
					minIndex = j;
				}
			}
			int temp = array[i];
			array[i] = min;
			array[minIndex] = temp;
		}
	}
	
	static char getChar(String prompt) { // get exactly one character from stdin
		int inputBuffer[] = new int[2];
		String errMsg = "Unable to parse input";
		System.out.print(prompt);
		try {
			inputBuffer[0] = System.in.read(); // may automatically throw an exception
			if (inputBuffer[0] == '\n')
				throw new IOException(); // throw exception if no characters are entered
			inputBuffer[1] = System.in.read(); // may automatically throw an exception
			if (inputBuffer[1] == '\n') {
				return (char) inputBuffer[0]; // one character was entered, followed by a line feed
			} else { // more than one character was entered
				while (System.in.read() != '\n'); // wait until the user is finished with their input
				errMsg = "Please enter a single character"; // change error message
				throw new IOException(); // throw exception
			}
		} catch (IOException e) {
			System.out.println(errMsg); // print error message
			return getChar(prompt); // recursively call the method again to retry character input; only way out is to provide valid input or terminate the program
		}
	}
	
	static int getInt(String prompt) { // get an integer from stdin
		System.out.print(prompt);
		int userInt = -1;
		Stack<Character> inputBuffer = new Stack<>();
		char userIn = ' ';
		try {
			while (userIn != '\n') { // get one character at a time
				userIn = (char) System.in.read(); // may automatically throw an exception
				if (userIn == '\n'); // does nothing, as the loop will break automatically for this condition at the next iteration
				else if (userIn >= '0' && userIn <= '9') // got a digit
					inputBuffer.push(userIn); // add to stack
				else { // got a non-numeric character
					while (System.in.read() != '\n'); // wait until the user is finished with their input
					throw new IOException(); // throw exception
				}
			}
			userInt = inputBuffer.isEmpty() ? 10 : 0; // only reachable if no non-numeric characters were entered; sets return value to default of 10 if no characters were entered at all
			for (int i = 1; !inputBuffer.isEmpty(); i *= 10) { // converts ASCII digits to ints, multiplies to correct power of 10, and sums
				userInt += Character.getNumericValue(inputBuffer.pop()) * i;
			}
			return userInt; // returns user number or 10
		} catch (IOException e) {
			System.out.println("Unable to parse input as integer"); // a non-numeric character was entered, or failed to read stdin
			return getInt(prompt); // recursively try again
		}
	}
	
	public static void main(String[] args) {
		final int ARRAY_SIZE = 10000;
		final int MAX_VALUE = 1000000;
		char userIn = ' ';
		String lastResult = "No previous results";
		ACTP actp = new ACTP(10);
		LinkedList<Task> tasks = new LinkedList<Task>();
		TaskPriorityQueue tpq;
		Random rand = new Random();
		int[] unsortedArray = new int[ARRAY_SIZE];
		int[] sortedArray = new int[ARRAY_SIZE];
		
		for (int i = 0; i < ARRAY_SIZE; ++i) { // create two identical arrays of random numbers
			int nextInt = rand.nextInt(MAX_VALUE);
			unsortedArray[i] = nextInt;
			sortedArray[i] = nextInt;
		}
		
		Arrays.sort(sortedArray); // sort one of the two arrays
		
		tasks.add(new Task("Get last element: O(1)", Task.TimeComplexity.CONSTANT, () -> { @SuppressWarnings("unused") int lastElement = sortedArray[ARRAY_SIZE - 1]; })); // a single basic operation
		tasks.add(new Task("Binary search: O(log N)", Task.TimeComplexity.LOGARITHMIC, () -> { Arrays.binarySearch(sortedArray, sortedArray[ARRAY_SIZE - 1]); })); // binary search on sorted array, using max value for worst-case outcome
		tasks.add(new Task("Linear search: O(N)", Task.TimeComplexity.LINEAR, () -> { for (int i : sortedArray) if (i == sortedArray[ARRAY_SIZE -1]) break;})); // linear search of the same value as above for comparison
		tasks.add(new Task("Selection sort: O(N^2)", Task.TimeComplexity.EXPONENTIAL, () -> { selectionSort(unsortedArray.clone()); })); // only use of unsorted array, may vary between executions
		
		System.out.println("Welcome to the Automatic Computational Task Prioritizer!");
		while (userIn != 'q') {
			userIn = getChar("Choose an option:\n  i. Set test iterations\n  t. Run tests\n  v. View last test results\n  q. Quit\n");
			switch (userIn) {
			case 'i':
				actp.setIterations(getInt("Enter amount of test iterations to run (leave blank for default of 10): "));
				System.out.println(actp.getIterations());
				break;
			case 't': // falls through to 'v', since displaying the results is the next step anyway
				tpq = actp.testTasks(tasks);
				lastResult = "";
				while (!tpq.isEmpty()) {
					lastResult += tpq.remove() + "\n";
				}
			case 'v': // falls through to 'q', since all it does is break
				System.out.println("Test results (most intensive to least intensive):");
				System.out.println(lastResult);
			case 'q' : // does nothing but break, since the loop conditional already checks for 'q'
				break;
			default:
				System.out.println("Please enter one of the provided menu options");
			}
		}
		System.out.println("Program terminated");
	}
}
