package team.smd.vdsp.utils;

import org.junit.Test;
import java.util.Arrays;

public class BFSTest {
	public final int[][] matrix = {
			{ 0, 1, 0, 3, 0 },
			{ 0, 0, 2, 4, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0 }
	};
	public final int start = 0;
	public final BFS b = new BFS(matrix, start);

	@Test
	public void showMatrix() {
		System.out.println(b.toMatrix());
	}

	@Test
	public void showPath() {
		b.shortest();
		System.out.println(b.allPath);
	}

	@Test
	public void showQueue() {
		b.shortest();
		System.out.println(b.stepQueue);
	}

	@Test
	public void showAllDis() {
		b.shortest();
		System.out.println(Arrays.toString(b.getAllDis()));
	}

	@Test
	public void showClass() {
		b.shortest();
		System.out.println(b.toString());
	}
}
