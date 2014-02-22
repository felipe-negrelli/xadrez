package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Jogador implements Serializable{
	
	private static final long serialVersionUID = 8165478622877966954L;
	private String nome;
	private TipoJogador tipoJogador;
	private Cor cor;
	private List<Peca> pecasCapturadas = new ArrayList<Peca>();
	private int pontos;
	
	public void addPecaCapturada(PecaXadrez peca)
	{
		this.pecasCapturadas.add((Peca)peca);
	}
	
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

	public List<Peca> getPecasCapturadas() {
		
		Collections.sort(pecasCapturadas);
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
	
	public Jogada getJogadaAutomatica(Tabuleiro tabuleiro)
	{
		Jogada jogadaResultante;
		
		List<Jogada> listaJogadas = new ArrayList<Jogada>();		
		List<PecaXadrez> pecasAtivas = getPecasEmJogo(tabuleiro);
		
		for(int contador=0;contador<pecasAtivas.size();contador++)	
		{
			List<Jogada> listaJogasPecaAtual = getJogadasPossiveisPeca(pecasAtivas.get(contador), tabuleiro);
			listaJogadas.addAll(listaJogasPecaAtual);
		}
		
		int tamLista = listaJogadas.size();		
		Random random = new Random();
		int valorRandomico = random.nextInt(tamLista);
		
		jogadaResultante = listaJogadas.get(valorRandomico);
		return jogadaResultante;
	}
	
	private List<PecaXadrez> getPecasEmJogo(Tabuleiro tabuleiro)
	{
		List<PecaXadrez> pecasAtivas = new ArrayList<PecaXadrez>();
		
		for(int contadorLinha=1;contadorLinha<=8;contadorLinha++)	
		{
			for(int contadorColuna=1;contadorColuna<=8;contadorColuna++)
			{
				CasaTabuleiro casa = tabuleiro.getCasa(new Posicao(contadorLinha, contadorColuna));
				if(casa.getOcupada() && casa.getCor() == this.cor)
				{
					pecasAtivas.add(casa.getPeca());
				}
			}
		}	
		
		return pecasAtivas;		
	}
	
	private List<Jogada> getJogadasPossiveisPeca(PecaXadrez pecaAtual, Tabuleiro tabuleiro)
	{
		List<Jogada> listaJogadasFinal = new ArrayList<Jogada>();
		List<Posicao> posicoesPossiveis = pecaAtual.getMovimentosPossiveis(tabuleiro);
		
		for(int contador=0; contador<posicoesPossiveis.size();contador++)
		{
			if(tabuleiro.getCasa(posicoesPossiveis.get(contador)).getOcupada())
			{
				listaJogadasFinal.add(new Jogada(pecaAtual.getPosicao(), posicoesPossiveis.get(contador), TipoJogada.Captura));
			}
			else
			{
				listaJogadasFinal.add(new Jogada(pecaAtual.getPosicao(), posicoesPossiveis.get(contador), TipoJogada.Movimento));
			}
		}
		
		return listaJogadasFinal;		
	}
	
	
}
