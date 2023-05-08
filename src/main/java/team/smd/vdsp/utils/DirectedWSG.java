package team.smd.vdsp.utils;

import java.util.Random;

public class DirectedWSG {

	private static int[][] adjM_int;

	private static int weight = 99;

	private static boolean[][] generate(int n, int k, double p) {
		boolean[][] adjMatrix = new boolean[n][n];

		// 创建一个n个节点的环形图
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k / 2; j++) {
				int idx = (i + j) % n;
				adjMatrix[i][idx] = true;
				adjMatrix[idx][i] = true;
			}
		}

		// 将每个出边替换为一个指向其他节点的出边或入边
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adjMatrix[i][j] && random.nextDouble() < p) {
					if (random.nextDouble() < 0.5) {
						// 替换为一个指向其他节点的出边
						adjMatrix[i][j] = false;
						int idx = random.nextInt(n);
						while (idx == i || adjMatrix[j][idx]) {
							idx = random.nextInt(n);
						}
						adjMatrix[j][idx] = true;
					} else {
						// 替换为一个指向其他节点的入边
						adjMatrix[i][j] = false;
						int idx = random.nextInt(n);
						while (idx == j || adjMatrix[idx][i]) {
							idx = random.nextInt(n);
						}
						adjMatrix[idx][i] = true;
					}
				}
			}
		}

		return adjMatrix;
	}

	private static void generateWeightedAdjMatrix(boolean[][] adjMatrix, int maxWeight) {
		int n = adjMatrix.length;
		adjM_int = new int[n][n];
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adjMatrix[i][j]) {
					adjM_int[i][j] = random.nextInt(maxWeight) + 1; // 随机生成1-maxWeight之间的整数作为权重
				}
			}
		}
	}

	public static int[][] getAdjM_int() {
		return adjM_int;
	}

	public static void setWeight(int weight) {
		DirectedWSG.weight = weight;
	}

	//返回一个带权邻接矩阵
	public static int[][] getMatrix() {
		boolean[][] adjMatrix = generate(12, 2, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		//printAdjMatrix();
		return getAdjM_int();
	}

	//返回一个带权邻接矩阵
	public static int[][] getMatrix(int n) {
		boolean[][] adjMatrix = generate(n, 2, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		//printAdjMatrix();
		return getAdjM_int();
	}

	//返回一个带权邻接矩阵
	public static int[][] getMatrix(int n, int k) {
		boolean[][] adjMatrix = generate(n, k, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		//printAdjMatrix();
		return getAdjM_int();
	}

	public static int[][] getMatrix(int n, int k, int p) {
		boolean[][] adjMatrix = generate(n, k, p);
		generateWeightedAdjMatrix(adjMatrix, weight);
		//printAdjMatrix();
		return getAdjM_int();
	}

	private static void printAdjMatrix() {
		int n = adjM_int.length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%2d ", adjM_int[i][j]);
			}
			System.out.println();
		}
	}
}
