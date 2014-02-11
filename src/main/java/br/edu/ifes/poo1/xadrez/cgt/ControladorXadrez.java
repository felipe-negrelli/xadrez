package br.edu.ifes.poo1.xadrez.cgt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cci.ControladorJogo;
import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.DadosPartida;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Partida;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.Tabuleiro;

public class ControladorXadrez implements Serializable {
	
	Partida partida = new Partida(this);	
	List<DadosPartida> dadosPartidas = new ArrayList<DadosPartida>();
	ControladorJogo controladorJogo;
	
	public ControladorXadrez(ControladorJogo controladorJogo)
	{
		this.controladorJogo = controladorJogo;
	}
	
	public Tabuleiro getTabuleiro()
	{
		return this.partida.getTabuleiro();
	}
	
	public EstadoPartida getEstadoPartida()
	{
		return this.partida.getEstadoPartida();
	}
	
	public Jogador getJodadorDaVez()
	{
		return this.partida.getJogadorDaVez();
	}	
	
	public void salvaJogador(Jogador jogador)
	{
		if(jogador.getCor() == Cor.Branco)
			this.partida.setJogadorBranco(jogador);
		else
			this.partida.setJogadorPreto(jogador);
	}
	
	public void processarJogada(String jogada)
	{		
		if(jogada.length() == 4)
		{
			String teste = jogada.substring(0,1);
			int linhaOrigem = Integer.parseInt(teste);
			int colunaOrigem = Integer.parseInt(jogada.substring(1,2));
			Posicao posicaoAtual = new Posicao(linhaOrigem, colunaOrigem);
			
			int linhaDestino = Integer.parseInt(jogada.substring(2,3));
			int colunaDestino = Integer.parseInt(jogada.substring(3,4));
			Posicao posicaoDestino = new Posicao(linhaDestino, colunaDestino);
			
			//testa se casa de origem esta ocupada
			if(partida.getTabuleiro().getCasa(posicaoAtual).getOcupada())
			{
				//teste se peca de origem é do jogador 
				if(partida.getTabuleiro().getCasa(posicaoAtual).getCor() == getJodadorDaVez().getCor())
				{
					this.partida.processarMovimento(posicaoAtual, posicaoDestino);
				}
				else
				{
					controladorJogo.exibirErroJogada(jogada);
				}
			}
			else
			{
				controladorJogo.exibirErroJogada(jogada);
			}			
		}
		else if(jogada.length() == 5)
		{
			
		}
		
	}
	
	public int getPontosJogadorAtual()
	{
		return this.partida.getPontosJogadorAtual();
	}
	
	public void setErroLogico()
	{
		controladorJogo.exibirErroLogico();
	}
	

}
