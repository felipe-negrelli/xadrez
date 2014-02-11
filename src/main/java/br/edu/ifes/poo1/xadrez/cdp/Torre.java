package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Torre extends Peca implements Serializable{
	
	public Torre(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Torre);
		this.setCor(cor);
	}

	@Override
	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		//testa as posições a frente em relação ao branco e atras em relacao ao preto
		for(int contador=linhaAtual+1;contador<=8;contador++)
		{
			Posicao posicaoAtual = new Posicao(contador, colunaAtual);			
			if(tabuleiro.getCasa(posicaoAtual).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);				
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() != this.getCor())
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);
				break;
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() == this.getCor())
			{
				break;
			}
		}
		
		//testa as posições a atras em relação ao branco e a frente em relacao ao preto
		for(int contador=linhaAtual-1;contador>=1;contador--)
		{
			Posicao posicaoAtual = new Posicao(contador, colunaAtual);			
			if(tabuleiro.getCasa(posicaoAtual).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);				
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() != this.getCor())
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);
				break;
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() == this.getCor())
			{
				break;
			}			
		}
		
		//testa as posições a esquerda em relação ao branco e a direita em relacao ao preto
		for(int contador=colunaAtual-1;contador>=1;contador--)
		{
			Posicao posicaoAtual = new Posicao(linhaAtual, contador);			
			if(tabuleiro.getCasa(posicaoAtual).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);				
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() != this.getCor())
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);
				break;
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() == this.getCor())
			{
				break;
			}			
		}
		
		//testa as posições a direita em relação ao branco e a esquerda em relacao ao preto
		for(int contador=colunaAtual+1;contador<=8;contador++)
		{
			Posicao posicaoAtual = new Posicao(linhaAtual, contador);			
			if(tabuleiro.getCasa(posicaoAtual).getOcupada() == false)
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);				
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() != this.getCor())
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoAtual);
				break;
			}
			else if(tabuleiro.getCasa(posicaoAtual).getOcupada() == true && tabuleiro.getCasa(posicaoAtual).getCor() == this.getCor())
			{
				break;
			}			
		}
		
		return destinosPossiveis;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "t";
		return "T";
	}
}
