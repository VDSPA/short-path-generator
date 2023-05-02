package src.test.java.team.vdsp.vdsp.utils;

import src.main.java.team.vdsp.vdsp.utils.directedWSG;

public class WSGTest {
	public static void main(String[] args) {
		test1(9);
		test2(9);
		test3(9);
		test4(9);
	}

	private static void test1(int n) {
		int[][] testM1 = directedWSG.getMatrix(n);

		System.out.println("====graph1：测试矩阵形状====");
		int rowLength = testM1.length;
		int colLength = testM1[0].length;
		System.out.println("rowLength:" + rowLength);
		System.out.println("colLength:" + colLength);
	}

	private static void test2(int n) {
		int[][] testM2 = directedWSG.getMatrix(n);

		System.out.println("====graph2：测试矩阵输出====");
		for (int[] ints : testM2) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}
	}

	private static void test3(int n) {

		System.out.println("====graph3：测试矩阵无环====");
		for (int m = 4; m <= n; m++) {
			int[][] testM3 = directedWSG.getMatrix(m);
			for (int i = 0; i < m; i++) {
				if (testM3[i][i] > 0) {
					System.out.println("error n=" + m + " ith:" + i);
				}
			}
		}
		System.out.println("结果如上");
	}

	private static void test4(int n) {

		System.out.println("====graph4：测试矩阵两点之间仅有一边====");
		for (int m = 4; m <= n; m++) {
			int[][] testM4 = directedWSG.getMatrix(m);
			for (int i = 0; i < m / 2; i++) {
				for (int j = 0; j < m; j++) {
					if (testM4[i][j] > 0 && testM4[j][i] > 0) {
						System.out.println("error n=" + m + " ith:" + i + " jth:" + j);
					}
				}
			}
		}
		System.out.println("结果如上");
	}
}
