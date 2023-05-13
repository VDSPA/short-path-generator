package team.smd.vdsp.utils;

import java.util.LinkedList;
import java.util.Arrays;
import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;

public class BFS extends ShortestPath {

	/** shortest path length */
	private int[] distance;

	/** whether this vertex has been visited */
	private boolean[] visited;

	/** Precursor on the shortest path */
	private int[] pre;

	/** BFS queue */
	private LinkedList<Integer> bfsQueue = new LinkedList<Integer>();

	BFS() {
	}

	BFS(int[][] Matrix, int start) {
		super(Matrix, start);
		distance = new int[vSize];
		visited = new boolean[vSize];
		pre = new int[vSize];
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
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(pre, -1);
		Arrays.fill(visited, false);
	}

	/**
	 * find shortest path
	 */
	public void shortest() {
		this.stepQueue.clear();
		this.allPath.clear();
		bfsQueue.offer(start);
		this.distance[start] = 0;
		// traverse start
		Target[] tarTemp = new Target[1];
		Step stepTemp;
		tarTemp[0] = new Target("node", "" + start);
		stepTemp = new Step("traverse", tarTemp);
		stepQueue.offer(stepTemp);
		while (!bfsQueue.isEmpty()) {
			int u = bfsQueue.poll();// head element of queue
			if (visited[u] == true) {
				continue;
			}
			visited[u] = true;
			for (int i = 0; i < vSize; i++) {
				if ((adjMatrix[u][i] != 0) && (adjMatrix[u][i] != Integer.MAX_VALUE)) {
					// If there is a path between node u and node i
					// traverse edge u:i
					tarTemp[0] = new Target("edge", u + ":" + i);
					stepTemp = new Step("traverse", tarTemp);
					stepQueue.offer(stepTemp);
					// traverse node i
					tarTemp[0] = new Target("node", "" + i);
					stepTemp = new Step("traverse", tarTemp);
					stepQueue.offer(stepTemp);
					if ((distance[i] > distance[u] + adjMatrix[u][i])) {
						distance[i] = distance[u] + adjMatrix[u][i];
						pre[i] = u;
						bfsQueue.offer(i);

					}

				} else {
					if (visited[u] != true) {
						visited[u] = true;
						tarTemp[0] = new Target("node", "" + u);
						stepTemp = new Step("settle", tarTemp);
						stepQueue.offer(stepTemp);
					}

				}
			}

		}
		// Save the shortest path information in allPath
		for (int end = 0; end < vSize; end++) {
			if (end == start || visited[end] == false)
				continue;
			else {
				this.allPath.add(start + " - " + end + " : " + getOnePath(end) + "\n");
			}
		}

	}

	/**
	 * Find a shortest path according to the pre table
	 * 
	 * @param end
	 * @return path String
	 */
	public String getOnePath(int end) {
		String path = String.valueOf(end);

		while (true) {
			end = findPre(end);
			if (end == -1)
				break;
			path = (String.valueOf(end) + " -> ") + path;
		}
		return path;
	}

	/**
	 * return all shortest distances
	 */
	public int[] getAllDis() {
		return distance;
	}

	/**
	 * According to the pre table
	 * find the predecessor of a vertex on the shortest path
	 * 
	 * @param index
	 * @return pre's index
	 */
	int findPre(int index) {
		return pre[index];
	}

	/**
	 * Show tool table information, for internal testing
	 */
	public void showInfo() {
		System.out.println("Shortest path:");

		for (int i = 0; i < vSize; i++) {
			System.out.print(distance[i] + " ");
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
}
