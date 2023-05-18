package team.smd.vdsp.dtos.graph;

public class CreateResponseDto {
	private int[][] graph;

	public CreateResponseDto(int[][] graph) {
		this.graph = graph;
	}

	public int[][] getGraph() {
		return graph;
	}

	public void setGraph(int[][] graph) {
		this.graph = graph;
	}
}
