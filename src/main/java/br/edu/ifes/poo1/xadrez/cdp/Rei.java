package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rei  extends Peca implements Serializable{
	
	public Rei(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Rei);
		this.setCor(cor);
	}

	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		
		//Cor cor = 
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		if(this.getPosicao().getLinha() < 8)
		{
			//Todas as variaveis de posições estão nomeadas com relação ao Branco. Ex.: Frente: Seria coluna+1
			
			
			//testa se a posicao a frente esta vazia
			Posicao posicaoFrente = new Posicao(linhaAtual+1, colunaAtual);			
			if((tabuleiro.getCasa(posicaoFrente).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFrente).getOcupada() == true) && (tabuleiro.getCasa(posicaoFrente).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFrente);
			}
			
			//testa se existe uma coluna a esquerda
			if(this.getPosicao().getColuna() > 1)
			{			
				//testa se a posicao a frente e esquerda esta vazia
				Posicao posicaoFrenteEsquerda = new Posicao(linhaAtual+1, colunaAtual-1);			
				if((tabuleiro.getCasa(posicaoFrenteEsquerda).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFrenteEsquerda).getOcupada() == true) && (tabuleiro.getCasa(posicaoFrenteEsquerda).getCor() != this.getCor())))
				{
					//adiciona a lista de destinos
					destinosPossiveis.add(posicaoFrenteEsquerda);
				}
			}
			
			//testa se existe uma coluna a direita
			if(this.getPosicao().getColuna() < 8)
			{				
				//testa se a posicao a frente e direita esta vazia
				Posicao posicaoFrenteDireita = new Posicao(linhaAtual+1, colunaAtual+1);			
				if((tabuleiro.getCasa(posicaoFrenteDireita).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFrenteDireita).getOcupada() == true) && (tabuleiro.getCasa(posicaoFrenteDireita).getCor() != this.getCor())))
				{
					//adiciona a lista de destinos
					destinosPossiveis.add(posicaoFrenteDireita);
				}
			}
		}
		
		//testa se existe uma coluna a esquerda
		if(this.getPosicao().getColuna() > 1)
		{	
			//testa se a posição a esquerda esta vazia
			Posicao posicaoEsquerda = new Posicao(linhaAtual, colunaAtual-1);			
			if((tabuleiro.getCasa(posicaoEsquerda).getOcupada() == false) || ((tabuleiro.getCasa(posicaoEsquerda).getOcupada() == true) && (tabuleiro.getCasa(posicaoEsquerda).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoEsquerda);
			}
			
		}
		
		//testa se existe uma coluna a direita
		if(this.getPosicao().getColuna() < 8)
		{	
			//testa se a posição a direita esta vazia
			Posicao posicaoDireita = new Posicao(linhaAtual, colunaAtual+1);			
			if((tabuleiro.getCasa(posicaoDireita).getOcupada() == false) || ((tabuleiro.getCasa(posicaoDireita).getOcupada() == true) && (tabuleiro.getCasa(posicaoDireita).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoDireita);
			}		
		}
		
		//Testa se existe linha atras
		if(this.getPosicao().getLinha() > 1)
		{
			//testa se a posicao a atras esta vazia
			Posicao posicaoAtras = new Posicao(linhaAtual-1, colunaAtual);			
			if((tabuleiro.getCasa(posicaoAtras).getOcupada() == false) || ((tabuleiro.getCasa(posicaoAtras).getOcupada() == true) && (tabuleiro.getCasa(posicaoAtras).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtras);
			}
			
			//testa se existe uma coluna a esquerda
			if(this.getPosicao().getColuna() > 1)
			{			
				//testa se a posicao a atras e esquerda esta vazia
				Posicao posicaoAtrasEsquerda = new Posicao(linhaAtual-1, colunaAtual-1);			
				if((tabuleiro.getCasa(posicaoAtrasEsquerda).getOcupada() == false) || ((tabuleiro.getCasa(posicaoAtrasEsquerda).getOcupada() == true) && (tabuleiro.getCasa(posicaoAtrasEsquerda).getCor() != this.getCor())))
				{
					//adiciona a lista de destinos
					destinosPossiveis.add(posicaoAtrasEsquerda);
				}
			}
			
			//testa se existe uma coluna a direita
			if(this.getPosicao().getColuna() < 8)
			{				
				//testa se a posicao atrás e direita esta vazia
				Posicao posicaoAtrasDireita = new Posicao(linhaAtual-1, colunaAtual+1);			
				if((tabuleiro.getCasa(posicaoAtrasDireita).getOcupada() == false) || ((tabuleiro.getCasa(posicaoAtrasDireita).getOcupada() == true) && (tabuleiro.getCasa(posicaoAtrasDireita).getCor() != this.getCor())))
				{
					//adiciona a lista de destinos
					destinosPossiveis.add(posicaoAtrasDireita);
				}
			}
		}
		
		return destinosPossiveis;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "r";
		return "R";
	}
}
