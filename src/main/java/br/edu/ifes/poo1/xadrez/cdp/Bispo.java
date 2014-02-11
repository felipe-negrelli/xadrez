package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sun.jndi.toolkit.dir.ContainmentFilter;

public class Bispo extends Peca implements Serializable {
	
	public Bispo(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Bispo);
		this.setCor(cor);
	}

	@Override
	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		//testa as posições a frente e direita em relação ao branco e atras e esquerda em relacao ao preto
		int contadorColuna = colunaAtual+1;
		for(int contadorLinha=linhaAtual+1;contadorLinha<=8 && contadorColuna<=8;contadorLinha++)
		{
			Posicao posicaoAtual = new Posicao(contadorLinha, contadorColuna);			
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
			contadorColuna++;			
		}
		
		//testa as posições a atras e direita em relação ao branco e a frente e esquera em relacao ao preto
		contadorColuna = colunaAtual+1;
		for(int contadorLinha=linhaAtual-1;contadorLinha>=1 && contadorColuna<=8;contadorLinha--)
		{
			Posicao posicaoAtual = new Posicao(contadorLinha, contadorColuna);			
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
			contadorColuna++;
		}
		
		//testa as posições a esquerda e atras em relação ao branco e a direita e frente em relacao ao preto
		contadorColuna = colunaAtual-1;
		for(int contadorLinha=linhaAtual-1;contadorLinha>=1 && contadorColuna>=1;contadorLinha--)
		{
			Posicao posicaoAtual = new Posicao(contadorLinha, contadorColuna);			
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
			contadorColuna--;
		}
		
		//testa as posições a frente e esquerda em relação ao branco
		contadorColuna = colunaAtual-1;
		for(int contadorLinha=linhaAtual+1;contadorLinha<=8 && contadorColuna>=1;contadorLinha++)
		{
			Posicao posicaoAtual = new Posicao(contadorLinha, contadorColuna);			
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
			contadorColuna--;
		}
			
			
		return destinosPossiveis;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "b";
		return "B";
	}
}
