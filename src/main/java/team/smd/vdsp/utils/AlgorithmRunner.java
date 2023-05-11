package team.smd.vdsp.utils;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

public class AlgorithmRunner {

	// store the steps
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

		//If instance setting is not empty, submit thread task
		if (setting != null) {
			executorService.submit(new DFSRunnable(setting, result));
			executorService.submit(new DijstraRunnable(setting, result));
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
