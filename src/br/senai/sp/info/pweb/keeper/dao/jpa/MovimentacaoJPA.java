package br.senai.sp.info.pweb.keeper.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.keeper.dao.MovimentacaoDAO;
import br.senai.sp.info.pweb.keeper.models.Movimentacao;

@Repository
@Transactional

public class MovimentacaoJPA implements MovimentacaoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Movimentacao buscar(Long id) {
		String hql = "FROM Movimentacao m WHERE m.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Movimentacao> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Movimentacao> buscarTodos() {
		String hql = "FROM Movimentacao m";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

	@Override
	public void deletar(Movimentacao obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void editar(Movimentacao obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void persistir(Movimentacao obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

}
