package br.edu.ifes.poo1.xadrez.cdp;

import java.util.ArrayList;
import java.util.List;

public class Rei  extends Peca{
	
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
			//testa se a posicao a frente esta vazia
			Posicao posicaoBrancoFrente = new Posicao(linhaAtual+1, colunaAtual);
			
			if((tabuleiro.getCasa(posicaoBrancoFrente).getOcupada() == false) || ((tabuleiro.getCasa(posicaoBrancoFrente).getOcupada() == false) && (tabuleiro.getCasa(posicaoBrancoFrente).getCor() == this.get)))
			{
				//adiciona a lista de destinos
				destinosPossiveis.add(posicaoBrancoFrente);
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
