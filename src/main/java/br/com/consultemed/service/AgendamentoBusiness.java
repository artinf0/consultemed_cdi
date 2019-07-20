package br.com.consultemed.service;

import java.util.Collection;

import javax.inject.Inject;

import br.com.consultemed.dao.AgendamentoImpl;
import br.com.consultemed.model.Agendamento;

public class AgendamentoBusiness {

	@Inject
	private AgendamentoImpl dao;
	
	public AgendamentoBusiness() {
		this.dao = new AgendamentoImpl();
	}
	
	public void save(Agendamento agendamento) throws Exception  {
		this.dao.save(agendamento);
	}
	
	public void update(Agendamento agendamento) throws Exception  {
		this.dao.update(agendamento);
	}
	
	public Collection<Agendamento> listAll() throws Exception {
		return this.dao.listAll();
	}
	
	public Agendamento findById(Long id) throws Exception {
		return this.dao.findById(id);
	}
	
	public void deleteById(Long id) throws Exception {
		this.dao.deleteById(id);
	}
	
	public int count() throws Exception {
		return this.dao.countAgendamento();
	}
}
