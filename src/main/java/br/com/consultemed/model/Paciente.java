package br.com.consultemed.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(name = "Paciente.findAllCount", query = "SELECT COUNT(c) FROM Paciente c"),
	@NamedQuery(name="Paciente.findAll", query="SELECT c FROM Paciente c")})

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente extends AbstractEntity<Long>  {

    private String nome;
    @Column(unique = true)
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public Paciente() {
    }

    public Paciente(String nome, String cpf, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
