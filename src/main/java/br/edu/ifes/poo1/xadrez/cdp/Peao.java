package br.edu.ifes.poo1.xadrez.cdp;

import br.edu.ifes.poo1.xadrez.cdp.Cor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Peao extends Peca implements Serializable{
	
	public Peao(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Peao);
		this.setCor(cor);
	}

	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		//testa se a peca eh branca
		if(this.getCor() == Cor.Branco)
		{
			//testa se a posicao a frente esta vazia
			Posicao posicaoFrente = new Posicao(linhaAtual+1, colunaAtual);	
			
			if(tabuleiro.getCasa(posicaoFrente).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFrente);
			}
			
			//teste se a posicao linha+1 e linha+2 estão vazias
			Posicao posicao2Frente = new Posicao(linhaAtual+2, colunaAtual);
			
			if((!this.getMoveu()) && (tabuleiro.getCasa(posicaoFrente).getOcupada() == false) && (tabuleiro.getCasa(posicao2Frente).getOcupada() == false))
			{
				//adiciona linha+2 
				destinosPossiveis.add(posicao2Frente);
			}	
			
			//testa se pode comer a esquerda
			if(this.getPosicao().getColuna() > 1)
			{
				Posicao posicaoComerEsquerda = new Posicao(linhaAtual+1, colunaAtual-1);	
				
				if(tabuleiro.getCasa(posicaoComerEsquerda).getOcupada() == true)
				{
					if(tabuleiro.getCasa(posicaoComerEsquerda).getCor() == Cor.Preto)
					{
						//adiciona a lista de destinos
						destinosPossiveis.add(posicaoComerEsquerda);
					}
				}
			}
			
			//testa se pode comer a direita
			if(this.getPosicao().getColuna() < 8)
			{
				Posicao posicaoComerDireita = new Posicao(linhaAtual+1, colunaAtual+1);	
				
				if(tabuleiro.getCasa(posicaoComerDireita).getOcupada() == true && tabuleiro.getCasa(posicaoComerDireita).getCor() == Cor.Preto)
				{
					destinosPossiveis.add(posicaoComerDireita);
				}
			}
		}
		//testa se as peca for preta 
		else
		{
			//testa se a posicao a frente esta vazia
			Posicao posicaoFrente = new Posicao(linhaAtual-1, colunaAtual);	
			
			if(tabuleiro.getCasa(posicaoFrente).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFrente);
			}
			
			//teste se a posicao linha+1 e linha+2 estão vazias
			Posicao posicao2Frente = new Posicao(linhaAtual-2, colunaAtual);
			
			if((tabuleiro.getCasa(posicaoFrente).getOcupada() == false) && (tabuleiro.getCasa(posicao2Frente).getOcupada() == false) && (!this.getMoveu()))
			{
				//adiciona linha+2 
				destinosPossiveis.add(posicao2Frente);
			}
			
			//testa se pode comer a esquerda
			if(this.getPosicao().getColuna() < 8)
			{
				Posicao posicaoComerEsquerda = new Posicao(linhaAtual-1, colunaAtual+1);	
				
				if(tabuleiro.getCasa(posicaoComerEsquerda).getOcupada() == true)
				{
					if(tabuleiro.getCasa(posicaoComerEsquerda).getCor() == Cor.Branco)
					{
						//adiciona a lista de destinos
						destinosPossiveis.add(posicaoComerEsquerda);
					}
				}
			}
			
			//testa se pode comer a direita
			if(this.getPosicao().getColuna() > 1)
			{
				Posicao posicaoComerDireita = new Posicao(linhaAtual-1, colunaAtual-1);	
				
				if(tabuleiro.getCasa(posicaoComerDireita).getOcupada() == true)
				{
					if(tabuleiro.getCasa(posicaoComerDireita).getCor() == Cor.Branco)
					{
						//adiciona a lista de destinos
						destinosPossiveis.add(posicaoComerDireita);
					}
				}
			}
		}
		
		return destinosPossiveis;
		
	}
	
	
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "p";
		return "P";
	}

}
