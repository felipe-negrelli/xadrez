package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TesteBispo {
	private Tabuleiro tabuleiro;
	private Bispo bispo;	
	
	@Before
	public void before(){
		// Inicia um tabuleiro.
		tabuleiro = new Tabuleiro();

		// Inicia o bispo.
		bispo = new Bispo(Cor.Branco);

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.getCasa(new Posicao(4, 4)).setPeca(bispo);
		tabuleiro.getCasa(new Posicao(6, 4)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(8, 8)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(1, 1)).setPeca(new Peao(Cor.Preto));
	
	}
	
	@Test
	public void podeMovimentar() {
		
		List<Posicao> lista = this.bispo.getMovimentosPossiveis(this.tabuleiro);
		
		Assert.assertTrue(lista.contains(new Posicao(1,1)));
		Assert.assertTrue(lista.contains(new Posicao(2,2)));
		Assert.assertTrue(lista.contains(new Posicao(3,3)));
		Assert.assertTrue(lista.contains(new Posicao(4,4)));
		Assert.assertTrue(lista.contains(new Posicao(5,5)));
		Assert.assertTrue(lista.contains(new Posicao(6,6)));
		Assert.assertTrue(lista.contains(new Posicao(7,7)));
		Assert.assertTrue(lista.contains(new Posicao(8,8)));
		Assert.assertTrue(lista.contains(new Posicao(6,4)));
		Assert.assertTrue(lista.contains(new Posicao(4,6)));
		Assert.assertTrue(lista.contains(new Posicao(3,7)));
		Assert.assertTrue(lista.contains(new Posicao(2,8)));
	
	}

}
