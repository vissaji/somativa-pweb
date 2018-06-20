package br.senai.sp.info.pweb.keeper.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.keeper.dao.AmbienteDAO;
import br.senai.sp.info.pweb.keeper.models.Ambiente;

@Repository
@Transactional

public class AmbienteJPA implements AmbienteDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Ambiente buscar(Long id) {
		String hql = "FROM Ambiente a WHERE a.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Ambiente> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Ambiente> buscarTodos() {
		String hql = "FROM Ambiente a";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

	@Override
	public void deletar(Ambiente obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void editar(Ambiente obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void persistir(Ambiente obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public Ambiente buscarPorNome(String nome) {
		String hql = "FROM Ambiente a WHERE a.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		List<Ambiente> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

}
