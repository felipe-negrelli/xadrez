package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Cavalo  extends Peca{
	
	public Cavalo(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Cavalo);
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
			return "c";
		return "C";
	}
}
