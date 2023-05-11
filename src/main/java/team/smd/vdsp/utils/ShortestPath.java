package team.smd.vdsp.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import team.smd.vdsp.models.Step;

/**
 * ShortestPath algorithm class
 * includes paths, traversing steps
 */
public abstract class ShortestPath {
    /** matrix */
    int adjMatrix[][];

    /** start point id */
    int start;

    /** numbers of vertexes */
    int vSize;

    /** steps queue */
    LinkedList<Step> stepQueue = new LinkedList<Step>();

    /** all shortest path */
    ArrayList<String> allPath = new ArrayList<String>();

    public ShortestPath() {
        this.adjMatrix = new int[][] { {} };
        this.start = 0;
        this.vSize = adjMatrix.length;
    }

    public ShortestPath(int adjMatrix[][], int start) {
        this.adjMatrix = adjMatrix;
        this.start = start;
        this.vSize = adjMatrix.length;

    }

    /**
	 * Run specific algorithm to find shortest path.
	 * Stores result in member variables
     */
    public abstract void shortest();

    /**
     * get all shortest Path
     * 
     * @return all Path
     */
    public ArrayList<String> getAllPaths() {
        return this.allPath;
    }

    /**
     * get all steps
     * 
     * @return step queue
     */
    public LinkedList<Step> getAllSteps() {
        return this.stepQueue;
    }

    /**
     * get vertex size
     * 
     * @return vertex size
     */
    public int getVSize() {
        return vSize;
    }

    /**
     * show adjMatrix
     * 
     * @return matrix
     */
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

	@Override
    public String toString() {
        String info = "";
        // String temp=this.allPath.toString();
        for (int i = 0; i < allPath.size(); i++) {
            info += allPath.get(i);
            // temp.add(allPath.get(i));
        }
        for (int i = 0; i < stepQueue.size(); i++) {
            info += stepQueue.get(i) + "\n";
        }
        return info;
    }
}
