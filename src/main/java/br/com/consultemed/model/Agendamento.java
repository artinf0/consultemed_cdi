package br.com.consultemed.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Agendamento.findAllCount", query = "SELECT COUNT(c) FROM Agendamento c"),
	@NamedQuery(name="Agendamento.findAll", query="SELECT c FROM Agendamento c")})

@Entity
@Table(name="TB_AGENDAMENTOS")
public class Agendamento extends AbstractEntity<Long> {
	
	private Medico medico;
	
	private Paciente paciente;
	
	private Date dataAgendamento;

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}	
}
