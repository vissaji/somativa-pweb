package br.senai.sp.info.pweb.keeper.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="movimetacao")

public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="item_id", nullable=false)
	private Item item;
	
	@ManyToOne
	@JoinColumn(name="origem_id", nullable=false)
	private Ambiente origem;
	
	@ManyToOne
	@JoinColumn(name="destino_id", nullable=false)
	private Ambiente destino;
	
	@Column(nullable=false)
	private Date dataMovimentacao;

	@ManyToOne
	@JoinColumn(name="transportador_id", nullable=false)
	private Usuario transportador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Ambiente getOrigem() {
		return origem;
	}

	public void setOrigem(Ambiente origem) {
		this.origem = origem;
	}

	public Ambiente getDestino() {
		return destino;
	}

	public void setDestino(Ambiente destino) {
		this.destino = destino;
	}

	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Usuario getTransportador() {
		return transportador;
	}

	public void setTransportador(Usuario transportador) {
		this.transportador = transportador;
	}
	
	
	
}
