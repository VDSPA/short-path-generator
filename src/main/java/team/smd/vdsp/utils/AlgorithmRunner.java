package team.smd.vdsp.utils;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

public class AlgorithmRunner {

	/**
	 * store the steps
	 * DFS settled in 0
	 * Dijstra settled in 1
	 */
	private LinkedList<Step>[] result;

	// Storing the information of the graph
	private Setting setting;

	/**
	 * Empty constructor
	 */
	public AlgorithmRunner() {
	}

	/**
	 * constructor
	 *
	 * @param setting
	 */
	public AlgorithmRunner(Setting setting) {
		this.setting = setting;
	}

	/**
	 * @return result
	 */
	public LinkedList<Step>[] getResult() {
		return result;
	}

	/**
	 * to run the algorithm simultaneously
	 *
	 * @return result
	 */
	public LinkedList<Step>[] runAlgorithms() {

		//Create thread pool and 2 threads
		ExecutorService executorService = Executors.newFixedThreadPool(2);

		result = new LinkedList[2];
		result[0] = new LinkedList<Step>();
		result[1] = new LinkedList<Step>();

		//If instance setting is not empty, submit thread task
		if (setting != null) {
			executorService.submit(new DFSRunnable(setting, result[0]));
			executorService.submit(new DijstraRunnable(setting, result[1]));
		}

		executorService.shutdown();

		// Wait for all tasks to complete
		while (!executorService.isTerminated()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return getResult();
	}

}
