package br.edu.ifes.poo1.xadrez.cdp;

public class CasaTabuleiro{
	
	private Posicao posicao;
	private PecaXadrez pecaAtual;
	
	public CasaTabuleiro(Posicao posicao)
	{
		this.posicao = posicao;
	}			
	
	public void setPeca(PecaXadrez peca)
	{
		this.pecaAtual = peca;
	}
	
	public PecaXadrez getPeca()
	{
		return this.pecaAtual;
	}
	
	public void limpaPeca()
	{
		this.pecaAtual = null;
	}
	
	public boolean getOcupada()
	{
		if(pecaAtual == null)
			return false;
		return true;
	}
	
	public Cor getCor()
	{
		if(getOcupada())
		{
			return this.pecaAtual.getCor();
		}
		return null;			
	}
}
