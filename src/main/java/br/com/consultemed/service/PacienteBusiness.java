package br.com.consultemed.service;

import br.com.consultemed.dao.PacienteDAOImpl;
import br.com.consultemed.model.Paciente;

import javax.inject.Inject;
import java.util.Collection;

public class PacienteBusiness {
    @Inject
    private PacienteDAOImpl dao;

    public PacienteBusiness() {

    }

    public void save(Paciente p) throws Exception  {
        this.dao.save(p);
    }

    public Collection<Paciente> listAll() throws Exception {
        return this.dao.listAll();
    }

    public void deleteById(Long id) throws Exception {
        this.dao.deleteById(id);
    }

    public Paciente findById(Long id) throws Exception {
        return this.dao.findById(id);
    }
}
