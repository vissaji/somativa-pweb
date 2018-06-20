package br.senai.sp.info.pweb.keeper.dao;

import java.util.List;

public interface DAO<T> {

	public T buscar(Long id);
	
	public List<T> buscarTodos();
	
	public void deletar(T obj);

	public void editar(T obj);
	
	public void persistir(T obj);
}
