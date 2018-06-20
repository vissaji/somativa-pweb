package br.senai.sp.info.pweb.keeper.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.keeper.dao.UsuarioDAO;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Repository
@Transactional
public class UsuarioJPA implements UsuarioDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Usuario buscar(Long id) {
		String hql = "FROM Usuario u WHERE u.id = :id";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		List<Usuario> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Usuario> buscarTodos() {
		String hql = "FROM Usuario u";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Override
	public void deletar(Usuario obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public void editar(Usuario obj) {
		sessionFactory.getCurrentSession().update(obj);
	}
	
	@Override 
	public void persistir(Usuario obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public Usuario buscarPorEmail(String email) {
		String hql = "FROM Usuario u WHERE u.email = :email";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		
		List<Usuario> resultados = query.list();
		
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Usuario buscarPorEmailSenha(String email, String senha) {
		String hql = "FROM Usuario u WHERE u.email = :email AND u.senha = :senha";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
			
		List<Usuario> resultados = query.list();
				
		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

}
