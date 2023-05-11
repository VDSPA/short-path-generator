package team.smd.vdsp.utils;

public class DFS extends ShortestPath {
	public DFS() {
	}

	public DFS(int[][] Matrix, int start) {
		super(Matrix, start);
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

		for (int end = 0; end < vSize; end++) {
			DFSImpl dfsImpl = new DFSImpl(this.adjMatrix, this.start, end);
			if (end == this.start)
				continue;
			else {
				dfsImpl.shortest();
				this.stepQueue.addAll(dfsImpl.stepQueue);
				this.allPath.add(dfsImpl.allPath.get(0));
			}
		}
	}
}
