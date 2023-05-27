package team.smd.vdsp.utils;

import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;

public class Dijstra extends ShortestPath {

	/** shortest path length */
	private int[] distance;

	/** if the shortest path of a vertex has been found */
	private boolean[] visited;

	/** Precursor on the shortest path */
	private int[] pre;

	public Dijstra() {
	}

	public Dijstra(int[][] Matrix, int start) {
		super(Matrix, start, "dijkstra");
		this.adjMatrix = new int[vSize][Matrix[0].length];
		for (int i = 0; i < Matrix.length; i++) {
			for (int j = 0; j < Matrix[i].length; j++) {
				this.adjMatrix[i][j] = Matrix[i][j];
			}
		}
		resetParam();

		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if ((i != j) && (adjMatrix[i][j] == 0)) {
					adjMatrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}
	}

	/**
	 * 
	 * find the shortest path
	 */
	public void shortest() {
		resetParam();
		Step stepTemp;
		// Initialize the source node
		distance[start] = 0;
		visited[start] = true;
		stepTemp = new Step("settle", new Target[] { new Target("node", Integer.toString(start)) });
		stepQueue.offer(stepTemp);
		boolean StartHasOutEdge = false; // recode if start point has out edge
		// traverse all vertexes that start point can arrive directly
		for (int i = 0; i < vSize; i++) {
			if (start == i) {
				continue;
			}
			distance[i] = adjMatrix[start][i];

			if (adjMatrix[start][i] != Integer.MAX_VALUE) {
				StartHasOutEdge = true;
				// traverse edge start:i
				stepTemp = new Step("traverse", new Target[] { new Target("edge", start + ":" + i) });
				stepQueue.offer(stepTemp);
				// traverse node i
				stepTemp = new Step("traverse", new Target[] { new Target("node", Integer.toString(i)) });
				stepQueue.offer(stepTemp);

				pre[i] = start;

			}
		}
		if (StartHasOutEdge == false) {// if edge doesn't have out edge,we can end execution.
			return;
		}

		pre[start] = -1;
		int min = Integer.MAX_VALUE;
		int min_index = start;

		for (int x = 0; x < vSize - 1; x++) {
			for (int i = 0; i < vSize; i++) {
				// Traverse all the points to find
				// the vertex v that has not find the shortest path and has the smallest dist
				// min:the shortest distance
				// min_index:v's index
				if (visited[i] == true) {
					continue;
				} else {
					if (distance[i] < min) {
						min = distance[i];
						min_index = i;
					}
				}
			}

			visited[min_index] = true;
			// settle index min_index
			stepTemp = new Step("settle", new Target[] {
					new Target("node", Integer.toString(min_index)),
					new Target("edge", pre[min_index] + ":" + min_index)
			});
			stepQueue.offer(stepTemp);
			// Check all edges adjacent to v
			for (int j = 0; j < vSize; j++) {

				if (visited[j] == false && (adjMatrix[min_index][j] != Integer.MAX_VALUE)) {
					// traverse edge min_index；j
					stepTemp = new Step("traverse", new Target[] { new Target("node", min_index + ":" + j) });
					stepQueue.offer(stepTemp);

					// traverse index j
					stepTemp = new Step("traverse", new Target[] { new Target("node", Integer.toString(j)) });
					stepQueue.offer(stepTemp);
					if ((min + adjMatrix[min_index][j]) < distance[j]) {
						distance[j] = min + adjMatrix[min_index][j];
						pre[j] = min_index;

					}

				}
			}
			min = Integer.MAX_VALUE;

		}
		// Remove duplicate elements from the stepQueue
		for (int i = 0; i < stepQueue.size(); i++) {
			if (i == stepQueue.size() - 1)
				break;
			else {
				if (stepQueue.get(i).equals(stepQueue.get(i + 1))) {
					stepQueue.remove(i);
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
	 * Reset some member variables that need to be accumulated
	 */
	public void resetParam() {
		this.stepQueue.clear();
		this.allPath.clear();
		distance = new int[vSize];
		visited = new boolean[vSize];
		pre = new int[vSize];
		for (int i = 0; i < vSize; i++) {
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE;
			pre[i] = -1;
		}

	}

	/**
	 * Iterate to get the shortest path
	 * 
	 * @param index end node index
	 * @return shortest path string
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
	 * get all shortest distances
	 */
	public int[] getAllDis() {
		return distance;
	}

	/**
	 * Show intermediate information for internal testing
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
