package com.algaworks.id;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/relatorio")
public class RelatorioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject // instancia atraves do cdi
	private RelatorioService relatorioService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(relatorioService.totalPedidosMesAtual());
	}

}
