package br.edu.ifes.poo1.xadrez.cdp;

public class CasaTabuleiro {
	
	private PecaXadrez pecaAtual;
	
	public void setPeca(PecaXadrez peca)
	{
		this.pecaAtual = peca;
	}
	
	public PecaXadrez getPeca()
	{
		return this.pecaAtual;
	}

}
