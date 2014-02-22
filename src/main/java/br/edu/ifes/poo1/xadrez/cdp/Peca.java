package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.util.List;

public abstract class Peca implements PecaXadrez,Serializable,Comparable<Peca> {
	
	private static final long serialVersionUID = -2043646471672017146L;
	private Cor cor;
	private int quantMovimentos = 0;
	private boolean movimentoDuplo = false;
	private TipoPeca tipoPeca;
	private Posicao posicao;
	private int valorEmPontos;
	
	public Peca()
	{
		
	}
	
	public int getValorEmPontos() {
		return valorEmPontos;
	}

	public void setValorEmPontos(int valorEmPontos) {
		this.valorEmPontos = valorEmPontos;
	}

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
	
	public void incrementaMovimento()
	{
		this.quantMovimentos++;
	}
	
	public boolean isMovimentoDuplo() {
		return movimentoDuplo;
	}

	public void setMovimentoDuplo(boolean movimentoDuplo) {
		this.movimentoDuplo = movimentoDuplo;
	}
	
	public abstract List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro);
	
	public int compareTo(Peca peca)
	{
		return (""+valorEmPontos).compareTo((peca.getValorEmPontos()+""));
	}

}
