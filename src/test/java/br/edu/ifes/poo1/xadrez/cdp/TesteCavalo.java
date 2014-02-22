package br.edu.ifes.poo1.xadrez.cdp;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.edu.ifes.poo1.xadrez.cdp.Cavalo;

public class TesteCavalo {
	private Tabuleiro tabuleiro;
	private Cavalo cavalo;	
	
	
	@Before
	public void before(){
		// Inicia um tabuleiro.
		tabuleiro = new Tabuleiro();

		// Inicia o bispo.
		cavalo = new Cavalo(Cor.Branco);

		// Coloca peças inimigas no tabuleiro.
		tabuleiro.getCasa(new Posicao(4, 4)).setPeca(cavalo);
		tabuleiro.getCasa(new Posicao(6, 3)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(8, 8)).setPeca(new Peao(Cor.Preto));
		tabuleiro.getCasa(new Posicao(1, 1)).setPeca(new Peao(Cor.Preto));
	
	}
	
	@Test
	public void podeMovimentar() {
		
		List<Posicao> lista = this.cavalo.getMovimentosPossiveis(this.tabuleiro);
		
		Assert.assertTrue(existe(lista,new Posicao(2,5)));
		Assert.assertTrue(existe(lista,new Posicao(3,6)));
		Assert.assertTrue(existe(lista,new Posicao(5,6)));
		Assert.assertTrue(existe(lista,new Posicao(6,5)));		
		Assert.assertTrue(existe(lista,new Posicao(6,3)));
		Assert.assertTrue(existe(lista,new Posicao(5,2)));
		Assert.assertTrue(existe(lista,new Posicao(3,2)));
		Assert.assertTrue(existe(lista,new Posicao(2,3)));
	
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
