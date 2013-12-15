package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public class Torre extends Peca {
	
	public Torre(Cor cor)
	{
		super();
		this.setTipoPeca(TipoPeca.Torre);
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
			return "t";
		return "T";
	}
}
