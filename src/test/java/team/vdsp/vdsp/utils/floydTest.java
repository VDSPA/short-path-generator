package src.test.java.team.vdsp.vdsp.utils;

import src.main.java.team.vdsp.vdsp.utils.directedWSG;

public class floydTest {
    /*
    private void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                }
            }
        }

        // 打印
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                System.out.println(i + " " + j + ":" + a[i][j]);
            }
        }
    }
    */

	//距离矩阵
	public static int[][] distance;

	// 路径矩阵
	public static int[][] path;

	public static void floyd(int[][] graph) {
		//初始化距离矩阵 distance
		distance = graph;

		//初始化路径
        /*
        path = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                path[i][j] = j;
            }
        }
        */

		//开始 Floyd 算法
		//每个点为中转
		for (int i = 0; i < graph.length; i++) {
			//所有入度
			for (int j = 0; j < graph.length; j++) {
				//所有出度
				for (int k = 0; k < graph[j].length; k++) {
					//以每个点为「中转」，刷新所有出度和入度之间的距离
					//例如 AB + BC < AC 就刷新距离
                    /*if (graph[j][i] != -1 && graph[i][k] != -1) {
                        int newDistance = graph[j][i] + graph[i][k];
                        if (newDistance < graph[j][k] || graph[j][k] == -1) {
                            //刷新距离
                            graph[j][k] = newDistance;
                            //刷新路径
                            path[j][k] = i;
                        }
                    }*/
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}

	/**
	 * 测试
	 */
	public static void test1(String[] args) {
		//char[] vertices = new char[]{'A', 'B', 'C', 'D'};
		int[][] graph = directedWSG.getMatrix(5);

		System.out.println("====graph====");
		for (int[] ints : graph) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}

		floyd(graph);

		System.out.println("====distance====");
		for (int[] ints : distance) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}

		System.out.println("====path====");
		for (int[] ints : path) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}
	}

	public static void test2(String[] args) {
		int[][] adjMatrix = directedWSG.getMatrix(6);
		int n = adjMatrix.length;

		System.out.println("====graph====");
		for (int[] ints : adjMatrix) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}

		// Initialize the distance matrix with the weights of the edges
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = adjMatrix[i][j];
			}
		}

		// Apply the Floyd algorithm
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][k] != 0 && dist[k][j] != 0 && (dist[i][j] == 0 || dist[i][k] + dist[k][j] < dist[i][j])) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}

		System.out.println("====dist====");
		// Print the distance matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
	}

	//dist还不行
	public static void main(String[] args) {
		int[][] adjMatrix = directedWSG.getMatrix();
		int n = adjMatrix.length;

		System.out.println("====graph====");
		for (int[] ints : adjMatrix) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}

		// Initialize the distance and path matrices with the weights and intermediate nodes of the edges
		int[][] dist = new int[n][n];
		int[][] path = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dist[i][j] = adjMatrix[i][j];
				path[i][j] = (adjMatrix[i][j] == 0) ? -1 : i;
			}
		}

		System.out.println();
		System.out.println("====paths====");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}

		// Apply the Floyd algorithm
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (dist[i][k] != 0 && dist[k][j] != 0 && (dist[i][j] == 0 || dist[i][k] + dist[k][j] < dist[i][j])) {
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}

		System.out.println("====dist====");
		// Print the distance and path matrices
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println("====paths====");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(path[i][j] + " ");
			}
			System.out.println();
		}


		System.out.println();
		System.out.println("====Print the shortest paths between all pairs of nodes====");
		// Print the shortest paths between all pairs of nodes
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dist[i][j] != 0 && dist[i][j] != Integer.MAX_VALUE) {
					System.out.print(i + " -> " + j + " (dist=" + dist[i][j] + "): ");
					printPath(path, i, j);
					System.out.println();
				}
			}
		}
	}

	// Recursively print the intermediate nodes of the shortest path from i to j
	private static void printPath(int[][] path, int i, int j) {
		if (i == j) {
			System.out.print(i);
		} else if (path[i][j] == -1) {
			System.out.print("no path");
		} else {
			printPath(path, i, path[i][j]);
			System.out.print(" -> " + j);
		}
	}


}

