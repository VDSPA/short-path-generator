package team.smd.vdsp.utils;

import java.util.LinkedList;
import org.junit.Test;
import team.smd.vdsp.models.Step;
import team.smd.vdsp.utils.DFS;

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
	public void showQueue() {
		d.shortest();
		System.out.println(d.stepQueue);

	}

	@Test
	public void showPath() {
		d.shortest();
		System.out.println(d.allPath);
	}

	@Test
	public void showClass() {
		d.shortest();
		System.out.println(d.toString());
	}

}
