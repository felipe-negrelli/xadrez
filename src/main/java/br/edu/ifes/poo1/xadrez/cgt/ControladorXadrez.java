package br.edu.ifes.poo1.xadrez.cgt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cci.ControladorJogo;
import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Partida;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.Tabuleiro;
import br.edu.ifes.poo1.xadrez.cgd.Repositorio;
import br.edu.ifes.poo1.xadrez.exceptions.*;

public class ControladorXadrez implements Serializable {
	
	private static final long serialVersionUID = 709173488647234733L;
	ControladorJogo controladorJogo;
	List<Partida> listaPartidas = new ArrayList<Partida>();
	Partida partidaAtual;
	
	
	//List<DadosPartida> dadosPartidas = new ArrayList<DadosPartida>();
	
	
	public ControladorXadrez(ControladorJogo controladorJogo)
	{
		this.controladorJogo = controladorJogo;
	}
	
	public void inicializaNovaPartida()
	{
		partidaAtual = new Partida(this);
		partidaAtual.setHoraInicioAgora();
		this.listaPartidas.add(partidaAtual);
	}
	
	public void retomarPartida(int codigo)
	{
		partidaAtual = listaPartidas.get(codigo);
	}
	
	public List<Partida> getListaPartidas()
	{
		return this.listaPartidas;
	}
	
	public List<Partida> getListaPartidasAtivas()
	{
		List<Partida> listaAtivas = new ArrayList<Partida>();
		for (Partida atual : this.listaPartidas) 
		{
			if(atual.getEstadoPartida() == EstadoPartida.Normal || atual.getEstadoPartida() == EstadoPartida.Xeque)
			{
				listaAtivas.add(atual);
			}		
		}
		return this.listaPartidas;
	}
	
	public Tabuleiro getTabuleiro()
	{
		return this.partidaAtual.getTabuleiro();
	}
	
	public EstadoPartida getEstadoPartida()
	{
		return this.partidaAtual.getEstadoPartida();
	}
	
	public Jogador getJodadorDaVez()
	{
		return this.partidaAtual.getJogadorDaVez();
	}	
	
	public void salvarJogador(Jogador jogador)
	{
		if(jogador.getCor() == Cor.Branco)
			this.partidaAtual.setJogadorBranco(jogador);
		else
			this.partidaAtual.setJogadorPreto(jogador);
	}
	
	public void processarJogada(String jogada) throws SmallRockNotPossibleException, BigRockNotPossibleException, PlayNotPossibleException
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
			if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getOcupada())
			{
				//teste se peca de origem é do jogador 
				if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getCor() == getJodadorDaVez().getCor())
				{
					this.partidaAtual.processarMovimento(posicaoAtual, posicaoDestino);
				}
				else
				{
					throw new PlayNotPossibleException();
					
				}
			}
			else
			{
				controladorJogo.exibirErroJogada(jogada);
			}			
		}
		else if(jogada.equals("O-O"))
		{
			if(this.partidaAtual.getRockPequenoPossivel())
			{
				executarRockPequeno();
				
			}
			else
			{
				controladorJogo.exibirErroJogada(jogada);
			}
				
		}
		else if(jogada.equals("O-O-O"))
		{
			if(this.partidaAtual.getRockGrandePossivel())
			{
				executarRockGrande();
			}
			else
			{
				controladorJogo.exibirErroJogada(jogada);
			}
		}		
	}
	
	public int getPontosJogadorAtual()
	{
		return this.partidaAtual.getPontosJogadorAtual();
	}
	
	public void setErroLogico()
	{
		controladorJogo.exibirErroLogico();
	}
	
	public boolean getRockPequenoPossivel()
	{
		return this.partidaAtual.getRockPequenoPossivel();
	}
	
	public boolean getRockGrandePossivel()
	{
		return this.partidaAtual.getRockPequenoPossivel();
	}	
	
	public void executarRockPequeno()
	{
		this.partidaAtual.executarRockPequeno();
	}
	
	public void executarRockGrande()
	{
		this.partidaAtual.executarRockGrande();
	}
	
	public void salvarPartidas() throws Exception
	{
		Repositorio.salvarDados(this.listaPartidas);
	}
	
	public void lerPartidasSalvas() throws Exception
	{
		this.listaPartidas = Repositorio.lerDados();
	}
	

}
