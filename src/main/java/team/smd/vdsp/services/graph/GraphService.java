package team.smd.vdsp.services.graph;

import java.util.ArrayList;

import team.smd.vdsp.dtos.graph.CreateResponseDto;
import team.smd.vdsp.models.Setting;
import team.smd.vdsp.dtos.graph.ResultResponseDto;
import team.smd.vdsp.utils.AlgorithmRunner;
import team.smd.vdsp.utils.DirectedWSG;
import team.smd.vdsp.models.Result;

public class GraphService {
 	public CreateResponseDto create() {
		int[][] matrix = DirectedWSG.getMatrix();
		CreateResponseDto responseDto = new CreateResponseDto(matrix);
		return responseDto;
	}

	public ResultResponseDto getResult(Setting setting) {
		AlgorithmRunner runner = new AlgorithmRunner(setting);
		ArrayList<Result> results =  runner.runAlgorithms();

		ResultResponseDto responseDto = new ResultResponseDto();
		for (Result result: results) {
			switch (result.getName()) {
				case "dijkstra": responseDto.setDijkstra(result.getSteps()); break;
				case "dfs": responseDto.setDFS(result.getSteps()); break;
				case "bfs": responseDto.setBFS(result.getSteps()); break;
				case "floyd": responseDto.setFloyd(result.getSteps()); break;
			}
		}
		return responseDto;
	}

}
