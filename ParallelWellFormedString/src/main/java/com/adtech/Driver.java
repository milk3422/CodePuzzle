package com.adtech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * The parallel solution utilizes ExecutorService to create a thread pool for
 * the WellFormedString object to process strings read from STDIN.
 * <p>
 * First, {@link ExecutorService} analyzes the runtime to create a thread pool
 * of a size equal to that of the number of cores on the machine currently
 * running the ParallelWellFormedString application. Once a thread pool has been
 * created, a {@link com.adtech.WellFormedStringRunnable} task is assigned to
 * each thread in the thread pool.
 * <p>
 * After the threads are initialized, lines are read from STDIN and a
 * {@link BlockingQueue} is used to dispatch strings that are read from STDIN to 
 * the threads in the thread pool. Each thread reads from the 
 * {@link BlockingQueue} and processes the string using the 
 * {@link com.adtech.util.WellFormedString WellFormedString} object. Once a 
 * string has been evaluated, the thread prints its result to STDOUT and 
 * retrieves another string from the {@link BlockingQueue}.
 * <p>
 * After all of the strings are read from STDIN and placed on the 
 * {@link BlockingQueue}, a sentinel value is placed on the 
 * {@link BlockingQueue}. When the threads in the thread pool read the sentinel
 * value they will exit and complete execution.
 * <p> 
 * <b>To Build</b>
 * <p>
 * ParallelWellFormedString can be built using 'mvn clean install' from within
 * the AdtechCodePuzzle project. The ParallelWellFormedString jar file will be 
 * in AdtechCodePuzzle/target.
 * <p>
 * <b>Usage</b>
 * <p>
 * {@literal java -jar ParallelWellFormedString < file}
 * 
 * @author Zachary Radtka
 */
public class Driver {

	public static void main(String[] args) {

		// A blocking queue used to pass line numbers and strings to threads
		BlockingQueue<Entry<Integer, String>> sharedQueue = new LinkedBlockingQueue<Entry<Integer, String>>();

		// The line number currently being evaluated
		int currLine = 0;

		// The number of cores available on the running machine
		// Note: if Hyper Threading is enabled this will be double the number of
		// physical cores
		int numCores = Runtime.getRuntime().availableProcessors();

		// Create a thread pool of size equal to numCores
		ExecutorService threadPool = Executors.newFixedThreadPool(numCores);

		// Initialize all of the Tasks and add them to the thread pool
		for (int i = 0; i < numCores; i++) {
			Runnable task = new WellFormedStringRunnable(sharedQueue);
			threadPool.execute(task);
		}

		// Do not allow any more tasks to be added and wait for all tasks to be
		// completed before shutting down the executor
		threadPool.shutdown();

		// Read form STDIN and add each line to the shared queue
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in))) {

			// The current line
			String input;

			// Continue processing until a null character has been reached
			while ((input = br.readLine()) != null) {

				// Add the tuple (line number, string) to the shared queue
				try {
					sharedQueue
							.put(new AbstractMap.SimpleEntry<Integer, String>(
									currLine, input));
				} catch (InterruptedException e) {
					System.err.println("Error accessing shared queue: "
							+ e.getMessage());
					threadPool.shutdownNow();
					System.exit(1);
				}

				currLine++;
			}

		} catch (IOException e) {
			System.err.println("Error reading from STDIN: " + e.getMessage());
			System.exit(1);
		}

		// Terminate the tasks by sending the sentinel value to each task
		for (int i = 0; i < numCores; i++) {
			try {
				sharedQueue.put(new AbstractMap.SimpleEntry<Integer, String>(
						WellFormedStringRunnable.SENTINEL, null));
			} catch (InterruptedException e) {

				// If the shared queue throws an exception, display the error
				// and shutdown all running tasks
				System.err.println("Error accessing shared queue: "
						+ e.getMessage());
				threadPool.shutdownNow();
				System.exit(1);
			}
		}

		// Allow all threads to complete
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {

			// If the shared queue throws an exception, display the error and
			// shutdown all running tasks
			System.err.println("Error while waiting for threads to terminate: "
					+ e.getMessage());
			threadPool.shutdownNow();
			System.exit(1);
		}

	}

}
