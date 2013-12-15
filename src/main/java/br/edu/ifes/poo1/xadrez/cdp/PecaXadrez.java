package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public interface PecaXadrez {

	public Cor getCor();
	
	public Posicao getPosicao();

	public void setPosicao(Posicao posicao);
	
	public boolean getMoveu();
	
	public int getQuantMovimentos();
	
	public List<Posicao> getMovimentosPossiveis(Tabuleiro tabuleiro);
	
}
