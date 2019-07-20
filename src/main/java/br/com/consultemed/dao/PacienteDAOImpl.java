package br.com.consultemed.dao;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {
    EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
    EntityManager factory = null;


    @Override
    public void save(Paciente paciente) throws Exception {
        this.factory = emf.createEntityManager();

        try {
            factory.getTransaction().begin();
            if (paciente.getId() == null) {
                factory.persist(paciente);
            } else {
                factory.merge(paciente);
            }
            factory.getTransaction().commit();

        } catch (Exception e) {
            e.getMessage();
            this.factory.getTransaction().rollback();
        } finally {
            factory.close();
        }
    }

    @Override
    public Paciente findById(Long id) throws Exception {
        this.factory = emf.createEntityManager();
        Paciente usuario = new Paciente();
        try {
            usuario = factory.find(Paciente.class, id);
            return usuario;

        } catch (Exception e) {

            e.getMessage();
            this.factory.getTransaction().rollback();

        } finally {
            factory.close();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        this.factory = emf.createEntityManager();
        Paciente usuario = new Paciente();

        try {

            usuario = factory.find(Paciente.class, id);
            factory.getTransaction().begin();
            factory.remove(usuario);
            factory.getTransaction().commit();

        } catch (Exception e) {
            e.getMessage();
            this.factory.getTransaction().rollback();
        } finally {
            factory.close();
        }
    }

    @Override
    public void update(Paciente paciente) throws Exception {
        this.factory = emf.createEntityManager();

        try {
            factory.getTransaction().begin();
            factory.merge(paciente);
            factory.getTransaction().commit();

        } catch (Exception e) {
            e.getMessage();
            this.factory.getTransaction().rollback();
        } finally {
            factory.close();
        }
    }

    @Override
    public Collection<Paciente> listAll() throws Exception {
        this.factory = emf.createEntityManager();
        List<Paciente> usuarios = new ArrayList<Paciente>();

        try {
            factory.getTransaction().begin();
            TypedQuery<Paciente> query = factory.createNamedQuery("Paciente.findAll", Paciente.class);
            usuarios = query.getResultList();
            factory.getTransaction().commit();

        } catch (Exception e) {
            e.getMessage();
            this.factory.getTransaction().rollback();
        } finally {
            factory.close();
        }

        return usuarios;
    }
    
    @Override
	public int countPaciente() throws Exception {
		
		this.factory = emf.createEntityManager();
		int numPaciente = 0;
		
		try {
			factory.getTransaction().begin();
			 numPaciente = ((Number)this.factory.createNamedQuery("Paciente.findAllCount").getSingleResult()).intValue();
		     System.out.println(numPaciente);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return numPaciente;
	}
}
