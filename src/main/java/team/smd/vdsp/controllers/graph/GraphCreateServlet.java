package team.smd.vdsp.controllers.graph;

import java.io.IOException;
import java.io.PrintWriter;

import javax.lang.model.type.NullType;

import com.alibaba.fastjson2.JSON;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import team.smd.vdsp.dtos.graph.CreateResponseDto;
import team.smd.vdsp.models.Response;
import team.smd.vdsp.services.graph.GraphService;

@WebServlet("/api/graph")
public class GraphCreateServlet extends HttpServlet {

	private GraphService graphService;

	public GraphCreateServlet() {
		super();
		graphService = new GraphService();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter	out = res.getWriter();
		try {
			Response<CreateResponseDto> data = new Response<CreateResponseDto>(graphService.create(), "ok", 200);

			out.print(JSON.toJSONString(data));
		} catch (Exception e) {
			Response<NullType> r = new Response<>(null, e.toString(), 500);
			out.print(JSON.toJSONString(r));
		}
	}

}
