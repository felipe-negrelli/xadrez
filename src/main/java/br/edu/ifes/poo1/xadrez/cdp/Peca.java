package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public abstract class Peca implements PecaXadrez {
	
	private Cor cor;
	private int quantMovimentos;
	private TipoPeca tipoPeca;
	private Posicao posicao;
	
	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public TipoPeca getTipoPeca() {
		return tipoPeca;
	}

	public void setTipoPeca(TipoPeca tipoPeca) {
		this.tipoPeca = tipoPeca;
	}

	public Cor getCor()
	{
		if(this.cor == Cor.Branco)
			return Cor.Branco;
		return Cor.Preto;
	}
	
	public void setCor(Cor cor)
	{
		this.cor = cor;
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
	
	public abstract List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro);

}
