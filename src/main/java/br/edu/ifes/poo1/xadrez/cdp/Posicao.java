package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;

import br.edu.ifes.poo1.xadrez.exceptions.PosicaoNotValidException;

public class Posicao implements Serializable{

	public Posicao(int coluna,int linha)
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
	
	public String toString()
	{
		return ""+this.coluna+this.linha;
	}
	
	public boolean validarPosicao()	throws PosicaoNotValidException
	{
		if(this.coluna>=1 && this.coluna<=8 && this.linha>=1 && this.linha<=8)
			return true;
		return false;
	}
}
