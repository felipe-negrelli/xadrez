package br.edu.ifes.poo1.xadrez.cdp;

public class CasaTabuleiro {
	
	private int coluna, linha;
	private PecaXadrez pecaAtual;
	
	public CasaTabuleiro(int coluna, int linha)
	{
		this.coluna = coluna;
		this.linha = linha;
	}			
	
	public void setPeca(PecaXadrez peca)
	{
		this.pecaAtual = peca;
	}
	
	public PecaXadrez getPeca()
	{
		return this.pecaAtual;
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
