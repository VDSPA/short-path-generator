package team.smd.vdsp.utils;

import static org.junit.Assert.assertEquals;

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

		DFS dfs = new DFS(matrix, start);
		LinkedList<Step> dfsResult = new LinkedList<>();
		dfs.shortest();
		dfsResult.addAll(dfs.getAllSteps());

		Dijstra dij = new Dijstra(matrix, start);
		dij.shortest();
		LinkedList<Step> dijResult = new LinkedList<>();
		dijResult.addAll(dij.getAllSteps());

		assertEquals(result[0].size(), dfsResult.size());

		for (int i = 0; i < result[0].size(); i++) {
			assertEquals(result[0].get(i), dfsResult.get(i));
		}

		for (int i = 0; i < result[1].size(); i++) {
			assertEquals(result[1].get(i), dijResult.get(i));
		}

	}
}
