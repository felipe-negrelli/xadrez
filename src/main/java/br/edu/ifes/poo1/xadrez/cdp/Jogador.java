package br.edu.ifes.poo1.xadrez.cdp;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
	
	private String nome;
	private TipoJogador tipoJogador;
	private Cor cor;
	private List<PecaXadrez> pecasCapturadas = new ArrayList<PecaXadrez>();
	private int pontos;
	
	public Jogador(Cor cor)
	{
		this.cor = cor;
	}
	
	public Cor getCor() {
		return cor;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	
	public void adicionaPontos(int pontos)
	{
		this.pontos += pontos;
	}

	public List<PecaXadrez> getPecasCapturadas() {
		return pecasCapturadas;
	}	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoJogador getTipoJogador() {
		return tipoJogador;
	}

	public void setTipoJogador(TipoJogador tipoJogador) {
		this.tipoJogador = tipoJogador;
	}
	
}
