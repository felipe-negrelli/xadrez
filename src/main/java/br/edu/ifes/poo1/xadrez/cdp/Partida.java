package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cgt.ControladorXadrez;
import br.edu.ifes.poo1.xadrez.exceptions.PlayNotPossibleException;

public class Partida implements Serializable{

	private static final long serialVersionUID = 3541436818761632524L;
	ControladorXadrez controladorXadrez;
	Tabuleiro tabuleiro = new Tabuleiro();
	
	EstadoPartida estadoPartida;
	
	Jogador jogadorBranco;
	Jogador jogadorPreto ;
	
	Cor vezJogada;
	public Cor getVezJogada() {
		return vezJogada;
	}

	Cor ganhador;
	
	Date dataInicio;
	Date dataFim;
	
	PecaXadrez ultimaPecaMovida;
	
	public Partida(ControladorXadrez controlador)
	{
		this.controladorXadrez = controlador;
		this.estadoPartida = EstadoPartida.Normal;
		vezJogada = Cor.Branco;
	}
	
	public Jogador getJogadorBranco() {
		return jogadorBranco;
	}

	public void setJogadorBranco(Jogador jogadorBranco) {
		this.jogadorBranco = jogadorBranco;
	}

	public Jogador getJogadorPreto() {
		return jogadorPreto;
	}

	public void setJogadorPreto(Jogador jogadorPreto) {
		this.jogadorPreto = jogadorPreto;
	}

	public Jogador getJogadorDaVez() {
		if(vezJogada == Cor.Branco)
			return this.jogadorBranco;
		return this.jogadorPreto;
	}

	public void setVezJogada(Cor vezJogada) {
		this.vezJogada = vezJogada;
	}

