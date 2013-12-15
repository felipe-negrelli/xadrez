package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Rainha  extends Peca{
	
	public Rainha(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Rainha);
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
			return "d";
		return "D";
	}
}
