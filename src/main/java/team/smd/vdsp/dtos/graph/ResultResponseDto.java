package team.smd.vdsp.dtos.graph;
import java.util.ArrayList;
import java.util.LinkedList;

import team.smd.vdsp.models.Result;
import team.smd.vdsp.models.Step;

public class ResultResponseDto {

	private LinkedList<Step> dijkstra;
	private LinkedList<Step> dfs;
	private LinkedList<Step> bfs;
	private LinkedList<Step> floyd;

	public void ResponseDto(ArrayList<Result> results) {
		this.dijkstra = null;
		this.dfs = null;
		this.bfs = null;
		this.floyd = null;
	}

	public void setDijkstra(LinkedList<Step> steps) {
		this.dijkstra = steps;
	}
	
	public void setDFS(LinkedList<Step> steps) {
		this.dfs = steps;
	}

	public void setBFS(LinkedList<Step> steps) {
		this.bfs = steps;
	}

	public void setFloyd(LinkedList<Step> steps) {
		this.floyd = steps;
	}

	public LinkedList<Step> getDijkstra() {
		return dijkstra;
	}

	public LinkedList<Step> getDfs() {
		return dfs;
	}

	public LinkedList<Step> getBfs() {
		return bfs;
	}

	public LinkedList<Step> getFloyd() {
		return floyd;
	}
}
