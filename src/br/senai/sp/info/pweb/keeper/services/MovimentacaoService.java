package br.senai.sp.info.pweb.keeper.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.pweb.keeper.dao.ItemDAO;
import br.senai.sp.info.pweb.keeper.dao.MovimentacaoDAO;
import br.senai.sp.info.pweb.keeper.exceptions.NadaEncontradoException;
import br.senai.sp.info.pweb.keeper.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.keeper.models.Item;
import br.senai.sp.info.pweb.keeper.models.Movimentacao;
import br.senai.sp.info.pweb.keeper.models.Usuario;

@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoDAO movimentacaoDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	public List<Movimentacao> buscarTodosMovimentacaos() {
		return movimentacaoDAO.buscarTodos();
	}
	
	public Movimentacao buscarMovimentacao(Long id) throws NadaEncontradoException {
		Movimentacao movimentacaoBuscada = movimentacaoDAO.buscar(id);
		
		if (movimentacaoBuscada == null) {
			throw new NadaEncontradoException();
		}
		
		return movimentacaoBuscada;
	}
	
	public Movimentacao cadastrar(Long id, Movimentacao movimentacao,  
			BindingResult bindingResult) throws ValidacaoException {
		
		if (bindingResult.hasErrors()) {			
			throw new ValidacaoException();
		}
				
		Item item = itemDAO.buscar(id);

		movimentacao.setItem(item);
		movimentacao.setOrigem(item.getAmbiente());
		movimentacao.setDataMovimentacao(new Date());	
		Usuario usuarioBuscado = (Usuario) SecurityContextHolder.getContext().getAuthentication();
		movimentacao.setTransportador(usuarioBuscado);
				
		item.setData(new Date());
		item.setAmbiente(movimentacao.getDestino());
		
		if (movimentacao.getOrigem().getId() == movimentacao.getDestino().getId()) {
			throw new ValidacaoException();
		}
		
		itemDAO.editar(item);
		movimentacaoDAO.persistir(movimentacao);
		
		return movimentacao;
	}
}
