package br.edu.ifes.poo1.xadrez.cci;

import br.edu.ifes.poo1.xadrez.cdp.Cor;
import br.edu.ifes.poo1.xadrez.cdp.EstadoPartida;
import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cgt.ControladorXadrez;
import br.edu.ifes.poo1.xadrez.cih.TelaTexto;

public class ControladorJogo {
	
	ControladorXadrez controladorXadrez = new ControladorXadrez(this);
	TelaTexto telaTexto = new TelaTexto();
	
	public void iniciar()
	{
		telaTexto.imprimeMenu();	
		int resposta = telaTexto.recebeRespostaInteiro();
		
		switch(resposta){
			case 1:
				iniciaMenuJogadores();
			case 2:
				exibeDadosPartida();
			case 3:
				System.exit(0);			
		}
	}
	
	public void iniciaMenuJogadores()
	{
		boolean maquina = true;
		Jogador jogadorBranco = new Jogador(Cor.Branco);
		Jogador jogadorPreto = new Jogador(Cor.Preto);
		
		telaTexto.exibirMenuJogadores();		
		int resposta = telaTexto.recebeRespostaInteiro();
		
		jogadorBranco.setNome(telaTexto.lerNomeJogadorBranco());			
		
		if(resposta == 1) //contra a maquina
		{
			maquina = true;
			jogadorPreto.setNome("Zeus");
		}
		if(resposta == 2) //contra outro jogador
		{
			maquina = false;
			jogadorPreto.setNome(telaTexto.lerNomeJogadorPreto());
		}
		
		controladorXadrez.salvaJogador(jogadorBranco);
		controladorXadrez.salvaJogador(jogadorPreto);		
			
		iniciaPartida(maquina);		
	}

	public void iniciaPartida(boolean computador)
	{	
		while(controladorXadrez.getEstadoPartida() == EstadoPartida.Normal)
		{
			exibeTabuleiro();
			
			Jogador jogador = controladorXadrez.getJodadorDaVez();
			String jogada = telaTexto.lerJogada(jogador.getNome()+" ("+jogador.getCor().toString()+")");
			
			if(isComandoValido(jogada))
			{
				processaJogada(jogada);
			}
			else
			{
				exibirErroJogada(jogada);
			}
		}
	}	
	
	public void processaJogada(String jogada)
	{
		if(jogada == "pontos")
		{
			int pontos = this.controladorXadrez.getPontosJogadorAtual();
		}
		else
		{
			//testa se é movimento de captura e remove o X
			int numero = 0;
			if(jogada.length()==5 && (jogada.substring(2,3).equals("x") || jogada.substring(2,3).equals("X")))
			{
				try	{numero = Integer.parseInt(jogada.substring(0,2)+jogada.substring(3,5));}
				catch(Exception e){}
				
				if(numero>1111 && numero<8888)
				{
					jogada = ""+numero;
				}
			}
			
			//processa a jogada
			controladorXadrez.processarJogada(jogada);
		}		
	}
	
	public boolean isComandoValido(String jogada)
	{
		boolean jogadaValida = false;		
		if(jogada.equals("desistir")    ||
				jogada.equals("empate") ||
				jogada.equals("pontos") ||
				jogada.equals("O-O-O")  ||
				jogada.equals("O-O") )
		{
			jogadaValida = true;
		}
		
		//testa se eh um movimento simples
		int numero = 0;
		try	{numero = Integer.parseInt(jogada);}
		catch(Exception e){}
		
		if(numero>1111 && numero<8888)
		{
			jogadaValida = true;
		}		
		
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
	
	public void exibeTabuleiro()
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
	
	public void exibeDadosPartida()
	{
		//implementar
	}
}
