package team.smd.vdsp.utils;

import java.util.LinkedList;
import org.junit.Test;
import team.smd.vdsp.models.Step;

public class DFSTest {
	public final int[][] matrix = {
			{ 0, 1, 0, 3, 0 },
			{ 0, 0, 2, 4, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0 }
	};
	public final int start = 0;
	public final DFS d = new DFS(matrix, start);

	@Test
	public void showMatrix() {
		System.out.println(d.toMatrix());
	}

	@Test
	public void showAllPaths() {
		System.out.printf("Show the shortest path between start node(%d) and other node:\n", start);
		for (int i = 0; i < d.getVSize(); i++) {
			if (i != start) {
				d.shortest(start, i);
				System.out.println(d.getFullPath(start, i));
			}
		}
	}

	@Test
	public void showSteps() {
		LinkedList<Step> queue = d.shortest(start, 3);
		queue.addAll(d.shortest(start, 2));
		// 输出队列中的值
		while (queue.isEmpty() != true) {
			Step head = queue.poll();
			System.out.println(head);
		}
	}

}
