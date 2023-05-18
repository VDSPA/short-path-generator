package team.smd.vdsp.controllers.graph;

import java.io.IOException;
import java.io.PrintWriter;

import javax.lang.model.type.NullType;

import com.alibaba.fastjson2.JSON;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import team.smd.vdsp.dtos.graph.ResultRequestDto;
import team.smd.vdsp.dtos.graph.ResultResponseDto;
import team.smd.vdsp.models.Response;
import team.smd.vdsp.services.graph.GraphService;
import team.smd.vdsp.utils.RequestUtil;
import jakarta.servlet.ServletException;

@WebServlet("/api/graph/result")
public class GraphResultServlet extends HttpServlet {

	private GraphService graphService;

	public GraphResultServlet() {
		super();
		graphService = new GraphService();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter	out = res.getWriter();
		try {
			ResultRequestDto requestDto = JSON.parseObject(RequestUtil.getBody(req), ResultRequestDto.class);
			ResultResponseDto responseDto = graphService.getResult(requestDto.getSettings());

			String data = JSON.toJSONString(new Response<ResultResponseDto>(responseDto, "ok", 200));
			out.print(data);
		} catch(Exception e) {
			Response<NullType> r = new Response<>(null, e.toString(), 500);
			out.print(JSON.toJSONString(r));
		}

	}

}
