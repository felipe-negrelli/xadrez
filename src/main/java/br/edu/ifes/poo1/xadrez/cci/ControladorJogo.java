package br.edu.ifes.poo1.xadrez.cci;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.ifes.poo1.xadrez.cdp.Comando;
import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogada;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Partida;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.TipoComando;
import br.edu.ifes.poo1.xadrez.cdp.TipoJogada;
import br.edu.ifes.poo1.xadrez.cdp.TipoJogador;
import br.edu.ifes.poo1.xadrez.cdp.TipoPeca;
import br.edu.ifes.poo1.xadrez.cgt.ControladorXadrez;
import br.edu.ifes.poo1.xadrez.cih.TelaTexto;
import br.edu.ifes.poo1.xadrez.exceptions.*;

public class ControladorJogo implements Serializable {
	
	private static final long serialVersionUID = 8320224785014378153L;
	ControladorXadrez controladorXadrez = new ControladorXadrez(this);
	TelaTexto telaTexto = new TelaTexto();
	
	public void iniciar()
	{
		try
		{
			controladorXadrez.lerPartidasSalvas();
		}
		catch(Exception ex)
		{
			this.telaTexto.exibirErro("Não foi possível carregar dados salvos");
		}	
		
		exibirMenuInicial();
	}
	
	public void exibirMenuInicial()
	{
		int resposta = 0;		
		while(resposta != 4)
		{
			telaTexto.imprimeMenu();	
			resposta = telaTexto.recebeRespostaInteiro();
			
			if(resposta >= 1 && resposta <=4)
			{			
				switch(resposta){
					case 1:
						iniciaMenuJogadores();
						break;
					case 2:
						retornarPartida();
						break;
					case 3:
						exibirDadosPartida();
						break;
					case 4:
						System.exit(0);
						break;
				}
			}
			else
			{
				this.telaTexto.exibirErro("Digite um numero de 1 a 4.");
			}
		}
	}
	
	public void retornarPartida()
	{
		String mensagem = "";
		
		mensagem += "\nLista de Partidas salvas:\n\n" ;
		
		List<Partida> listaPartidasAtivas = this.controladorXadrez.getListaPartidasAtivas();
		
		if(listaPartidasAtivas.size() > 0)
		{
			for(int contador=0; contador<listaPartidasAtivas.size(); contador++)
			{
				mensagem += ""+ (contador+1) + " - " + listaPartidasAtivas.get(contador).toString()+ "\n";
			}
			
			this.telaTexto.exibirMensagem(mensagem);
			
			boolean respostaOK = false;
			while(!respostaOK)
			{			
				mensagem = "\nDigite o numero da partida a ser retomada:\n=>" ;
				this.telaTexto.exibirMensagem(mensagem);
				int resposta = telaTexto.recebeRespostaInteiro();
				if(resposta >= 1 && resposta <= listaPartidasAtivas.size())
				{
					respostaOK = true;
					this.controladorXadrez.retomarPartida(listaPartidasAtivas.get(resposta-1));
					iniciarPartida();
				}
				else
				{
					this.telaTexto.exibirMensagem("O valor digitado não corresponde a uma partida.");
				}
			}			
		}
		else
		{
			this.telaTexto.exibirErro("\nNão existem partidas a serem continuadas.");
		}
	}
	
	public void iniciaMenuJogadores()
	{
		this.controladorXadrez.inicializaNovaPartida();
		
		
		boolean respostaOK = false;
		while(!respostaOK)
		{
			this.telaTexto.exibirMenuJogadores();
			int resposta = telaTexto.recebeRespostaInteiro();
			switch (resposta) {
			case 1:
				lerNomesJogadores(true);
				respostaOK = true;
				break;
			case 2:
				lerNomesJogadores(false);
				respostaOK = true;
				break;
			default:
				this.telaTexto.exibirErro("Digite 1 ou 2.");
				break;
			}
		}
		
		iniciarPartida();		
	}
	
