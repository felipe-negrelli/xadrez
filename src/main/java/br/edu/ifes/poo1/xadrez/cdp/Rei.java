package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Rei  extends Peca{
	
	public Rei(Cor cor)
	{
		this.setCor(cor);
	}

	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "r";
		return "R";
	}
}
