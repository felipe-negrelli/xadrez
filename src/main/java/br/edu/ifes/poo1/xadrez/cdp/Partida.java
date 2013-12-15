package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Partida {
	
	Tabuleiro tabuleiro = new Tabuleiro();
	EstadoPartida estadoPartida;
	Jogador jogadorBranco;
	Jogador jogadorPreto ;
	Cor vezJogada = Cor.Branco;
	
	public Partida()
	{
		estadoPartida = EstadoPartida.Normal;
	}
	
	public Jogador getJogadorBranco() {
		return jogadorBranco;
	}

	public void setJogadorBranco(Jogador jogadorBranco) {
		this.jogadorBranco = jogadorBranco;
	}

	public Jogador getJogadorPreto() {
		return jogadorPreto;
	}

	public void setJogadorPreto(Jogador jogadorPreto) {
		this.jogadorPreto = jogadorPreto;
	}

	public Jogador getJogadorDaVez() {
		if(vezJogada == Cor.Branco)
			return this.jogadorBranco;
		return this.jogadorPreto;
	}

	public void setVezJogada(Cor vezJogada) {
		this.vezJogada = vezJogada;
	}

	public void setEstadoPartida(EstadoPartida estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public Tabuleiro getTabuleiro()
	{
		return this.tabuleiro;
	}
	
	public EstadoPartida getEstadoPartida()
	{
		return this.estadoPartida;
	}
	
	public void setNomeJogadorBranco(String nome)
	{
		this.jogadorBranco.setNome(nome);
	}
	
	public void processarMovimento(Posicao posicaoOrigem, Posicao posicaoDestino)
	{
		//testa se a posicao destino esta entre as possiveis		
		List<Posicao> posicoesPossiveis = this.getTabuleiro().getCasa(posicaoOrigem).getPeca().getMovimentosPossiveis(this.tabuleiro);
		
		if(existe(posicoesPossiveis, posicaoDestino))
		{
			moverPeca(posicaoOrigem, posicaoDestino);			
		}
	}
	
	public void moverPeca(Posicao posicaoOrigem, Posicao posicaoDestino)
	{
		//move peca
		PecaXadrez pecaOrigem = this.getTabuleiro().getCasa(posicaoOrigem).getPeca();
		
		//seta variavel peca no lugar de origem com a nova
		this.tabuleiro.getCasa(posicaoDestino).setPeca(pecaOrigem);
		
		//limpa peca
		this.tabuleiro.getCasa(posicaoOrigem).limpaPeca();
		
		alteraVez();
	}
	
	public boolean existe(List<Posicao> posicoes, Posicao posicaoDestino)
	{
		boolean achou = false;
		for(int contador=0;contador<posicoes.size();contador++)
		{
			if(posicoes.get(contador).getLinha()==posicaoDestino.getLinha() && (posicoes.get(contador).getColuna()==posicaoDestino.getColuna()))
			{
				achou = true;
			}
		}
		return achou;
	}
	
	public void alteraVez()
	{
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			setVezJogada(Cor.Preto);
		}
		else
		{
			setVezJogada(Cor.Branco);
		}
	}
}
