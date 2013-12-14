package br.edu.ifes.poo1.xadrez.cdp;

import br.edu.ifes.poo1.xadrez.cdp.CasaTabuleiro;

public class Tabuleiro {
	
	private CasaTabuleiro[][] casas = new CasaTabuleiro[8][8];
	
	public Tabuleiro()
	{
		inicializaTabuleiro();
		criaTabuleiro();
	}
	
	public void criaTabuleiro()
	{
		casas[0][0].setPeca(new Torre(Cor.Preto));		
		casas[0][1].setPeca(new Cavalo(Cor.Preto));
		casas[0][2].setPeca(new Bispo(Cor.Preto));
		casas[0][3].setPeca(new Rainha(Cor.Preto));
		casas[0][4].setPeca(new Rei(Cor.Preto));
		casas[0][5].setPeca(new Bispo(Cor.Preto));
		casas[0][6].setPeca(new Cavalo(Cor.Preto));
		casas[0][7].setPeca(new Torre(Cor.Preto));
		
		//preenche os peões brancos
		for(int contador=0;contador<8;contador++)
		{
			casas[1][contador].setPeca(new Peao(Cor.Preto));
		}
		
		//preenche os peões pretos
		for(int contador=0;contador<8;contador++)
		{
			casas[6][contador].setPeca(new Peao(Cor.Branco));
		}
		
		casas[7][0].setPeca(new Torre(Cor.Branco));
		casas[7][1].setPeca(new Cavalo(Cor.Branco));
		casas[7][2].setPeca(new Bispo(Cor.Branco));
		casas[7][3].setPeca(new Rainha(Cor.Branco));
		casas[7][4].setPeca(new Rei(Cor.Branco));
		casas[7][5].setPeca(new Bispo(Cor.Branco));
		casas[7][6].setPeca(new Cavalo(Cor.Branco));
		casas[7][7].setPeca(new Torre(Cor.Branco));		
		
	}
	
	public void inicializaTabuleiro()
	{
		for(int contadorLinha=0;contadorLinha<8;contadorLinha++)	
		{
			for(int contadorColuna=0;contadorColuna<8;contadorColuna++)
			{	
				casas[contadorLinha][contadorColuna] = new CasaTabuleiro(new Posicao(8-contadorLinha,contadorColuna+1));			
			}
		}
	}	
	
	public CasaTabuleiro getCasa(Posicao posicao)
	{
		CasaTabuleiro casa;
		
		casa = casas[posicao.getLinha()-1][posicao.getColuna()-1];	
		
		return casa;	
	}
	
	public CasaTabuleiro getCasa(int linha, int coluna)
	{
		CasaTabuleiro casa;
		
		casa = casas[linha-1][coluna-1];	
		
		return casa;	
	}
}
