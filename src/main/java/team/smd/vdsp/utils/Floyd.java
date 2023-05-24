package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import team.smd.vdsp.models.Step;
import team.smd.vdsp.models.Target;

public class Floyd extends ShortestPath {

	/** the shortest distance from one vertex to another */
	private int[][] distance;

	/** intermedia node between two vertexes */
	public int[][] path;

	/** The shortest distance from the start to other vertex */
	public int[] shortestDis;

	/** the middle point from i to j (including i and j) */
	ArrayList<Integer> ijVertex = new ArrayList<>();

	/** the middle point from i to k (including i and k) */
	ArrayList<Integer> ikVertex = new ArrayList<>();

	/** the middle point from k to j (including k and j) */
	ArrayList<Integer> kjVertex = new ArrayList<>();

	/** the middle edge from i to j */
	ArrayList<String> ijEdge = new ArrayList<>();

	/** the middle edge from i to k */
	ArrayList<String> ikEdge = new ArrayList<>();

	/** the middle edge from k to j */
	ArrayList<String> kjEdge = new ArrayList<>();

	public Floyd() {
	}

	public Floyd(int[][] Matrix, int start) {
		super(Matrix, start, "floyd");
		resetParam();
	}

	/**
	 * get all shortest distances
	 */
	public int[] getAllDis() {
		return shortestDis;
	}

	/**
	 * Get the shortest path through multiple loops
	 */
	public void shortest() {
		resetParam();
		for (int k = 0; k < vSize; k++) {
			for (int i = 0; i < vSize; i++) {
				for (int j = 0; j < vSize; j++) {
					if (i == j || i == k || j == k) {
						// no need to update the matrix
						continue;
					}
					update(i, j, k);
				}
			}

		}
		for (int i = 0; i < vSize; i++) {
			shortestDis[i] = distance[start][i];
		}
		for (int end = 0; end < vSize; end++) {
			if (start == end || (path[start][end] == -1 && adjMatrix[start][end] == Integer.MAX_VALUE))
				continue;
			this.allPath.add(start + " - " + end + ":" + getOnePath(this.path, start, end) + " " + end + "\n");
		}

	}

	/**
	 * Compare the information in the matrix,
	 * update the distance and path matrix and record the step
	 *
	 * @param i
	 * @param j
	 * @param k
	 */
	public void update(int i, int j, int k) {
		clearToolArr();
		ArrayList<Integer> traVertex = new ArrayList<>();// traversed vertex
		ArrayList<String> traEdge = new ArrayList<>();// traversed edge
		traVertex.addAll(Arrays.asList(i, k, j));
		getMiddle(i, j, ijVertex, ijEdge, traVertex, traEdge);
		getMiddle(i, k, ikVertex, ikEdge, traVertex, traEdge);
		getMiddle(k, j, kjVertex, kjEdge, traVertex, traEdge);

		// remove duplicates of traverse
		traVertex = removeDuplicates(traVertex);
		traEdge = removeDuplicates(traEdge);

		offerStep(traVertex, traEdge, "traverse");

		ArrayList<String> setEdge = new ArrayList<>();
		ArrayList<Integer> setVertex = new ArrayList<>();

		if (distance[i][j] > add(distance[i][k], distance[k][j])) {

			distance[i][j] = add(distance[i][k], distance[k][j]);

			// settle node i,k,j,there may be middle nodes between i and k, k and j
			setVertex.addAll(ikVertex);
			setVertex.addAll(kjVertex);
			// settle edge i:k,k:j,there may be middle edges between i and k, k and j
			setEdge.addAll(ikEdge);
			setEdge.addAll(kjEdge);

			// update path matrix,record middle vertex
			path[i][j] = k;// from i to j, middle vertex is k,update path matrix

		} else {
			setVertex.addAll(ijVertex);
			setEdge.addAll(ijEdge);
		}

		// remove duplicates of settle
		setVertex = removeDuplicates(setVertex);
		setEdge = removeDuplicates(setEdge);

		offerStep(setVertex, setEdge, "settle");

	}

