package br.com.consultemed.dao;

import java.util.Collection;

import br.com.consultemed.model.Agendamento;

public interface IAgendamentoDao extends GenericDao<Agendamento, Long>{

public void save(Agendamento agendamento) throws Exception;
	
	public Agendamento findById(Long id) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
	public void update(Agendamento agendamento) throws Exception;
	
	public Collection<Agendamento> listAll() throws Exception;
	
	public int countAgendamento() throws Exception;
	
}
