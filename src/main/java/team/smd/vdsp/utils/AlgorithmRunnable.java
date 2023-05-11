package team.smd.vdsp.utils;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;

abstract class AlgorithmRunnable implements Runnable {

	// Storing the step of the dfs algorithm
	protected LinkedList<Step> result;

	// Storing the information of the graph
	protected Setting setting;

	/**
	 * Empty constructor
	 */
	public AlgorithmRunnable() {
	}

	/**
	 * constructor
	 *
	 * @param setting
	 * @param result
	 */
	public AlgorithmRunnable(Setting setting, LinkedList<Step> result) {
		this.setting = setting;
		this.result = result;
	}

	/**
	 * Initialize Algorithm Class
	 */
	private void init() {
	}
}
