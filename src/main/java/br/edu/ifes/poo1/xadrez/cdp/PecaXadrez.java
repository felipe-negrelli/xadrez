package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

public interface PecaXadrez {

	public boolean getBranco();
	
	public boolean getMoveu();
	
	public int getQuantMovimentos();
	
	public List<CasaTabuleiro> getMovimentosPossiveis();
	
	public void moverPeca(CasaTabuleiro casa_tabuleiro);	
	
}