	/**
	 * Reset some member variables that need to be accumulated
	 */
	public void resetParam() {
		this.stepQueue.clear();
		this.allPath.clear();
		distance = new int[vSize][adjMatrix[0].length];
		path = new int[vSize][adjMatrix[0].length];
		shortestDis = new int[vSize];
		// Initialize the path matrix
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length; j++) {
				path[i][j] = -1;
			}
		}
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				this.adjMatrix[i][j] = adjMatrix[i][j];
				this.distance[i][j] = adjMatrix[i][j];// Specific to Floyd's algorithm
			}
		}
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix.length; j++) {
				if ((i != j) && (adjMatrix[i][j] == 0)) {
					adjMatrix[i][j] = Integer.MAX_VALUE;
					distance[i][j] = Integer.MAX_VALUE;// Specific to Floyd's algorithm
				}
			}
		}

	}

	/**
	 * clear tool ArrayList
	 */
	public void clearToolArr() {
		ijVertex = new ArrayList<>();
		ikVertex = new ArrayList<>();
		kjVertex = new ArrayList<>();
		ijEdge = new ArrayList<>();
		ikEdge = new ArrayList<>();
		kjEdge = new ArrayList<>();
	}

	/**
	 * get the middle vertex ,middle edge between two points
	 * record traverse info
	 * 
	 * @param a
	 * @param b
	 * @param abMedia
	 * @param abEdge
	 * @param traVertex
	 */
	public void getMiddle(int a, int b, ArrayList<Integer> abVertex, ArrayList<String> abEdge,
			ArrayList<Integer> traVertex, ArrayList<String> traEdge) {
		if (distance[a][b] != Integer.MAX_VALUE) {
			for (Integer element : getMiddleV(path, a, b)) {
				abVertex.add(element);
				traVertex.add(element);
			}
			abVertex = removeDuplicates(abVertex);
			for (int m = 0; m < abVertex.size() - 1; m++) {
				abEdge.add(abVertex.get(m) + ":" + abVertex.get(m + 1));
			}
		}
		traEdge.addAll(abEdge);

	}

	/**
	 * Save traverse or settle information into stepQueues
	 * 
	 * @param V
	 * @param E
	 */
	public void offerStep(ArrayList<Integer> V, ArrayList<String> E, String traOrSet) {
		Target[] tarTemp = new Target[V.size() + E.size()];
		for (int n = 0; n < tarTemp.length; n++) {
			if (n < V.size()) {
				tarTemp[n] = new Target("node", "" + V.get(n));
			} else {
				tarTemp[n] = new Target("edge", "" + E.get(n - V.size()));
			}
		}
		if (tarTemp.length != 0) {
			Step stepTemp = new Step(traOrSet, tarTemp);
			stepQueue.offer(stepTemp);
		}
	}

	/**
	 * Addition of Integer.MAX_VALUE
	 * (Integer.MAX_VALUE + otherNum = Integer.MAX_VALUE)
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int add(int a, int b) {
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return a + b;
		}

	}

	/**
	 * Remove duplicate elements in ArrayList
	 * 
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
		LinkedHashSet<T> set = new LinkedHashSet<>(list);
		return new ArrayList<>(set);
	}

	/**
	 * get the shortest path from i to j
	 * 
	 * @param path
	 * @param i
	 * @param j
	 * @return
	 */
	public String getOnePath(int[][] path, int i, int j) {
		if (path[i][j] == -1) {
			return " " + i + " ->";
		} else {
			int k = path[i][j];
			return getOnePath(path, i, k) + getOnePath(path, k, j);
		}
	}

	/**
	 * Get the middle node of two points
	 * 
	 * @param path
	 * @param i
	 * @param j
	 * @return
	 */
	public int[] getMiddleV(int[][] path, int i, int j) {

		if (path[i][j] == -1) {
			// unreachable, no middle nodes
			int[] temp = { i, j };
			return temp;
		} else {
			// reachable
			int k = path[i][j];
			int[] temp1 = getMiddleV(path, i, k);
			int[] temp2 = getMiddleV(path, k, j);
			int[] result = new int[temp1.length + temp2.length];
			System.arraycopy(temp1, 0, result, 0, temp1.length);
			System.arraycopy(temp2, 0, result, temp1.length, temp2.length);
			return result;
		}
	}

	/**
	 * Show the distance matrix, for internal testing
	 * 
	 * @return
	 */
	public String showDistance() {
		String disMatrix = "";
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance[i].length; j++) {
				if (distance[i][j] == Integer.MAX_VALUE) {
					disMatrix += String.format("%5s", "MAX");
				} else {
					disMatrix += String.format("%5s ", distance[i][j]);
				}
			}
			disMatrix += "\n";
		}
		return disMatrix;

	}

	/**
	 * Show the path matrix, for internal testing
	 * 
	 * @return
	 */
	public String showPathMatrix() {
		String pathMatrix = "";
		for (int i = 0; i < vSize; i++) {
			for (int j = 0; j < vSize; j++) {
				pathMatrix += String.format("%5s ", path[i][j]);
			}
			pathMatrix += "\n";
		}
		return pathMatrix;
	}

}
