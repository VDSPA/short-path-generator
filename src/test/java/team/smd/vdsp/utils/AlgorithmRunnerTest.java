package team.smd.vdsp.utils;

import org.junit.Test;
import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;

public class AlgorithmRunnerTest {
	public final int[][] matrix = {
		{0, 1, 0, 3, 0},
		{0, 0, 2, 4, 0},
		{0, 0, 0, 0, 1},
		{0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0}
	};
	public final int start = 0;
	public final AlgorithmRunner algorithmRunner = new AlgorithmRunner(new Setting(matrix, start));

	@Test
	public void showResult() {

		LinkedList<Step>[] result = algorithmRunner.runAlgorithms();

		for (LinkedList<Step> steps : result) {
			if (steps != null) {
				System.out.println("====**====");
				for (Step step : steps) {
					System.out.println(step);
				}
			}
		}
	}
}
