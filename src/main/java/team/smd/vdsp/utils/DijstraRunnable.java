package team.smd.vdsp.utils;

import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;

public class DijstraRunnable extends AlgorithmRunnable {

	private Dijstra dijstra;

	/**
	 * constructor
	 *
	 * @param setting
	 * @param result
	 */
	public DijstraRunnable(Setting setting, LinkedList<Step>[] result) {
		super(setting, result);
		init();
	}

	/**
	 * Initialize Algorithm Class
	 */
	private void init() {
		this.dijstra = new Dijstra(setting.getMatrix(), setting.getStart());
	}

	@Override
	public void run() {
		result[1] = dijstra.shortest();
	}
}