	public void setEstadoPartida(EstadoPartida estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	public Tabuleiro getTabuleiro()
	{
		return this.tabuleiro;
	}
	
	public EstadoPartida getEstadoPartida()
	{
		return this.estadoPartida;
	}
	
	public void setNomeJogadorBranco(String nome)
	{
		this.jogadorBranco.setNome(nome);
	}
	
	public int getPontosJogadorAtual()
	{
		if(this.vezJogada == Cor.Branco)
			return this.getJogadorBranco().getPontos();
		return this.getJogadorPreto().getPontos();
	}
	
	public void setHoraInicioAgora()
	{
		Date date = new Date();
		this.dataInicio = date;
	}
	
	public void setHoraFimAgora()
	{
		Date date = new Date();
		this.dataFim = date;
	}
	
	public boolean validarMovimentoSimples(Jogada jogada)
	{
		//testa se a posicao destino esta entre as possiveis		
		List<Posicao> posicoesPossiveis = this.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getPeca().getMovimentosPossiveis(this.tabuleiro);
		
		if(existe(posicoesPossiveis, jogada.getPosicaoDestino()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean validarMovimentoCaptura(Jogada jogada)
	{
		//testa se a posicao destino esta entre as possiveis		
		List<Posicao> posicoesPossiveis = this.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getPeca().getMovimentosPossiveis(this.tabuleiro);
		
		if(existe(posicoesPossiveis, jogada.getPosicaoDestino()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void executarMovimentoSimples(Jogada jogada)
	{
		//move peca
		PecaXadrez pecaOrigem = this.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getPeca();
		
		//seta variavel peca no lugar de origem com a nova
		this.tabuleiro.getCasa(jogada.getPosicaoDestino()).setPeca(pecaOrigem);		
		pecaOrigem.incrementaMovimento();		
		ultimaPecaMovida = pecaOrigem;
		
		//limpa peca na origem
		this.tabuleiro.getCasa(jogada.getPosicaoOrigem()).limpaPeca();
		
		alterarVez();
	}
	
	public void executarMovimentoCaptura(Jogada jogada) throws PlayNotPossibleException
	{
		
		//move peca
		PecaXadrez pecaOrigem = this.getTabuleiro().getCasa(jogada.getPosicaoOrigem()).getPeca();		
		
		//testa se existe um peça a ser comida na posicao destino e adiciona pontos
		if(this.tabuleiro.getCasa(jogada.getPosicaoDestino()).getOcupada())
		{
			adicionarPontos(this.tabuleiro.getCasa(jogada.getPosicaoDestino()).getPeca());			
			addListaCapituradas(jogada.getPosicaoDestino());
			
			//testa se esta comendo o rei
			if(this.tabuleiro.getCasa(jogada.getPosicaoDestino()).getPeca().getTipoPeca() == TipoPeca.Rei)
			{
				this.estadoPartida = EstadoPartida.XequeMate;
				this.ganhador = this.vezJogada;
			}
				
			//seta variavel peca no lugar de origem com a nova
			this.tabuleiro.getCasa(jogada.getPosicaoDestino()).setPeca(pecaOrigem);		
			pecaOrigem.incrementaMovimento();		
			ultimaPecaMovida = pecaOrigem;
			
			//limpa peca na origem
			this.tabuleiro.getCasa(jogada.getPosicaoOrigem()).limpaPeca();
			
			alterarVez();
		}
		else
		{
			throw new PlayNotPossibleException();
		}
		
	}
	
	private void addListaCapituradas(Posicao posicaoDestino)
	{
		//adiciona a peca comida na lista de capturadas do outro jogador
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			this.jogadorBranco.addPecaCapturada(this.getTabuleiro().getCasa(posicaoDestino).getPeca());
		}
		else
		{
			this.jogadorPreto.addPecaCapturada(this.getTabuleiro().getCasa(posicaoDestino).getPeca());
		}
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
	
	public void alterarVez()
	{
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			setVezJogada(Cor.Preto);
		}
		else
		{
			setVezJogada(Cor.Branco);
		}
	}
	
	public void setGanhador(Cor corGanhador)
	{
		this.ganhador = corGanhador;

	}
	
	public Jogador getGanhador()
	{
		switch (this.ganhador) {
		case Branco:
			return this.jogadorBranco;
		default:
			return this.jogadorPreto;
		}
	}
	
	public Jogador getPerdedor()
	{
		switch (this.ganhador) {
		case Branco:
			return this.jogadorPreto;
		default:
			return this.jogadorBranco;
		}
	}
	
	private void adicionarPontos(PecaXadrez pecaComida)
	{
		if(vezJogada == Cor.Branco)
		{
			jogadorBranco.adicionaPontos(pecaComida.getValorEmPontos());
		}
		else
		{
			jogadorPreto.adicionaPontos(pecaComida.getValorEmPontos());
		}
	}
	
	public void executarRockPequeno()
	{		
		Posicao posicaoReiAntiga;
		Posicao posicaoReiFutura;
		
		Posicao posicaoTorreAntiga;
		Posicao posicaoTorreFutura;
		
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			posicaoReiAntiga = new Posicao(1,5);
			posicaoReiFutura = new Posicao(1,7);
			
			posicaoTorreAntiga = new Posicao(1,8);
			posicaoTorreFutura = new Posicao(1,6);			
		}
		else
		{
			posicaoReiAntiga = new Posicao(8,5);
			posicaoReiFutura = new Posicao(8,7);
			
			posicaoTorreAntiga = new Posicao(8,8);
			posicaoTorreFutura = new Posicao(8,6);			
		}		
		
		//Pega as peças das casas
		PecaXadrez pecaRei = this.getTabuleiro().getCasa(posicaoReiAntiga).getPeca();
		PecaXadrez pecaTorre = this.getTabuleiro().getCasa(posicaoTorreAntiga).getPeca();
		
		//muda o rei de lugar
		this.tabuleiro.getCasa(posicaoReiFutura).setPeca(pecaRei);
		pecaRei.incrementaMovimento();		
		
		//Muda a torre de lugar
		this.tabuleiro.getCasa(posicaoTorreFutura).setPeca(pecaTorre);
		pecaRei.incrementaMovimento();
		
		//limpa o rei na origem
		this.tabuleiro.getCasa(posicaoReiAntiga).limpaPeca();
		
		//limpa a torre na origem
		this.tabuleiro.getCasa(posicaoTorreAntiga).limpaPeca();
		
		ultimaPecaMovida = pecaRei;
		alterarVez();
	}	
	
	public void executarRockGrande()
	{
		Posicao posicaoReiAntiga;
		Posicao posicaoReiFutura;
		
		Posicao posicaoTorreAntiga;
		Posicao posicaoTorreFutura;
		
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			posicaoReiAntiga = new Posicao(1,5);
			posicaoReiFutura = new Posicao(1,3);
			
			posicaoTorreAntiga = new Posicao(1,1);
			posicaoTorreFutura = new Posicao(1,4);			
		}
		else
		{
			posicaoReiAntiga = new Posicao(8,5);
			posicaoReiFutura = new Posicao(8,3);
			
			posicaoTorreAntiga = new Posicao(8,1);
			posicaoTorreFutura = new Posicao(8,4);			
		}		
		
		//Pega as peças das casas
		PecaXadrez pecaRei = this.getTabuleiro().getCasa(posicaoReiAntiga).getPeca();
		PecaXadrez pecaTorre = this.getTabuleiro().getCasa(posicaoTorreAntiga).getPeca();
		
		//muda o rei de lugar
		this.tabuleiro.getCasa(posicaoReiFutura).setPeca(pecaRei);
		pecaRei.incrementaMovimento();		
		
		//Muda a torre de lugar
		this.tabuleiro.getCasa(posicaoTorreFutura).setPeca(pecaTorre);
		pecaRei.incrementaMovimento();
		
		//limpa o rei na origem
		this.tabuleiro.getCasa(posicaoReiAntiga).limpaPeca();
		
		//limpa a torre na origem
		this.tabuleiro.getCasa(posicaoTorreAntiga).limpaPeca();
		
		ultimaPecaMovida = pecaRei;
		
		alterarVez();
	}
	
	public boolean getRockPequenoPossivel()
	{
		boolean resposta = false;
		boolean reiOK = false;
		boolean casaVaziaEsquerdaOK = false;
		boolean casaVaziaDireitaOK = false;
		boolean torreOK = false;
		
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			Posicao posicaoRei = new Posicao(1,5);
			if(this.tabuleiro.getCasa(posicaoRei).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getTipoPeca() == TipoPeca.Rei
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getMoveu() == false)
			{
				reiOK = true;
			}
			
			
			Posicao posicaoCasaVaziaEsquerda = new Posicao(1,6);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaEsquerda).getOcupada() == false)
			{
				casaVaziaEsquerdaOK = true;
		
			}
			
			Posicao posicaoCasaVaziaDireita = new Posicao(1,7);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaDireita).getOcupada() == false)
			{
				casaVaziaDireitaOK = true;
		
			}
			
			Posicao posicaoTorre = new Posicao(1,8);
			if(this.tabuleiro.getCasa(posicaoTorre).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getTipoPeca() == TipoPeca.Torre
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getMoveu() == false)
			{
				torreOK = true;
			}
			
			if(reiOK && casaVaziaEsquerdaOK && casaVaziaDireitaOK && torreOK)
			{
				resposta = true;
			}
			
		}
		else
		{
			Posicao posicaoRei = new Posicao(8,5);
			if(this.tabuleiro.getCasa(posicaoRei).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getTipoPeca() == TipoPeca.Rei
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getMoveu() == false)
			{
				reiOK = true;
			}
			
			
			Posicao posicaoCasaVaziaEsquerda = new Posicao(8,6);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaEsquerda).getOcupada() == false)
			{
				casaVaziaEsquerdaOK = true;
		
			}
			
			Posicao posicaoCasaVaziaDireita = new Posicao(8,7);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaDireita).getOcupada() == false)
			{
				casaVaziaDireitaOK = true;
		
			}
			
			Posicao posicaoTorre = new Posicao(8,8);
			if(this.tabuleiro.getCasa(posicaoTorre).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getTipoPeca() == TipoPeca.Torre
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getMoveu() == false)
			{
				torreOK = true;
			}
			
			if(reiOK && casaVaziaEsquerdaOK && casaVaziaDireitaOK && torreOK)
			{
				resposta = true;
			}
		}
		
		return resposta;
	}
	
	public boolean getRockGrandePossivel()
	{
		boolean resposta = false;
		boolean reiOK = false;
		boolean casaVaziaEsquerdaOK = false;
		boolean casaVaziaMeioOK = false;
		boolean casaVaziaDireitaOK = false;
		boolean torreOK = false;
		
		if(getJogadorDaVez().getCor() == Cor.Branco)
		{
			Posicao posicaoTorre = new Posicao(1,1);
			if(this.tabuleiro.getCasa(posicaoTorre).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getTipoPeca() == TipoPeca.Torre
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getMoveu() == false)
			{
				torreOK = true;
			}
			
			
			Posicao posicaoCasaVaziaEsquerda = new Posicao(1,2);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaEsquerda).getOcupada() == false)
			{
				casaVaziaEsquerdaOK = true;
		
			}
			
			Posicao posicaoCasaVaziaMeio = new Posicao(1,3);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaMeio).getOcupada() == false)
			{
				casaVaziaMeioOK = true;
		
			}
			
			Posicao posicaoCasaVaziaDireita = new Posicao(1,4);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaDireita).getOcupada() == false)
			{
				casaVaziaDireitaOK = true;
		
			}
			
			Posicao posicaoRei = new Posicao(1,5);
			if(this.tabuleiro.getCasa(posicaoRei).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getTipoPeca() == TipoPeca.Rei
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getMoveu() == false)
			{
				reiOK = true;
			}
			
			if(reiOK && casaVaziaEsquerdaOK && casaVaziaMeioOK && casaVaziaDireitaOK && torreOK)
			{
				resposta = true;
			}
			
		}
		else
		{
			Posicao posicaoRei = new Posicao(8,5);
			if(this.tabuleiro.getCasa(posicaoRei).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getTipoPeca() == TipoPeca.Rei
					&& this.tabuleiro.getCasa(posicaoRei).getPeca().getMoveu() == false)
			{
				reiOK = true;
			}
			
			
			Posicao posicaoCasaVaziaEsquerda = new Posicao(8,2);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaEsquerda).getOcupada() == false)
			{
				casaVaziaEsquerdaOK = true;
		
			}
			
			Posicao posicaoCasaVaziaMeio = new Posicao(8,3);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaMeio).getOcupada() == false)
			{
				casaVaziaMeioOK = true;
		
			}
			
			Posicao posicaoCasaVaziaDireita = new Posicao(8,4);
			if(this.tabuleiro.getCasa(posicaoCasaVaziaDireita).getOcupada() == false)
			{
				casaVaziaDireitaOK = true;
		
			}
			
			Posicao posicaoTorre = new Posicao(8,1);
			if(this.tabuleiro.getCasa(posicaoTorre).getOcupada() 
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getTipoPeca() == TipoPeca.Torre
					&& this.tabuleiro.getCasa(posicaoTorre).getPeca().getMoveu() == false)
			{
				torreOK = true;
			}
			
			if(reiOK && casaVaziaEsquerdaOK && casaVaziaMeioOK && casaVaziaDireitaOK && torreOK)
			{
				resposta = true;
			}
		}
		
		return resposta;
	}
	
	public String toString()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		if(this.estadoPartida == EstadoPartida.Desistencia || this.estadoPartida == EstadoPartida.Empate || this.estadoPartida == EstadoPartida.XequeMate)
		{
			String mensagem = "Inicio:"+dateFormat.format(this.dataInicio) + " - Fim:"+ dateFormat.format(this.dataFim) + " - Ganhador:";
			
			switch (this.ganhador) {
			case Branco:
				mensagem += this.getJogadorBranco().getNome();
				break;
			case Preto:
				mensagem += this.getJogadorPreto().getNome();
				break;				
			default:
				mensagem += "Empate";
				break;
			}			
			
			return mensagem;
		}
		
		return dateFormat.format(this.dataInicio) + " - Branco: " + jogadorBranco.getNome()+ "(" + jogadorBranco.getPontos() +")"+
											" Preto: " + jogadorPreto.getNome()+ "(" + jogadorPreto.getPontos() +")";
	}
	
	
}
