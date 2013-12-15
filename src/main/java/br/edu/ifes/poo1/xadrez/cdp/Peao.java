package br.edu.ifes.poo1.xadrez.cdp;

import br.edu.ifes.poo1.xadrez.cdp.Cor;

import java.util.ArrayList;
import java.util.List;

public class Peao extends Peca{
	
	public Peao(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Peao);
		this.setCor(cor);
	}

	@Override
	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		
		int linhaAtual = this.getPosicao().getLinha();
		int colunaAtual = this.getPosicao().getColuna();
		List<Posicao> destinosPossiveis = new ArrayList<Posicao>();
		
		//testa se a posicao a frente esta vazia
		Posicao posicaoFrente = new Posicao(linhaAtual+1, colunaAtual);
		if(tabuleiro.getCasa(posicaoFrente).getOcupada() == false)
		{
			//adiciona a lista de destinos
			destinosPossiveis.add(posicaoFrente);
		}
		
		//teste se a posicao linha+1 e linha+2 estão vazias
		Posicao posicao2Frente = new Posicao(linhaAtual+2, colunaAtual);
		if((tabuleiro.getCasa(posicaoFrente).getOcupada() == false) && (tabuleiro.getCasa(posicao2Frente).getOcupada() == false))
		{
			//adiciona linha+2 
			destinosPossiveis.add(posicao2Frente);
		}
		
		
		
		////////////////////////////////////implementar o resto
		
		return destinosPossiveis;
		
	}
	
	
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "p";
		return "P";
	}

}
