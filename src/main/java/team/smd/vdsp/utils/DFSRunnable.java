package team.smd.vdsp.utils;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;

class DFSRunnable extends AlgorithmRunnable {

	private DFS dfs;

	/**
	 * constructor
	 *
	 * @param setting
	 * @param result
	 */
	public DFSRunnable(Setting setting, LinkedList<Step> result) {
		super(setting, result);
		init();
	}

	/**
	 * Initialize Algorithm Class
	 */
	private void init() {
		this.dfs = new DFS(setting.getMatrix(), setting.getStart());
	}

	@Override
	public void run() {
		dfs.shortest();
		result.addAll(dfs.getAllSteps());
	}
}

