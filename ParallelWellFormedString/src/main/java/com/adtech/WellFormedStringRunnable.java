package com.adtech;

import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;

import com.adtech.util.WellFormedString;

/**
 * The {@code WellFormedStringRunnable} class implements the
 * {@link java.lang.Runnable Runnable} interface. This class parallelizes the
 * {@link com.adtech.util.WellFormedString WellFormedString} class by utilizing
 * a {@link java.util.concurrent.BlockingQueue BlockingQueue} object, allowing a
 * thread to get the line number and a string to analyze.
 * <p>
 * This threaded model will continually poll the
 * {@link java.util.concurrent.BlockingQueue BlockingQueue} for work. The thread
 * will exit when either a sentinel value is read from the queue or the
 * interrupted flag is set.
 * 
 * @author Zachary Radtka
 */
public class WellFormedStringRunnable implements Runnable {

	/** A sentinel value used to kill each thread */
	public static final int SENTINEL = -1;

	/** A blocking queue used to retrieve line numbers and strings */
	private final BlockingQueue<Entry<Integer, String>> sharedQueue;

	/** A WellFormedString object used to determine if a string is well formed */
	private WellFormedString wfs;

	/**
	 * Initializes a a newly created {@code WellFormedStringRunnable}.
	 * 
	 * @param sharedQueue
	 *            A {@code BlockingQueue<Entry<Integer, String>>} that allows
	 *            threads to receive line numbers and strings to process
	 */
	public WellFormedStringRunnable(
			BlockingQueue<Entry<Integer, String>> sharedQueue) {
		this.sharedQueue = sharedQueue;
		wfs = new WellFormedString();
	}

	@Override
	public void run() {

		// Used to hold a tuple from the shared queue
		Entry<Integer, String> currTuple = null;

		// Get a tuple from the shared queue
		try {
			currTuple = this.sharedQueue.take();
		} catch (InterruptedException e1) {
			Thread.currentThread().interrupt();
		}

		// Continue to process strings while the thread is not interrupted and
		// the sentinel value is received
		while (!Thread.currentThread().isInterrupted()
				&& currTuple.getKey() != SENTINEL) {

			// Display the strings corresponding line number and if it was a
			// well formed string
			if (wfs.isValid(currTuple.getValue())) {
				System.out.println(currTuple.getKey() + ":True");
			} else {
				System.out.println(currTuple.getKey() + ":False");
			}

			// Get the following tuple from the shared queue
			try {
				currTuple = this.sharedQueue.take();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

	}

}
