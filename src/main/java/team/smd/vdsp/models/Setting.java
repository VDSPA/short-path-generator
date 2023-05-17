package team.smd.vdsp.models;

import java.util.Arrays;

public class Setting {
	// is directed graph or not
	private boolean isDirected;

	// Adjacency matrix of graph
	private int[][] matrix;
	// Starting point of the path
	private int start;

	/**
	 * constructor
	 * default isDirected = true
	 *
	 * @param matrix
	 * @param start
	 */
	public Setting(int[][] matrix, int start) {
		this.isDirected = true;
		this.matrix = matrix;
		this.start = start;
	}

	/**
	 * constructor
	 *
	 * @param isDirected
	 * @param matrix
	 * @param start
	 */
	public Setting(boolean isDirected, int[][] matrix, int start) {
		this.isDirected = isDirected;
		this.matrix = matrix;
		this.start = start;
	}

	/**
	 * get isDirected
	 *
	 * @return isDirected
	 */
	public boolean isDirected() {
		return isDirected;
	}

	/**
	 * get the adjacency matrix of a graph
	 *
	 * @return matrix
	 */
	public int[][] getMatrix() {
		return matrix;
	}

	/**
	 * get starting point of the path
	 *
	 * @return start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * set the adjacency matrix of a graph
	 * set whether it is directed or undirected
	 * set starting point of the path
	 *
	 * @param directed
	 * @param matrix
	 * @param start
	 */
	public void resetMatrixInfo(boolean directed, int[][] matrix, int start) {
		isDirected = directed;
		this.matrix = matrix;
		this.start = start;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Setting other = (Setting) obj;
		if (isDirected != other.isDirected)
			return false;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		if (start != other.start)
			return false;
		return true;
	}
}
