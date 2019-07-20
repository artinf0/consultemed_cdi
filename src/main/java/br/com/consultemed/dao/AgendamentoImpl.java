package br.com.consultemed.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.utils.JPAUtils;

public class AgendamentoImpl implements IAgendamentoDao{
	
	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = null;

	@Override
	public void save(Agendamento agendamento) throws Exception {
		this.factory = emf.createEntityManager();

		try {
			factory.getTransaction().begin();
			if (agendamento.getId() == null) {
				factory.persist(agendamento);
			} else {
				factory.merge(agendamento);
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
	public Agendamento findById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		Agendamento agendamento = new Agendamento();
		try {
			agendamento = factory.find(Agendamento.class, id);
			return agendamento;

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
		Agendamento agendamento = new Agendamento();

		try {

			agendamento = factory.find(Agendamento.class, id);
			factory.getTransaction().begin();
			factory.remove(agendamento);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		
	}

	@Override
	public void update(Agendamento agendamento) throws Exception {
		this.factory = emf.createEntityManager();

		try {
			factory.getTransaction().begin();
			factory.merge(agendamento);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}
		
	}

	@Override
	public Collection<Agendamento> listAll() throws Exception {
		this.factory = emf.createEntityManager();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		try {
			factory.getTransaction().begin();
			TypedQuery<Agendamento> query = factory.createNamedQuery("Agendamento.findAll", Agendamento.class);
			agendamentos = query.getResultList();
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return agendamentos;
	}

	@Override
	public int countAgendamento() throws Exception {
		this.factory = emf.createEntityManager();
		int numAgendamento = 0;

		try {
			factory.getTransaction().begin();
			numAgendamento = ((Number)this.factory.createNamedQuery("Agendamento.findAllCount").getSingleResult()).intValue();
			System.out.println(numAgendamento);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return numAgendamento;
	}

}
