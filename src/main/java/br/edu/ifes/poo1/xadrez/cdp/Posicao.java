package br.edu.ifes.poo1.xadrez.cdp;

public class Posicao {

	public Posicao(int linha, int coluna)
	{
		this.coluna = coluna;
		this.linha = linha;
	}
	
	private int coluna, linha;

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
}
