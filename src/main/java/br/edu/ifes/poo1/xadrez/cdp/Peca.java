package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public abstract class Peca implements PecaXadrez {
	
	private Cor cor;
	private int quantMovimentos;
	
	public Cor getCor()
	{
		if(this.cor == Cor.Branco)
			return Cor.Branco;
		return Cor.Preto;
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
