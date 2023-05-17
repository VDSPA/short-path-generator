package team.smd.vdsp.utils;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import team.smd.vdsp.models.Setting;
import team.smd.vdsp.models.Step;

import java.util.LinkedList;
import java.util.ArrayList;

public class AlgorithmRunnerTest {
	public static int[][] matrix = {
		{0, 1, 0, 3, 0},
		{0, 0, 2, 4, 0},
		{0, 0, 0, 0, 1},
		{0, 0, 0, 0, 1},
		{0, 0, 0, 0, 0}
	};
	public static int start = 0;
	public static AlgorithmRunner algorithmRunner = new AlgorithmRunner(new Setting(matrix, start));

	/** Multi-thread results */
	public static ArrayList<LinkedList<Step>> results;

	@BeforeClass
	public static void init() {
		results = algorithmRunner.runAlgorithms();
	}
	
	@Test
	public void checkSetting() {
		Setting setting = algorithmRunner.getSetting();
		assertEquals(setting, algorithmRunner.getSetting());
	}

	@Test
	public void checkConcurrentResults() {
		for (int i = 0; i < results.size(); i++) {
			ShortestPath algorithm = algorithmRunner.algorithms.get(i);

			// Single algorithm result
			algorithm.shortest();
			LinkedList<Step> result = algorithm.getAllSteps();

			assertEquals(result, results.get(i));
		}
	}
}
