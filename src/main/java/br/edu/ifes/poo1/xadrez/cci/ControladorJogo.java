package br.edu.ifes.poo1.xadrez.cci;

import java.io.Serializable;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Partida;
import br.edu.ifes.poo1.xadrez.cgt.ControladorXadrez;
import br.edu.ifes.poo1.xadrez.cih.TelaTexto;
import br.edu.ifes.poo1.xadrez.exceptions.*;

public class ControladorJogo implements Serializable {
	
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
	}
	
	public void retornarPartida()
	{
		List<Partida> listaPartidasAtivas = this.controladorXadrez.getListaPartidasAtivas();
		if(listaPartidasAtivas.size() > 0)
		{
			String mensagem = "";
			
			for(int contador=0; contador<listaPartidasAtivas.size(); contador++)
			{
				mensagem += ""+ (contador+1) + " - " + listaPartidasAtivas.get(contador).toString()+ "\n";
			}
			
			mensagem += "\n\nDigite o numero da partida a ser retomada:" ;
			this.telaTexto.exibirMensagem(mensagem);
		}
		else
		{
			this.telaTexto.exibirErro("\nNão existem partidas a serem continuadas.");
		}
	}
	
	public void iniciaMenuJogadores()
	{
		this.controladorXadrez.inicializaNovaPartida();
		this.telaTexto.exibirMenuJogadores();
		
		boolean respostaOK = false;
		while(!respostaOK)
		{
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
				this.telaTexto.exibirErro("Entrada inválida! Digite 1 ou 2:");
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
		
		if(pretoMaquina) //contra a maquina
		{
			jogadorPreto.setNome("Zeus");
		}
		else //contra outro jogador
		{
			jogadorPreto.setNome(this.telaTexto.lerNomeJogadorPreto());
		}
		
		controladorXadrez.salvarJogador(jogadorBranco);
		controladorXadrez.salvarJogador(jogadorPreto);	
	}

	public void iniciarPartida()
	{	
		while(controladorXadrez.getEstadoPartida() == EstadoPartida.Normal)
		{
			exibirTabuleiro();
			
			Jogador jogador = controladorXadrez.getJodadorDaVez();
			String jogada = telaTexto.lerJogada(jogador.getNome()+" ("+jogador.getCor().toString()+")");
			
			if(isComandoValido(jogada))
			{
				processarJogada(jogada);
			}
			else
			{
				exibirErroJogada(jogada);
			}
		}
	}	
	
	public void processarJogada(String jogada)
	{
		if(jogada.equals("pontos"))
		{
			int pontos = this.controladorXadrez.getPontosJogadorAtual();
			this.telaTexto.exibirPontos(pontos);
		}
		else if(jogada.equals("sair"))
		{
			try
			{
				this.controladorXadrez.salvarPartidas();
			}
			catch(Exception ex)
			{
				this.telaTexto.exibirErro("Erro. Não foi possível salvar os dados em disco!");
			}
			this.exibirMenuInicial();
		}
		else
		{
			try
			{
				//tenta processar a jogada
				controladorXadrez.processarJogada(jogada);
			}
			catch(BigRockNotPossibleException bigRockException)
			{
				this.exibirErroJogada(jogada);
			}
			catch(SmallRockNotPossibleException smallRockException)
			{
				this.exibirErroJogada(jogada);
			}
			catch(PlayNotPossibleException playNotPossibleException)
			{
				this.exibirErroJogada(jogada);
			}
		}		
	}
	
	public boolean isComandoValido(String jogada)
	{
		boolean jogadaValida = false;		
		if(jogada.equals("desistir")    ||
				jogada.equals("empate") ||
				jogada.equals("pontos") ||
				jogada.equals("sair") ||				
				jogada.equals("O-O-O")  ||
				jogada.equals("O-O") )
		{
			jogadaValida = true;
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////
		//testa se eh um movimento simples
		int numero = 0;
		try	{numero = Integer.parseInt(jogada);}
		catch(Exception e){}
		
		if(numero>1111 && numero<8888)
		{
			jogadaValida = true;
		}		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		//testa se eh um movimento de captura		
		if(jogada.length()==5 && (jogada.substring(2,3).equals("x") || jogada.substring(2,3).equals("X")))
		{
			try	{numero = Integer.parseInt(jogada.substring(0,2)+jogada.substring(3,5));}
			catch(Exception e){}
			
			if(numero>1111 && numero<8888)
			{
				jogadaValida = true;
			}
		}		
		return jogadaValida;
	}	
	
	public void exibirTabuleiro()
	{	
		telaTexto.imprimeTabuleiro(controladorXadrez.getTabuleiro());
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
		//implementar
	}
	
}
