package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.LinkedList;

import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;

import java.util.Arrays;

public class DFS {

	/** number of vertexes */
	private int vSize = 0;

	/** start pot id */
	private int start = 0;

	private int adjMatrix[][];

	/** short-path of current dfs */
	private ArrayList<Integer> shortest = new ArrayList<>();

	/** 用来存储当前最短路径的长度 */
	private int shortestDis;

	public DFS() {
	}

	public DFS(int[][] Matrix, int start) {
		this.start = start;
		this.vSize = Matrix.length;
		this.adjMatrix = new int[vSize][Matrix[0].length];
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[i].length; j++) {
				this.adjMatrix[i][j] = Matrix[i][j];
			}
		}
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if ((i != j) && (adjMatrix[i][j] == 0)) {
					adjMatrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}

	public int getVSize() {
		return vSize;
	}

	/**
	 * 找到两个点之间的所有路径，并保存下最小的那条路径
	 * 
	 * @param start 起点
	 * @param end   终点
	 * @return 遍历信息
	 */
	public LinkedList<Step> shortest(int start, int end) {
		shortestDis = Integer.MAX_VALUE;
		boolean[] visited = new boolean[vSize];// 标记节点是否被访问过
		ArrayList<Integer> pathList = new ArrayList<>();// 用来存储当前的路径
		int[] pathLength = new int[vSize];
		Arrays.fill(pathLength, Integer.MAX_VALUE);
		// 从起点开始搜索最短路径
		pathList.add(start);
		pathLength[start] = 0;
		LinkedList<Step> queue = new LinkedList<Step>();
		queue = dfsShortest(start, end, visited, pathList, pathLength);

		// 此时 shortest 队列中的元素，即为当前起点和终点最短路径上的边
		// 对最短路径的所有边进行settle
		Target[] tt = new Target[shortest.size() - 1];
		for (int i = 0; i < shortest.size() - 1; i++) {
			tt[i] = new Target("edge", shortest.get(i) + ":" + shortest.get(i + 1));
		}
		Step ss = new Step("settle", tt);
		queue.offer(ss);

		// 两点之间最短路径寻找结束后，进行 reset
		Target[] t = new Target[1];
		t[0] = new Target("edge", "");
		Step s = new Step("reset", t);
		queue.offer(s);

		// 去掉队列中重复的元素
		for (int i = 0; i < queue.size(); i++) {
			if (i == queue.size() - 1)
				break;
			else {
				if ((queue.get(i)).equals(queue.get(i + 1))) {
					queue.remove(i);
				}
			}
		}
		return queue;
	}

	/**
	 * 
	 * @param u          起点
	 * @param end        终点
	 * @param visited
	 * @param pathList
	 * @param pathLength
	 * @return
	 */
	public LinkedList<Step> dfsShortest(int u, int end, boolean[] visited, ArrayList<Integer> pathList,
			int[] pathLength) {
		LinkedList<Step> queue = new LinkedList<>();
		Step s;
		visited[u] = true;

		// traverse node u
		Target[] t = new Target[1];
		if (u == start) {
			t[0] = new Target("node", "" + start);
			s = new Step("traverse", t);
			queue.offer(s);
		}

		if (u == end) {
			// 当前节点为终点
			// 将该路径与已有的最短路径的长度作比较，若当前路径更短，则更新最短路径
			if (shortestDis > pathLength[end]) {
				shortestDis = pathLength[end];
				shortest = new ArrayList<>(pathList);
			}
			// 回溯
			visited[u] = false;
			return queue;
		}

		for (int v = 0; v < vSize; v++) {
			if (adjMatrix[u][v] != 0
					&& !visited[v]
					&& adjMatrix[u][v] != Integer.MAX_VALUE) {

				// 存在边且尚未被访问过，继续遍历
				pathList.add(v);
				// traverse edge u:v
				t[0] = new Target("edge", u + ":" + v);
				s = new Step("traverse", t);
				queue.offer(s);

				// traverse node v
				t[0] = new Target("node", "" + v);
				s = new Step("traverse", t);
				queue.offer(s);
				pathLength[v] = pathLength[u] + adjMatrix[u][v];
				queue.addAll(dfsShortest(v, end, visited, pathList, pathLength));

				// 回溯
				pathList.remove(pathList.size() - 1);
				pathLength[v] = Integer.MAX_VALUE;
			}
		}
		// 回溯
		visited[u] = false;

		return queue;
	}

	/**
	 * 从 shortest 获得最短路径信息
	 * 需要在
	 * 
	 * @param start
	 * @param end
	 * @return 最短路径信息
	 */
	public String getFullPath(int start, int end) {
		if (shortest.size() <= 0)
			return "";

		String path = shortest.get(0).toString();
		for (int i = 1; i < shortest.size(); i++) {
			path += (" -> " + shortest.get(i));
		}
		return path;
	}

	// 展示中间信息
	private void showInfo(boolean[] visited, ArrayList<Integer> pathList, int[] pathLength) {
		for (int i = 0; i < visited.length; i++) {
			System.out.print("visited:" + visited[i] + " ");
		}
		System.out.println("");
		for (int i = 0; i < pathList.size(); i++) {
			System.out.print("pathList:" + pathList.get(i) + " ");
		}
		System.out.println("");
		for (int i = 0; i < pathLength.length; i++) {
			System.out.println("pathLength" + pathLength[i] + " ");
		}
		System.out.println("");
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
