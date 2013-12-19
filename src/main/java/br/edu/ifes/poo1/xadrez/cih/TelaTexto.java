package br.edu.ifes.poo1.xadrez.cih;

import java.io.InputStreamReader;
import java.util.Scanner;

import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.Tabuleiro;

public class TelaTexto {
	
	public TelaTexto()
	{
		
	}
	
	public void imprimeMenu()
	{
		String menu = "Xadrez - (Felipe Negrelli e Paulo Vianna)\n\n"+ 
					  "1. Iniciar uma nova partida\n"+
					  "2. Dados das partidas\n"+
					  "3. Sair\n"+
					  "=>";
		System.out.print(menu);
	}
	
	public void imprimeTabuleiro(Tabuleiro tabuleiro)
	{
		String tabuleiroDesenho = "";
		
		tabuleiroDesenho = "            Preto\n";
		tabuleiroDesenho += "    1  2  3  4  5  6  7  8\n";
		tabuleiroDesenho += "   +----------------------+\n";
		
		for(int contadorLinha=8;contadorLinha>0;contadorLinha--){
			
			tabuleiroDesenho += " "+(contadorLinha)+" |";
			
			
			for(int contadorColuna=1;contadorColuna<9;contadorColuna++)
			{			
				//testa para ver se existe uma pe�a ou n�o
				if(tabuleiro.getCasa(new Posicao(contadorLinha,contadorColuna)).getOcupada())
				{
					//se existir adiciona a letra equivalente ao tabuleiro
					tabuleiroDesenho += tabuleiro.getCasa(new Posicao(contadorLinha,contadorColuna)).getPeca().toString();
				}
				//se n�o tiver pe�a, coloca um ponto
				else
				{
					tabuleiroDesenho += ".";
				}
				
				//adiciona espa�o ap�s pe�as, exceto na ultima coluna
				if(contadorColuna<8)
				{
					tabuleiroDesenho += "  ";
				}				
			}
			
			tabuleiroDesenho += "| "+(contadorLinha)+"\n";		
		}

		tabuleiroDesenho += "   +----------------------+\n";
		tabuleiroDesenho += "    1  2  3  4  5  6  7  8\n";
		tabuleiroDesenho += "            Branco\n";	
		
		System.out.println(tabuleiroDesenho);
		
	}
	
	public int recebeRespostaInteiro()
	{
		int resultado;		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		
		resultado = scanner.nextInt();
		
		return resultado;	
	}
	
	public void exibirMenuJogadores()
	{	
		String menu = "\n\nDeseja jogar contra o computador ou contra outro usu�rio?\n"+
				  "1. Sozinho (Contra o computador).\n"+
				  "2. Contra outro usu�rio.\n"+
				  "=>";
		System.out.print(menu);
	}
	
	public String lerNomeJogadorBranco()
	{
		boolean resultadoValido = false;
		String resultado = "";
		
		while(!resultadoValido)
		{
			String mensagem = "\n\nDigite o nome do Jogador Branco:\n=>";
			System.out.print(mensagem);
			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			
			resultado = scanner.nextLine();
			
			if(resultado.length() > 0)
			{
				resultadoValido = true;
			}
		}
		
		return resultado;	
	}
	
	public String lerNomeJogadorPreto()
	{
		boolean resultadoValido = false;
		String resultado = "";	
		
		while(!resultadoValido)
		{
			String mensagem = "\nDigite o nome do Jogador Preto:\n=>";
			System.out.println(mensagem);
			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			
			resultado = scanner.nextLine();
			
			if(resultado.length() > 0)
			{
				resultadoValido = true;
			}
			else
			{
				System.out.println("\n� necess�rio digitar um nome!");
			}
		}
		
		return resultado;	
	}
	
	public String lerJogada(String nomeJogador)
	{
		boolean resultadoValido = false;
		String resultado = "";
		
		while(!resultadoValido)
		{
			String mensagem = nomeJogador+" =>";
			System.out.print(mensagem);
			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			
			resultado = scanner.nextLine();
			
			if(resultado.length() > 0)
			{
				resultadoValido = true;
			}
		}
		
		return resultado;
	}
	
	public void exibirErroJogada(String jogada)
	{
		System.out.println("'"+jogada+"'"+" n�o � uma jogada v�lida.\n\n");
	}
	
	public void exibirErroLogico()
	{
		System.out.println("'A jogada n�o � poss�vel. Digite uma nova jogada.\n\n");
	}
	
	public void exibirPontos(Jogador jogador)
	{
		
	}
}
