package team.smd.vdsp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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
	@Ignore
	public void showResult() {

		LinkedList<Step>[] result = algorithmRunner.runAlgorithms();

		int vNumber = matrix.length;
		DFS dfs = new DFS(matrix, start);
		LinkedList<Step> dfsResult = new LinkedList<>();

		for (int i = 0; i < vNumber; i++) {
			if (i != start) {
				LinkedList<Step> path = dfs.shortest(start, i);
				dfsResult.addAll(path);
			}
		}

		assertEquals(result[0].size(), dfsResult.size());

		for (LinkedList<Step> steps : result) {
			if (steps != null) {
				System.out.println("====**====");
				for (int i = 0; i < steps.size(); i++) {
					// FIXME: handle Step equals override issue
					assertEquals(steps.get(i), dfsResult.get(i));
				}
			}
		}


	}
}
