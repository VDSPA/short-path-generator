package team.smd.vdsp.utils;

import java.util.Random;

public class DirectedWSG {

	private static int[][] adjM_int;

	private static int weight = 99;

	/**
	 * Create a graph matrix by Watts-Strogatz
	 *
	 * @param n
	 * @param k
	 * @param p
	 * @return boolean[][] adjMatrix
	 */
	private static boolean[][] generate(int n, int k, double p) {
		boolean[][] adjMatrix = new boolean[n][n];

		// Create a graph with n vertex
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k / 2; j++) {
				int idx = (i + j) % n;
				adjMatrix[i][idx] = true;
				adjMatrix[idx][i] = true;
			}
		}

		// Replace each outgoing edge with an outgoing or incoming edge pointing to another vertex
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adjMatrix[i][j] && random.nextDouble() < p) {
					if (random.nextDouble() < 0.5) {
						// replaced by an outgoing edge pointing to another vertex
						adjMatrix[i][j] = false;
						int idx = random.nextInt(n);
						while (idx == i || adjMatrix[j][idx]) {
							idx = random.nextInt(n);
						}
						adjMatrix[j][idx] = true;
					} else {
						// replaced by an incoming edge pointing to another vertex
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

	/**
	 * generate a weighted no ring graph
	 * maxWeight is the largest weight in this graph
	 *
	 * @param adjMatrix
	 * @param maxWeight
	 */
	private static void generateWeightedAdjMatrix(boolean[][] adjMatrix, int maxWeight) {
		int n = adjMatrix.length;
		adjM_int = new int[n][n];
		Random random = new Random();

		// Ensure that there is only one edge between any two vertices
		// And no ring
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adjMatrix[i][j] && adjMatrix[j][i]) {
					adjMatrix[i][j] = false;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adjMatrix[i][j]) {
					// Randomly generate an integer between 1 - maxWeight as the weight
					adjM_int[i][j] = random.nextInt(maxWeight) + 1;
				}
			}
		}
	}

	/**
	 * return the adjM_int
	 *
	 * @return int[][] adjM_int
	 */
	public static int[][] getAdjM_int() {
		return adjM_int;
	}

	/**
	 * set the weight
	 *
	 * @param weight default be 99
	 */
	public static void setWeight(int weight) {
		DirectedWSG.weight = weight;
	}

	/**
	 * returns a weighted adjacency matrix
	 * n = 12
	 * k = 2
	 * p = 0.5
	 *
	 * @return getAdjM_int()
	 */
	public static int[][] getMatrix() {
		boolean[][] adjMatrix = generate(12, 2, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		return getAdjM_int();
	}

	/**
	 * returns a weighted adjacency matrix
	 * n = n
	 * k = 2
	 * p = 0.5
	 *
	 * @param n
	 * @return getAdjM_int()
	 */
	public static int[][] getMatrix(int n) {
		boolean[][] adjMatrix = generate(n, 2, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		return getAdjM_int();
	}

	/**
	 * returns a weighted adjacency matrix
	 * n = n
	 * k = k
	 * p = 0.5
	 *
	 * @param n
	 * @param k
	 * @return getAdjM_int()
	 */
	public static int[][] getMatrix(int n, int k) {
		boolean[][] adjMatrix = generate(n, k, 0.5);
		generateWeightedAdjMatrix(adjMatrix, weight);
		return getAdjM_int();
	}

	/**
	 * returns a weighted adjacency matrix
	 * n = n
	 * k = k
	 * p = p
	 *
	 * @param n
	 * @param k
	 * @param p
	 * @return getAdjM_int()
	 */
	public static int[][] getMatrix(int n, int k, int p) {
		boolean[][] adjMatrix = generate(n, k, p);
		generateWeightedAdjMatrix(adjMatrix, weight);
		return getAdjM_int();
	}

	/**
	 * print the AdjMatrix
	 */
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
