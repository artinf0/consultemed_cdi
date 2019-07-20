package br.com.consultemed.controller;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.PacienteBusiness;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.DataUtils;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

@WebServlet("/admin/pacientes")
public class PacienteController extends HttpServlet {

    @Inject
    private PacienteBusiness business;

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
        Paciente paciente = new Paciente();

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String dataNascimento = request.getParameter("dataNascimento");

        paciente.setNome(nome);
        paciente.setCpf(cpf);
        try {
            paciente.setDataNascimento(DataUtils.stringToDate(dataNascimento, "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String id = request.getParameter("id");
        if(id != "") {
            paciente.setId(Long.parseLong(id));
            request.setAttribute("editado", nome + " " + Constantes.USUARIO_EDITADO);
        }else {
            request.setAttribute("cadastro", nome + " " + Constantes.USUARIO_SUCESSO);
        }

        try {
            this.business.save(paciente);
            list(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher(Constantes.PACIENTES);
        Collection<Paciente> pacientes = this.business.listAll();
        request.setAttribute("pacientes", pacientes);
        rd.forward(request, response);

    }

    private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
        rd.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
        Paciente paciente = this.business.findById(Long.parseLong(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher(Constantes.ADD_PACIENTES);
        request.setAttribute("paciente", paciente);
        rd.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
        this.business.deleteById(Long.parseLong(request.getParameter("id")));
        request.setAttribute("remover", Constantes.PACIENTE_REMOVIDO);
        list(request, response);
    }

}
