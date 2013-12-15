package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Bispo extends Peca {
	
	public Bispo(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Bispo);
		this.setCor(cor);
	}

	@Override
	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString()
	{
		if(this.getCor() == Cor.Branco)
			return "b";
		return "B";
	}
}
