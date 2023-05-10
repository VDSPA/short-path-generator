package team.smd.vdsp.utils;

import java.util.LinkedList;

import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;

public class Dijstra {

	/**
	 * number of vertexes
	 */
	private int vSize = 0;

	private int start = 0;// 出发点
	private int adjMatrix[][];
	// 最短路径长度
	private int[] shortest;
	// 判断该点的最短路径是否求出
	private boolean[] visited;
	// 用来存储最短路径上的前驱
	private int[] pre;

	public Dijstra() {
	}

	public Dijstra(int[][] Matrix, int start) {
		this.start = start;
		this.vSize = Matrix.length;
		this.adjMatrix = new int[vSize][Matrix[0].length];
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[i].length; j++) {
				this.adjMatrix[i][j] = Matrix[i][j];
			}
		}
		shortest = new int[vSize];
		visited = new boolean[vSize];
		pre = new int[vSize];

		for (int i = 0; i < Matrix.length; i++) {
			visited[i] = false;
			shortest[i] = Integer.MAX_VALUE;
			pre[i] = -1;
		}

	}

	// 对矩阵进行初始化
	public void initialize() {
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if ((i != j) && (adjMatrix[i][j] == 0)) {
					adjMatrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}

	// 寻找最短路径
	public LinkedList<Step> shortest() {
		LinkedList<Step> queue = new LinkedList<>();
		Step s;
		initialize();
		// 初始化源节点
		shortest[start] = 0;
		visited[start] = true;
		Target[] t = new Target[1];
		t[0] = new Target("node", "" + start);
		s = new Step("settle", t);
		queue.offer(s);
		// 遍历起点可以到达的所有点
		for (int i = 0; i < vSize; i++) {
			shortest[i] = adjMatrix[start][i];

			if (adjMatrix[start][i] != Integer.MAX_VALUE) {
				// 如果这个点是起点可以直接达到的点，则setter
				// traverse edge start:i
				t[0] = new Target("edge", start + ":" + i);
				s = new Step("traverse", t);
				queue.offer(s);
				// traverse node i
				t[0] = new Target("node", "" + i);
				s = new Step("traverse", t);
				queue.offer(s);

				pre[i] = start;

			}
		}

		pre[start] = -1;
		int min = Integer.MAX_VALUE;
		int min_index = start;

		for (int x = 0; x < vSize - 1; x++) {
			for (int i = 0; i < vSize; i++) {
				// 遍历循环所有的点，用来找到还没确定最短路径，且dist最小的顶点v1
				// 用min来表示最短距离，用min_index来表示v1的索引

				if (visited[i] == true) {
					continue;
				} else {
					if (shortest[i] < min) {
						min = shortest[i];
						min_index = i;
					}
				}
			}

			visited[min_index] = true;
			// settle index min_index
			t[0] = new Target("node", "" + min_index);
			s = new Step("settle", t);
			queue.offer(s);
			// 检查所有邻接自v1的边，对于v1的邻接顶点
			for (int j = 0; j < vSize; j++) {

				if (visited[j] == false && (adjMatrix[min_index][j] != Integer.MAX_VALUE)) {
					// traverse edge min_index；j
					t[0] = new Target("edge", min_index + ":" + j);
					s = new Step("traverse", t);
					queue.offer(s);
					// traverse index j
					t[0] = new Target("node", "" + j);
					s = new Step("traverse", t);
					queue.offer(s);
					if ((min + adjMatrix[min_index][j]) < shortest[j]) {
						shortest[j] = min + adjMatrix[min_index][j];
						pre[j] = min_index;

					}

				}
			}
			min = Integer.MAX_VALUE;

		}
		// 去掉队列中重复的元素
		for (int i = 0; i < queue.size(); i++) {
			if (i == queue.size() - 1)
				break;
			else {
				if (queue.get(i).equals(queue.get(i + 1))) {
					queue.remove(i);
				}
			}
		}
		return queue;
	}

	/**
	 * 迭代获取最短路径
	 *
	 * @param index end node index
	 * @return 最短路径字符串
	 */
	public String getFullPath(int index) {
		String path = String.valueOf(index);

		while (true) {
			index = findPre(index);
			if (index == -1)
				break;
			path = (String.valueOf(index) + " -> ") + path;
		}
		return path;
	}

	// 根据pre表，找到某个点在最短路径上对应的前驱
	int findPre(int index) {
		return pre[index];
	}

	// 展示工具表的信息，仅用于内部测试
	public void showInfo() {
		System.out.println("Shortest path:");

		for (int i = 0; i < vSize; i++) {
			System.out.print(shortest[i] + " ");
		}
		System.out.println(" ");
		System.out.println("Visited:");
		for (int i = 0; i < vSize; i++) {
			System.out.print(visited[i] + " ");
		}
		System.out.println(" ");
		System.out.println("Pre:");
		for (int i = 0; i < vSize; i++) {
			System.out.print(pre[i] + " ");
		}
		System.out.println(" ");

	}

	public String toMatrix() {
		String matrix = "";
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				if (adjMatrix[i][j] == Integer.MAX_VALUE) {
					matrix += String.format("%5s", "MAX");
				} else {
					matrix += String.format("%5s ", adjMatrix[i][j]);
				}
			}
			matrix += "\n";
		}
		return matrix;
	}
}
