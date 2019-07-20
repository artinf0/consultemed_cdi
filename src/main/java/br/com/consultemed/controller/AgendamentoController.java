package br.com.consultemed.controller;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.AgendamentoBusiness;
import br.com.consultemed.service.MedicoBusiness;
import br.com.consultemed.service.PacienteBusiness;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.DataUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

@WebServlet("/admin/agendamentos")
public class AgendamentoController extends HttpServlet{
	
	@Inject
	private AgendamentoBusiness business;
	@Inject
	private PacienteBusiness pacienteBusiness;
	@Inject
	private MedicoBusiness medicoBusiness;

	private static final long serialVersionUID = 1L;

	public AgendamentoController() {
		this.business = new AgendamentoBusiness();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter(Constantes.ACTION);

		try {
			switch (action) {
				case Constantes.NOVO:
					novo(request, response);
					break;
				case Constantes.DELETE:
					delete(request, response);
					break;
				case Constantes.EDITAR:
					editar(request, response);
					break;
				case Constantes.LISTAR :
					list(request, response);
					break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Agendamento agendamento = new Agendamento();


		String crm = request.getParameter("crm");
		String cpf = request.getParameter("cpf");
		String dataAgendamento = request.getParameter("dataAgendamento");


		Medico medico = new Medico();
		Paciente paciente = new Paciente();
		try {
			medico = medicoBusiness.findByCrm(crm);
			paciente = pacienteBusiness.findByCpf(cpf);
		} catch (Exception e) {
			e.printStackTrace();
		}

		agendamento.setMedico(medico);
		agendamento.setPaciente(paciente);

		try {
			agendamento.setDataAgendamento(DataUtils.stringToDate(dataAgendamento, "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}


		String id = request.getParameter("id");
		if(id != "") {
			agendamento.setId(Long.parseLong(id));
			request.setAttribute("editado",  Constantes.AGENDAMENTO_EDITADO);
		}else {
			request.setAttribute("cadastro", Constantes.AGENDAMENTO_SUCESSO);
		}

		try {
			this.business.save(agendamento);
			list(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.AGENDAMENTOS);
		Collection<Agendamento> agendamentos = this.business.listAll();
		request.setAttribute("agendamentos", agendamentos);
		rd.forward(request, response);

	}

	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_AGENDAMENTOS);
		rd.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Agendamento agendamento = this.business.findById(Long.parseLong(request.getParameter("id")));
		RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_AGENDAMENTOS);
		request.setAttribute("agendamento", agendamento);
		rd.forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		this.business.deleteById(Long.parseLong(request.getParameter("id")));
		request.setAttribute("remover", Constantes.AGENDAMENTO_REMOVIDO);
		list(request, response);
	}

}
