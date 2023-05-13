package team.smd.vdsp.utils;

import team.smd.vdsp.utils.Floyd;

import java.util.Arrays;

import org.junit.Test;

public class FloydTest {
	public final int[][] matrix = {
			{ 0, 1, 0, 3, 0 },
			{ 0, 0, 2, 4, 0 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0 }
	};
	public final int start = 0;
	public final Floyd f = new Floyd(matrix, start);

	@Test
	public void showPath() {
		f.shortest();
		System.out.println(f.getAllPaths());
	}

	@Test
	public void showStep() {
		f.shortest();
		System.out.println(f.stepQueue);
	}

	@Test
	public void showClass() {
		f.shortest();
		System.out.println(f.toString());
	}

}
