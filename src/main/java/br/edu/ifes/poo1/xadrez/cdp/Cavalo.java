package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cavalo  extends Peca implements Serializable{
	
	public Cavalo(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Cavalo);
		this.setCor(cor);
		this.setValorEmPontos(3);
	}

	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		Posicao posicaoFutura = new Posicao(colunaAtual-2,linhaAtual+1);	
		if(posicaoFutura.getLinha()<=8 && posicaoFutura.getColuna()>=1)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual-1,linhaAtual+2);	
		if(posicaoFutura.getLinha()<=8 && posicaoFutura.getColuna()>=1)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual+1,linhaAtual+2);	
		if(posicaoFutura.getLinha()<=8 && posicaoFutura.getColuna()<=8)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual+2,linhaAtual+1);	
		if(posicaoFutura.getLinha()<=8 && posicaoFutura.getColuna()<=8)	
		{
			//
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual-2,linhaAtual-1);	
		if(posicaoFutura.getLinha()>=1 && posicaoFutura.getColuna()>=1)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual-1,linhaAtual-2);	
		if(posicaoFutura.getLinha()>=1 && posicaoFutura.getColuna()>=1)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual+1,linhaAtual-2);	
		if(posicaoFutura.getLinha()>=1 && posicaoFutura.getColuna()<=8)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		posicaoFutura = new Posicao(colunaAtual+2,linhaAtual-1);	
		if(posicaoFutura.getLinha()>=1 && posicaoFutura.getColuna()<=8)	
		{
			if((tabuleiro.getCasa(posicaoFutura).getOcupada() == false) || ((tabuleiro.getCasa(posicaoFutura).getOcupada() == true) && (tabuleiro.getCasa(posicaoFutura).getCor() != this.getCor())))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoFutura);
			}
		}
		
		return destinosPossiveis;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "c";
		return "C";
	}
}
