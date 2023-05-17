package team.smd.vdsp.utils;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;

/**
 * Algorithm wrapper for running algorithm 
 * in thread, and control to write result 
 * to the given variable `result`
 */
class AlgorithmRunnable implements Runnable {

	/** Memory to be stored the result */
	protected LinkedList<Step> result;

	/** custom settings for running the algorithm */
	protected Setting setting;

	protected ShortestPath algorithm;

	public AlgorithmRunnable() {
	}

	public AlgorithmRunnable(ShortestPath algorithm, Setting setting, LinkedList<Step> result) {
		this.setting = setting;
		this.result = result;
		this.algorithm = algorithm;
	}

	@Override
	public void run() {
		algorithm.shortest();
		result.addAll(algorithm.getAllSteps());
	}

}
