package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Result;

public class AlgorithmRunner {

	/** Traversing Steps of all algorithm */
	private ArrayList<Result> results;

	// Storing the information of the graph
	private Setting setting;

	protected ArrayList<ShortestPath> algorithms;

	public AlgorithmRunner() {
	}

	public AlgorithmRunner(Setting setting) {
		this.setting = setting;
		this.algorithms = new ArrayList<>(Arrays.asList(
			new DFS(setting.getMatrix(), setting.getStart()),
			new Dijstra(setting.getMatrix(), setting.getStart())
		));
	}

	public ArrayList<Result> getResults() {
		return results;
	}

	public Setting getSetting() {
		return setting;
	}

	/**
	 * Run the algorithm simultaneously
	 *
	 * @return result
	 */
	public ArrayList<Result> runAlgorithms() {

		// Create thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(this.algorithms.size());

		// Initial results
		results = new ArrayList<>();

		if (setting != null) {
			for (int i = 0; i < this.algorithms.size(); i++) {
				results.add(new Result(algorithms.get(i).getName(), new LinkedList<>()));
				executorService.submit(new AlgorithmRunnable(algorithms.get(i), setting, results.get(i)));
			}
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

		return results;
	}

}