	public void lerNomesJogadores(boolean pretoMaquina)
	{
		Jogador jogadorBranco = new Jogador(Cor.Branco);
		Jogador jogadorPreto = new Jogador(Cor.Preto);
		
		jogadorBranco.setNome(this.telaTexto.lerNomeJogadorBranco());
		jogadorBranco.setTipoJogador(TipoJogador.Humano);
		
		if(pretoMaquina) //contra a maquina
		{
			jogadorPreto.setNome("Zeus");
			jogadorPreto.setTipoJogador(TipoJogador.Computador);
		}
		else //contra outro jogador
		{
			jogadorPreto.setNome(this.telaTexto.lerNomeJogadorPreto());
			jogadorPreto.setTipoJogador(TipoJogador.Humano);
		}
		
		controladorXadrez.salvarJogador(jogadorBranco);
		controladorXadrez.salvarJogador(jogadorPreto);	
	}

	public void iniciarPartida()
	{	
		while(controladorXadrez.getEstadoPartida() == EstadoPartida.Normal || controladorXadrez.getEstadoPartida() == EstadoPartida.Xeque)
		{
			exibirTabuleiro();
			
			Jogador jogador = controladorXadrez.getJodadorDaVez();
			
			if(jogador.getTipoJogador() == TipoJogador.Computador)
			{
				//Exibe mensagem para simular que estamos perguntando ao computador
				telaTexto.exibirMensagem(jogador.getNome()+" ("+jogador.getCor().toString()+") =>");
				
				//gera uma jogada aleatora dentre os movimentos possiveis das pecas
				Jogada jogadaAtual = jogador.getJogadaAutomatica(this.controladorXadrez.getTabuleiro());
				
				//exibe a jogada executada pela maquina
				telaTexto.exibirMensagem(jogadaAtual.toString()+"\n");		
				
				//processarComandoString(jogadaAtual.toString());
				
				Comando comandoAtual = new Comando();
				comandoAtual.setJogada(jogadaAtual);
				comandoAtual.setTipoComando(TipoComando.Jogada);
				comandoAtual.setComandoValido(true);
				processarComando(comandoAtual);
			}
			else
			{
				String jogada = telaTexto.lerJogada(jogador.getNome()+" ("+jogador.getCor().toString()+")");
				
				Comando comandoAtual = gerarComando(jogada);
				if(comandoAtual.getComandoValido())
				{
					processarComando(comandoAtual);
				}
				else
				{
					exibirErroJogada(jogada);
				}
			}			
		}
	}
	
	public void processarComando(Comando comando)
	{
		if(comando.getTipoComando() == TipoComando.Desistencia)
		{
			this.controladorXadrez.desistir();
		}
		
		if(comando.getTipoComando() == TipoComando.Empate)
		{
			this.controladorXadrez.empatar();
		}
		
		else if(comando.getTipoComando() == TipoComando.Sair)
		{
			try
			{
				this.controladorXadrez.salvarPartidas();
				this.controladorXadrez.setEstadoPartida(EstadoPartida.Pausada);
			}
			catch(Exception ex)
			{
				this.telaTexto.exibirErro("Erro. Não foi possível salvar os dados em disco!");
			}
		}
		
		else if(comando.getTipoComando() == TipoComando.Pontos)
		{
			this.telaTexto.exibirPontos(this.controladorXadrez.getJodadorDaVez());
		}
		
		else if(comando.getTipoComando() == TipoComando.Salvar)
		{
			try
			{
				this.controladorXadrez.salvarPartidas();
				this.telaTexto.exibirMensagem("Jogo salvo com sucesso!\n\n");
				
			}
			catch(Exception ex)
			{
				this.telaTexto.exibirErro("Erro. Não foi possível salvar os dados em disco!");
			}
		}
		
		else if(comando.getTipoComando() == TipoComando.Jogada)
		{
			try
			{
				//tenta processar a jogada
				controladorXadrez.processarJogada(comando.getJogada());
			}
			catch(BigRockNotPossibleException bigRockException)
			{
				this.telaTexto.exibirErro("Jogada Roque Grande não é possível no momento.");
			}
			catch(SmallRockNotPossibleException smallRockException)
			{
				this.telaTexto.exibirErro("Jogada Roque Pequeno não é possível no momento.");
			}
			catch(PlayNotPossibleException playNotPossibleException)
			{
				this.telaTexto.exibirErro("Jogada não é possível.");
			}
		}	
	}
	
