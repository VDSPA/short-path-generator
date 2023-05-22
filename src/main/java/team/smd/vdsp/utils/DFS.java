package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.Arrays;

import team.smd.vdsp.models.Target;
import team.smd.vdsp.models.Step;
import java.util.HashSet;

public class DFS extends ShortestPath {

	/** Store the distance of the shortest path */
	int[] shortestDis = new int[vSize];

	/** accumulate and store the edges of each DFSImpler settle */
	ArrayList<Target> accumulateSettle = new ArrayList<>();

	public DFS() {
	}

	public DFS(int[][] Matrix, int start) {
		super(Matrix, start, "dfs");
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
	 * Traversing vSize-1 points except the start, call the method in DFSImpl
	 * Find the shortest path from the start to all other points
	 */
	public void shortest() {
		resetParam();

		for (int end = 0; end < vSize; end++) {
			DFSImpl dfsImpl = new DFSImpl(this.adjMatrix, this.start, end);
			if (end == this.start) {
				shortestDis[end] = 0;
				continue;
			} else {
				dfsImpl.shortest();

				for (Step element : dfsImpl.stepQueue) {
					if (element.getType() == "settle") {
						// If the type is settle, it will be accumulated in accumulateSettle
						for (Target i : element.getTargets()) {
							accumulateSettle.add(i);
						}
						accumulateSettle = removeDuplicates(accumulateSettle);

					} else {
						// If the type is traverse, go directly to stepQueue
						this.stepQueue.add(element);
					}
				}

				accumulateSettle = removeDuplicates(accumulateSettle);

				// Put the accumulated Settle into stepQueue
				Target[] targetArr = accumulateSettle.toArray(new Target[accumulateSettle.size()]);
				this.stepQueue.add(new Step("settle", targetArr));
				this.allPath.addAll(dfsImpl.allPath);
				shortestDis[end] = dfsImpl.getShortestDis();
			}
		}

	}

	/**
	 * get all shortest distances
	 */
	public int[] getAllDis() {
		return shortestDis;
	}

	/**
	 * Reset some member variables that need to be accumulated
	 */
	public void resetParam() {
		this.stepQueue.clear();
		this.allPath.clear();
		shortestDis = new int[vSize];
		accumulateSettle = new ArrayList<Target>();
	}

	public static ArrayList<Target> removeDuplicates(ArrayList<Target> originalList) {
		HashSet<Target> set = new HashSet<>();
		ArrayList<Target> newList = new ArrayList<>();

		for (Target target : originalList) {
			if (set.add(target)) {
				newList.add(target);
			}
		}

		return newList;
	}

}
