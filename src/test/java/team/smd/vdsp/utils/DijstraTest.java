package team.smd.vdsp.utils;

import java.util.LinkedList;
import org.junit.Test;
import team.smd.vdsp.models.Step;

public class DijstraTest {
	public final int[][] matrix = {
			{ 0, 1, 0, 3, 0 },
			{ 0, 0, 2, 4, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0 }
	};
	public final int start = 0;
	public final Dijstra d = new Dijstra(matrix, start);

	@Test
	public void showMatrix() {
		System.out.println(d.toMatrix());
	}

	@Test
	public void showAllPaths() {
		d.shortest();
		for (int i = 0; i < 5; i++) {
			System.out.println(d.getFullPath(i));
		}
	}

	@Test
	public void showSteps() {
		LinkedList<Step> queue = d.shortest();
		// 输出队列中的值
		while (queue.isEmpty() != true) {
			Step head = queue.poll();
			System.out.println(head);
		}
	}

}
