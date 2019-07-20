package br.com.consultemed.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Medico.findAllCount", query = "SELECT COUNT(c) FROM Medico c"),
	@NamedQuery(name="Medico.findAll", query="SELECT c FROM Medico c")})

@Entity
@Table(name="TB_MEDICOS")
public class Medico extends AbstractEntity<Long> {
	
	private String nome;
	
	private String crm;
	
	public Medico() {

	}

	public String getNome() {
		return nome;	
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

}
