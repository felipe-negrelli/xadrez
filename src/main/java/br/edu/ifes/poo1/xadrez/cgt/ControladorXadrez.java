package br.edu.ifes.poo1.xadrez.cgt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cci.ControladorJogo;
import br.edu.ifes.poo1.xadrez.cdp.Bispo;
import br.edu.ifes.poo1.xadrez.cdp.Cavalo;
import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogada;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Partida;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.Rainha;
import br.edu.ifes.poo1.xadrez.cdp.Tabuleiro;
import br.edu.ifes.poo1.xadrez.cdp.TipoJogada;
import br.edu.ifes.poo1.xadrez.cdp.TipoPeca;
import br.edu.ifes.poo1.xadrez.cdp.Torre;
import br.edu.ifes.poo1.xadrez.cgd.Repositorio;
import br.edu.ifes.poo1.xadrez.exceptions.*;

public class ControladorXadrez implements Serializable {
	
	private static final long serialVersionUID = 709173488647234733L;
	ControladorJogo controladorJogo;
	List<Partida> listaPartidas = new ArrayList<Partida>();
	Partida partidaAtual;
	
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
	
	public void retomarPartida(Partida partida)
	{
		partidaAtual = partida;
		partidaAtual.setEstadoPartida(EstadoPartida.Normal);
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
			if(atual.getEstadoPartida() == EstadoPartida.Normal || atual.getEstadoPartida() == EstadoPartida.Xeque || atual.getEstadoPartida() == EstadoPartida.Pausada)
			{
				listaAtivas.add(atual);
			}		
		}
		return listaAtivas;
	}
	
	public List<Partida> getListaPartidasFinalizadas()
	{
		List<Partida> listaAtivas = new ArrayList<Partida>();
		for (Partida atual : this.listaPartidas) 
		{
			if(atual.getEstadoPartida() == EstadoPartida.Desistencia || atual.getEstadoPartida() == EstadoPartida.XequeMate || atual.getEstadoPartida() == EstadoPartida.Empate)
			{
				listaAtivas.add(atual);
			}		
		}
		return listaAtivas;
	}
	
	public Tabuleiro getTabuleiro()
	{
		return this.partidaAtual.getTabuleiro();
	}
	
	public EstadoPartida getEstadoPartida()
	{
		return this.partidaAtual.getEstadoPartida();
	}
	
	public void setEstadoPartida(EstadoPartida estadoPartida)
	{
		this.partidaAtual.setEstadoPartida(estadoPartida);
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
	
	public void desistir()
	{
		this.partidaAtual.setEstadoPartida(EstadoPartida.Desistencia);
		this.partidaAtual.setHoraFimAgora();
		
		if(this.partidaAtual.getJogadorDaVez().getCor() == Cor.Branco)
		{
			this.partidaAtual.setGanhador(Cor.Preto);
		}
		else
		{
			this.partidaAtual.setGanhador(Cor.Branco);
		}
	}
	
	public void empatar()
	{
		this.partidaAtual.setEstadoPartida(EstadoPartida.Empate);
		this.partidaAtual.setHoraFimAgora();
	}
	
	public void processarJogada(Jogada jogada) throws SmallRockNotPossibleException, BigRockNotPossibleException, PlayNotPossibleException
	{		
		if(jogada.getTipoJogada() == TipoJogada.Movimento || jogada.getTipoJogada() == TipoJogada.MovimentoXeque || jogada.getTipoJogada() == TipoJogada.MovimentoXequeMate)
		{
			Posicao posicaoAtual = jogada.getPosicaoOrigem();
			Posicao posicaoDestino = jogada.getPosicaoDestino();
			
			//testa se casa de origem esta ocupada
			if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getOcupada())
			{
				//teste se peca de origem é do jogador e se a posicao na destino esta vazia
				if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getCor() == getJodadorDaVez().getCor() && !partidaAtual.getTabuleiro().getCasa(posicaoDestino).getOcupada())
				{
					this.partidaAtual.validarMovimentoSimples(jogada);
					this.partidaAtual.executarMovimentoSimples(jogada);
					
					if(jogada.getTipoJogada() == TipoJogada.Movimento)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.Normal);
					}
					else if(jogada.getTipoJogada() == TipoJogada.MovimentoXeque)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.Xeque);
					}
					else if(jogada.getTipoJogada() == TipoJogada.MovimentoXequeMate)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.XequeMate);
						this.partidaAtual.setGanhador(this.partidaAtual.getVezJogada());
						this.partidaAtual.setHoraFimAgora();
					}
				}
				else
				{
					throw new PlayNotPossibleException();
					
				}
			}
			else
			{
				controladorJogo.exibirErroJogada(jogada.toString());
			}			
		}
		else if(jogada.getTipoJogada() == TipoJogada.Captura || jogada.getTipoJogada() == TipoJogada.CapturaXeque || jogada.getTipoJogada() == TipoJogada.CapturaXequeMate)
		{
			Posicao posicaoAtual = jogada.getPosicaoOrigem();
			Posicao posicaoDestino = jogada.getPosicaoDestino();
			
			//testa se casa de origem esta ocupada
			if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getOcupada())
			{
				//teste se peca de origem é do jogador e se a posicao na destino esta ocupada e se a peca e do outro
				if(partidaAtual.getTabuleiro().getCasa(posicaoAtual).getCor() == getJodadorDaVez().getCor() && partidaAtual.getTabuleiro().getCasa(posicaoDestino).getOcupada() && partidaAtual.getTabuleiro().getCasa(posicaoDestino).getCor() != this.partidaAtual.getVezJogada())	
				{
					this.partidaAtual.validarMovimentoCaptura(jogada);
					this.partidaAtual.executarMovimentoCaptura(jogada);
					
					if(jogada.getTipoJogada() == TipoJogada.Captura)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.Normal);
					}
					else if(jogada.getTipoJogada() == TipoJogada.CapturaXeque)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.Xeque);
					}
					else if(jogada.getTipoJogada() == TipoJogada.MovimentoXequeMate)
					{
						this.partidaAtual.setEstadoPartida(EstadoPartida.XequeMate);
						this.partidaAtual.setGanhador(this.partidaAtual.getVezJogada());
						this.partidaAtual.setHoraFimAgora();
					}
				}
				else
				{
					throw new PlayNotPossibleException();
				}
			}
			else
			{
				throw new PlayNotPossibleException();
			}	
		}
		
		else if(jogada.getTipoJogada() == TipoJogada.Promocao)
		{	
			//testa se existe uma peca na origem e a peca é um peao
			if(this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getOcupada() && this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getPeca().getTipoPeca() == TipoPeca.Peao)
			{
				TipoPeca tipoPecaPromocao = jogada.getTipoPecaPromocao();
				if(this.getJodadorDaVez().getCor() == Cor.Branco && jogada.getPosicaoOrigem().getLinha() == 8)
				{					
					switch (tipoPecaPromocao) {
					
						case Cavalo:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Cavalo(Cor.Branco));
							break;
						case Bispo:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Bispo(Cor.Branco));
							break;
						case Torre:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Torre(Cor.Branco));
							break;
						case Rainha:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Rainha(Cor.Branco));
							break;
						default:
							throw new PlayNotPossibleException();
					}
					
				}
				else if(this.getJodadorDaVez().getCor() == Cor.Preto && jogada.getPosicaoOrigem().getLinha() == 1)
				{
					switch (tipoPecaPromocao) {					
						case Cavalo:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Cavalo(Cor.Preto));
							break;
						case Bispo:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Bispo(Cor.Preto));
							break;
						case Torre:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Torre(Cor.Preto));
							break;
						case Rainha:
							this.partidaAtual.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).setPeca(new Rainha(Cor.Preto));
							break;
						default:
							throw new PlayNotPossibleException();
					}
				}
				else
				{
					throw new PlayNotPossibleException();
				}
			}
			else
			{
				throw new PlayNotPossibleException();
			}
		}

		else if(jogada.getTipoJogada() == TipoJogada.RoquePequeno)
		{
			if(this.partidaAtual.getRockPequenoPossivel())
			{
				executarRockPequeno();
				
			}
			else
			{
				throw new SmallRockNotPossibleException();
			}
				
		}
		else if(jogada.getTipoJogada() == TipoJogada.RoqueGrande)
		{
			if(this.partidaAtual.getRockGrandePossivel())
			{
				executarRockGrande();
			}
			else
			{
				throw new BigRockNotPossibleException();
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
