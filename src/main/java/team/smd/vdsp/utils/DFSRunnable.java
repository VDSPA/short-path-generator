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
	public DFSRunnable(Setting setting, LinkedList<Step>[] result) {
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
		result[0] = new LinkedList<>();

		int start = setting.getStart();
		int vNumber = dfs.getVSize();

		for (int i = 0; i < vNumber; i++) {
			if (i != start) {
				LinkedList<Step> path = dfs.shortest(start, i);
				result[0].addAll(path);
			}
		}
	}
}