	public Comando gerarComando(String jogada)
	{
		int colunaOrigem, linhaOrigem, colunaDestino, linhaDestino;
		String colunaOrigemString, linhaOrigemString, colunaDestinoString, linhaDestinoString;
		Comando comandoAtual = new Comando();	
		try
		{			
			if(jogada.equals("desistir"))
			{
				comandoAtual.setTipoComando(TipoComando.Desistencia);
				comandoAtual.setComandoValido(true);
			}		
			
			else if(jogada.equals("empate"))
			{
				comandoAtual.setTipoComando(TipoComando.Empate);
				comandoAtual.setComandoValido(true);
			}			
				
			else if(jogada.equals("pontos"))
			{
				comandoAtual.setTipoComando(TipoComando.Pontos);
				comandoAtual.setComandoValido(true);
			}			
			
			else if(jogada.equals("sair"))
			{
				comandoAtual.setTipoComando(TipoComando.Sair);
				comandoAtual.setComandoValido(true);
			}
			
			else if(jogada.equals("salvar"))
			{
				comandoAtual.setTipoComando(TipoComando.Salvar);
				comandoAtual.setComandoValido(true);
			}
			
			else if(jogada.equals("o-o-o"))
			{
				comandoAtual.setTipoComando(TipoComando.Jogada);
				comandoAtual.setJogada(new Jogada(TipoJogada.RoqueGrande));
				comandoAtual.setComandoValido(true);
			}
			
			else if(jogada.equals("o-o"))
			{
				comandoAtual.setTipoComando(TipoComando.Jogada);
				comandoAtual.setJogada(new Jogada(TipoJogada.RoquePequeno));
				comandoAtual.setComandoValido(true);
			}
			
			//testa se é uma promoção
			else if(jogada.length()==4 && jogada.substring(2,3).equals("="))
			{
				TipoPeca tipoPecaPromocao;
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);			
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(1, 1);
				
				char letraPeca = jogada.charAt(3);
				
				switch (letraPeca) {
					case 'b':
						tipoPecaPromocao = TipoPeca.Bispo;
						break;
					case 'B':
						tipoPecaPromocao = TipoPeca.Bispo;	
						break;
					case 'c':
						tipoPecaPromocao = TipoPeca.Cavalo;
						break;
					case 'C':
						tipoPecaPromocao = TipoPeca.Cavalo;
						break;				
					case 't':
						tipoPecaPromocao = TipoPeca.Torre;
						break;
					case 'T':
						tipoPecaPromocao = TipoPeca.Torre;
						break;
					case 'd':
						tipoPecaPromocao = TipoPeca.Rainha;
						break;
					case 'D':
						tipoPecaPromocao = TipoPeca.Rainha;
						break;
					default:
						throw new PlayNotPossibleException();
				}
				
				if(origem.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.Promocao));
					comandoAtual.getJogada().setTipoPecaPromocao(tipoPecaPromocao);
					comandoAtual.setComandoValido(true);					
				}				
			}
			
			//testa se é um movimento simples
			else if(jogada.length()==4)
			{	
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(2,3);
				linhaDestinoString = jogada.substring(3,4);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);				
				
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);
				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.Movimento));
					comandoAtual.setComandoValido(true);					
				}				
			}
			
			
			
			//Testa se é um movimento simples e xeque	
			else if(jogada.length()==5 && jogada.substring(4,5).equals("+"))
			{
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(2,3);
				linhaDestinoString = jogada.substring(3,4);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.MovimentoXeque));
					comandoAtual.setComandoValido(true);					
				}
			}	
			
			//Testa se é um movimento simples e xeque mate	
			else if(jogada.length()==5 && jogada.substring(4,5).equals("#"))
			{
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(2,3);
				linhaDestinoString = jogada.substring(3,4);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.MovimentoXequeMate));
					comandoAtual.setComandoValido(true);					
				}
			}	
			
			//testa se eh um movimento de captura		
			else if(jogada.length()==5 && jogada.substring(2,3).equals("x"))
			{
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(3,4);
				linhaDestinoString = jogada.substring(4,5);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.Captura));
					comandoAtual.setComandoValido(true);					
				}
			}
			
			//testa se eh um movimento de captura e xeque	
			else if(jogada.length()==6 && jogada.substring(2,3).equals("x") && jogada.substring(5,6).equals("+"))
			{
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(3,4);
				linhaDestinoString = jogada.substring(4,5);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.CapturaXeque));
					comandoAtual.setComandoValido(true);					
				}
			}
			
			//testa se eh um movimento de captura e xeque mate
			else if(jogada.length()==6 && jogada.substring(2,3).equals("x") && jogada.substring(5,6).equals("#"))
			{
				colunaOrigemString = jogada.substring(0,1);
				linhaOrigemString = jogada.substring(1,2);
				colunaDestinoString = jogada.substring(3,4);
				linhaDestinoString = jogada.substring(4,5);
				
				colunaOrigem = Integer.parseInt(colunaOrigemString);
				linhaOrigem = Integer.parseInt(linhaOrigemString);
				colunaDestino = Integer.parseInt(colunaDestinoString);
				linhaDestino = Integer.parseInt(linhaDestinoString);
				
				Posicao origem = new Posicao(colunaOrigem, linhaOrigem);
				Posicao destino = new Posicao(colunaDestino, linhaDestino);				
				if(origem.validarPosicao() && destino.validarPosicao())
				{	
					comandoAtual.setTipoComando(TipoComando.Jogada);
					comandoAtual.setJogada(new Jogada(origem, destino, TipoJogada.CapturaXequeMate));
					comandoAtual.setComandoValido(true);					
				}
			}	

		}
		catch(Exception e){}
		return comandoAtual;
	}	
	
	public void exibirTabuleiro()
	{	
		telaTexto.imprimirTabuleiro(controladorXadrez.getTabuleiro());
	}
	
	public void exibirErroJogada(String jogada)
	{
		telaTexto.exibirErroJogada(jogada);
	}
	
	public void exibirErroLogico()
	{
		telaTexto.exibirErroLogico();
	}
	
	public void exibirDadosPartida()
	{
		List<Partida>  listaPartidasFinalizadas = this.controladorXadrez.getListaPartidasFinalizadas();		
		
		if(listaPartidasFinalizadas.size() > 0)
		{
			String mensagem = "\nDados das partidas finalizadas:\n\n" ;	
			for(int contador=0; contador<listaPartidasFinalizadas.size(); contador++)
			{
				mensagem += ""+ (contador+1) + " - " + listaPartidasFinalizadas.get(contador).toString()+ "\n";
			}
			
			//Imprime Vitorias
			List<String> listaJogadores = new ArrayList<String>();
			for(int contador=0; contador<listaPartidasFinalizadas.size(); contador++)
			{
				if(listaPartidasFinalizadas.get(contador).getEstadoPartida() != EstadoPartida.Empate)
				{					
					listaJogadores.add(listaPartidasFinalizadas.get(contador).getGanhador().getNome());
				}
			}			
			mensagem += "\nVitórias:\n";			
			Set<String> mySet = new HashSet<String>(listaJogadores);
			for(String s: mySet){
				mensagem += s + " " +Collections.frequency(listaJogadores,s)+"\n";
			}
			
			
			//Imprime derrotas
			List<String> listaJogadoresDerrotas = new ArrayList<String>();
			for(int contador=0; contador<listaPartidasFinalizadas.size(); contador++)
			{
				if(listaPartidasFinalizadas.get(contador).getEstadoPartida() != EstadoPartida.Empate)
				{					
					listaJogadoresDerrotas.add(listaPartidasFinalizadas.get(contador).getPerdedor().getNome());
				}
			}			
			mensagem += "\nDerrotas:\n";			
			Set<String> mySetDerrotas = new HashSet<String>(listaJogadoresDerrotas);
			for(String s: mySetDerrotas){
				mensagem += s + " " +Collections.frequency(listaJogadoresDerrotas,s)+"\n";
			}
			
			mensagem += "\n";
			this.telaTexto.exibirMensagem(mensagem);		
		}
		else
		{
			this.telaTexto.exibirMensagem("Não existe nenhuma partida finalizada no momento.\n\n");	
		}
	}	
}
