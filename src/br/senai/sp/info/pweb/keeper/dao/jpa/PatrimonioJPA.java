package br.senai.sp.info.pweb.keeper.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.keeper.dao.PatrimonioDAO;
import br.senai.sp.info.pweb.keeper.models.Patrimonio;

@Repository
@Transactional

public class PatrimonioJPA implements PatrimonioDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Patrimonio buscar(Long id) {
		String hql = "FROM Patrimonio p WHERE p.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Patrimonio> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Patrimonio> buscarTodos() {
		String hql = "FROM Patrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
		return query.list();
	}

	@Override
	public void deletar(Patrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void editar(Patrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void persistir(Patrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);	
	}

	@Override
	public Patrimonio buscarPorNome(String nome) {
		String hql = "FROM Patrimonio p WHERE p.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("nome", nome);
		List<Patrimonio> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
	
}
