package br.com.consultemed.model;

import br.com.consultemed.utils.DataUtils;

import java.util.Date;

import javax.persistence.*;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Agendamento.findAllCount", query = "SELECT COUNT(c) FROM Agendamento c"),
	@NamedQuery(name="Agendamento.findAll", query="SELECT c FROM Agendamento c")})

@Entity
@Table(name="TB_AGENDAMENTOS")
public class Agendamento extends AbstractEntity<Long> {

	@ManyToOne
	private Medico medico;

	@ManyToOne
	private Paciente paciente;

	@Temporal(TemporalType.DATE)
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

	public String getDataAgendamentoFormatado() {
		return DataUtils.formatarData(dataAgendamento);
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
