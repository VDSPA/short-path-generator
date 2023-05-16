package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;
import java.util.Arrays;

public class DFSImpl extends ShortestPath {

	/** The length of the current shortest path */
	private int shortestDis;

	/** short-path of current dfs */
	private ArrayList<Integer> shortest = new ArrayList<>();

	/** the end vertex */
	private int end;

	DFSImpl() {
	}

	DFSImpl(int[][] Matrix, int start, int end) {
		super(Matrix, start);
		this.end = end;
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

	/**
	 * 
	 * find all paths between two vertexes
	 * record the shortest path's traverse and path infomation
	 */
	public void shortest() {
		this.stepQueue.clear();
		this.allPath.clear();
		shortestDis = Integer.MAX_VALUE;

		boolean[] visited = new boolean[vSize];// Whether the node has been visited
		ArrayList<Integer> pathList = new ArrayList<>();// store the current path
		int[] pathLength = new int[vSize];
		Arrays.fill(pathLength, Integer.MAX_VALUE);
		// Search for the shortest path from the start
		pathList.add(start);
		pathLength[start] = 0;
		this.stepQueue = new LinkedList<Step>();
		stepQueue = dfsShortest(start, end, visited, pathList, pathLength);

		// Now, the elements in the shortest array are the edges on
		// the shortest path between the current start and the end
		// Settle all edges of the shortest path
		if (shortest.size() != 0) {
			Target[] tt = new Target[shortest.size() - 1];
			for (int i = 0; i < shortest.size() - 1; i++) {
				tt[i] = new Target("edge", shortest.get(i) + ":" + shortest.get(i + 1));
			}
			Step ss = new Step("settle", tt);
			stepQueue.offer(ss);
		}

		// After finding the shortest path between two vertexes, reset
		Target[] tarTemp = new Target[1];
		tarTemp[0] = new Target("edge", "");
		Step stepTemp = new Step("reset", tarTemp);
		stepQueue.offer(stepTemp);

		// Remove duplicate elements from the stepQueue
		for (int i = 0; i < stepQueue.size(); i++) {
			if (i == stepQueue.size() - 1)
				break;
			else {
				if ((stepQueue.get(i)).equals(stepQueue.get(i + 1))) {
					stepQueue.remove(i);
				}
			}
		}

		// get the shortest path from the current start to end from shortest
		// array,
		// And add the path to the allPath member variable
		if (shortest.size() <= 0) {

		} else {
			String path = start + "";
			for (int i = 1; i < shortest.size(); i++) {
				path += (" -> " + shortest.get(i));
			}
			this.allPath.add(start + " - " + end + " : " + path + "\n");
		}

	}

	/**
	 * 
	 * @param u          start
	 * @param end        end
	 * @param visited
	 * @param pathList
	 * @param pathLength
	 * @return stepInfo
	 */
	public LinkedList<Step> dfsShortest(int u, int end, boolean[] visited, ArrayList<Integer> pathList,
			int[] pathLength) {
		LinkedList<Step> queue = new LinkedList<>();
		Step stepTemp;
		visited[u] = true;

		// traverse node u
		Target[] tarTemp = new Target[1];
		if (u == start) {
			tarTemp[0] = new Target("node", "" + start);
			stepTemp = new Step("traverse", tarTemp);
			queue.offer(stepTemp);
		}

		if (u == end) {
			// if current node is the end point,then
			// Compare the path with the length of the existing shortest path,
			// if the current path is shorter, update the shortest path
			if (shortestDis > pathLength[end]) {
				shortestDis = pathLength[end];
				shortest = new ArrayList<>(pathList);
			}
			// backtrack
			visited[u] = false;
			return queue;
		}

		for (int v = 0; v < vSize; v++) {
			if (adjMatrix[u][v] != 0
					&& !visited[v]
					&& adjMatrix[u][v] != Integer.MAX_VALUE) {

				// Edges exist which have not been visited yet, continue traversing
				pathList.add(v);
				// traverse edge u:v
				tarTemp[0] = new Target("edge", u + ":" + v);
				stepTemp = new Step("traverse", tarTemp);
				queue.offer(stepTemp);

				// traverse node v
				tarTemp[0] = new Target("node", "" + v);
				stepTemp = new Step("traverse", tarTemp);
				queue.offer(stepTemp);
				pathLength[v] = pathLength[u] + adjMatrix[u][v];
				queue.addAll(dfsShortest(v, end, visited, pathList, pathLength));

				// backtrack
				pathList.remove(pathList.size() - 1);
				pathLength[v] = Integer.MAX_VALUE;
			}
		}
		// backtrack
		visited[u] = false;

		return queue;
	}

	/**
	 * useless
	 */
	public int[] getAllDis() {
		int[] shortestDis = new int[vSize];
		return shortestDis;
	}

	/*
	 * get shortest distance
	 */
	public int getShortestDis() {
		return this.shortestDis;
	}

	/**
	 * Show intermediate information for internal test
	 * 
	 * @param visited
	 * @param pathList
	 * @param pathLength
	 */
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
}
