package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteTorre {
	private Tabuleiro tabuleiro;
	private Torre torre;	
	
	@Before
	public void before(){
		// Inicia um tabuleiro.
		tabuleiro = new Tabuleiro();

		// Inicia o bispo.
		torre = new Torre(Cor.Branco);

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.getCasa(new Posicao(4, 4)).setPeca(torre);
		tabuleiro.getCasa(new Posicao(6, 4)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(8, 8)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(1, 1)).setPeca(new Peao(Cor.Preto));
	
	}
	
	@Test
	public void podeMovimentar() {
		
		List<Posicao> lista = this.torre.getMovimentosPossiveis(this.tabuleiro);
		
		Assert.assertTrue(existe(lista,new Posicao(4,1)));
		Assert.assertTrue(existe(lista,new Posicao(4,2)));
		Assert.assertTrue(existe(lista,new Posicao(4,3)));
		Assert.assertTrue(existe(lista,new Posicao(4,5)));
		Assert.assertTrue(existe(lista,new Posicao(4,6)));
		Assert.assertTrue(existe(lista,new Posicao(4,7)));
		Assert.assertTrue(existe(lista,new Posicao(4,8)));		
		Assert.assertTrue(existe(lista,new Posicao(1,4)));
		Assert.assertTrue(existe(lista,new Posicao(2,4)));
		Assert.assertTrue(existe(lista,new Posicao(3,4)));
		Assert.assertTrue(existe(lista,new Posicao(5,4)));
		Assert.assertTrue(existe(lista,new Posicao(6,4)));
	
	}
	
	public boolean existe(List<Posicao> posicoes, Posicao posicaoDestino)
	{
		boolean achou = false;
		for(int contador=0;contador<posicoes.size();contador++)
		{
			if(posicoes.get(contador).getLinha()==posicaoDestino.getLinha() && (posicoes.get(contador).getColuna()==posicaoDestino.getColuna()))
			{
				achou = true;
			}
		}
		return achou;
	}

}
