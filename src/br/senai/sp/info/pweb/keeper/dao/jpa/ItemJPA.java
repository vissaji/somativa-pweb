package br.senai.sp.info.pweb.keeper.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.keeper.dao.ItemDAO;
import br.senai.sp.info.pweb.keeper.models.Item;

@Repository
@Transactional

public class ItemJPA implements ItemDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Item buscar(Long id) {
		String hql = "FROM Item i WHERE i.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Item> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Item> buscarTodos() {
		String hql = "FROM Item i";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
		return query.list();
	}

	@Override
	public void deletar(Item obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void editar(Item obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void persistir(Item obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	
	
}
