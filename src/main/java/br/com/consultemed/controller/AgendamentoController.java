package br.com.consultemed.controller;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import br.com.consultemed.service.AgendamentoBusiness;

@WebServlet("/admin/agendamentos")
public class AgendamentoController extends HttpServlet{
	
	@Inject
	private AgendamentoBusiness business;

	private static final long serialVersionUID = 1L;

	public AgendamentoController() {
		this.business = new AgendamentoBusiness();
	}

}
