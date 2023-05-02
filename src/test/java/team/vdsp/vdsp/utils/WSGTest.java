package src.test.java.team.vdsp.vdsp.utils;

import src.main.java.team.vdsp.vdsp.utils.directedWSG;

public class WSGTest {
	public static void main(String[] args) {
		int[][] testM = directedWSG.getMatrix();

		System.out.println("====graph====");
		for (int[] ints : testM) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}

	}
}
