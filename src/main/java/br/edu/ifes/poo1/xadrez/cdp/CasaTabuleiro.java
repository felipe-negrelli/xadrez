package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;

public class CasaTabuleiro implements Serializable{
	
	private static final long serialVersionUID = 5738557159947594849L;
	private Posicao posicao;
	private PecaXadrez pecaAtual;
	
	public CasaTabuleiro(Posicao posicao)
	{
		this.posicao = posicao;
	}			
	
	public void setPeca(PecaXadrez peca)
	{
		this.pecaAtual = peca;		
		this.pecaAtual.setPosicao(this.posicao);
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
