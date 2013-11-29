package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public abstract class Peca implements PecaXadrez {
	
	private boolean ehBranco;
	private int quantMovimentos;
	
	public boolean getBranco()
	{
		if(ehBranco)
			return true;
		return false;
	}
	
	public boolean getMoveu()
	{
		if(this.quantMovimentos > 0)
			return true;
		return false;
	}
	
	public int getQuantMovimentos()
	{
		return this.quantMovimentos;
	}
	
	public abstract List<CasaTabuleiro> getMovimentosPossiveis();
	
	public abstract void moverPeca(CasaTabuleiro casa_tabuleiro);

}
