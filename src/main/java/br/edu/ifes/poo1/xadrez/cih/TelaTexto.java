package br.edu.ifes.poo1.xadrez.cih;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

import br.edu.ifes.poo1.xadrez.cdp.Jogador;
import br.edu.ifes.poo1.xadrez.cdp.Posicao;
import br.edu.ifes.poo1.xadrez.cdp.Tabuleiro;

public class TelaTexto implements Serializable{
	
	private static final long serialVersionUID = -9176851184338529236L;

	public TelaTexto()
	{
		
	}
	
	public void imprimeMenu()
	{
		String menu = "1. Iniciar uma nova partida\n"+
					  "2. Retornar um partida\n"+
					  "3. Dados das partidas\n"+
					  "4. Sair\n"+
					  "=>";
		System.out.print(menu);
	}
	
	public void imprimirTabuleiro(Tabuleiro tabuleiro)
	{
		String tabuleiroDesenho = "\n\n";
		
		tabuleiroDesenho = "            Preto\n";
		tabuleiroDesenho += "    1  2  3  4  5  6  7  8\n";
		tabuleiroDesenho += "   +----------------------+\n";
		
		for(int contadorLinha=8;contadorLinha>0;contadorLinha--){
			
			tabuleiroDesenho += " "+(contadorLinha)+" |";
			
			
			for(int contadorColuna=1;contadorColuna<9;contadorColuna++)
			{			
				//testa para ver se existe uma peça ou não
				if(tabuleiro.getCasa(new Posicao(contadorColuna,contadorLinha)).getOcupada())
				{
					//se existir adiciona a letra equivalente ao tabuleiro
					tabuleiroDesenho += tabuleiro.getCasa(new Posicao(contadorColuna,contadorLinha)).getPeca().toString();
				}
				//se não tiver peça, coloca um ponto
				else
				{
					tabuleiroDesenho += ".";
				}
				
				//adiciona espaço após peças, exceto na ultima coluna
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
	
	@SuppressWarnings("resource")
	public int recebeRespostaInteiro()
	{
		int resultado = 0;		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		try
		{
			resultado = scanner.nextInt();	
		}
		catch(Exception e)
		{
			System.out.println("Entrada Inválida!");
		}
		return resultado;	
	}
	
	public void exibirMenuJogadores()
	{	
		String menu = "\nDeseja jogar contra o computador ou contra outro usuário?\n"+
				  "1. Sozinho (Contra o computador).\n"+
				  "2. Contra outro usuário.\n"+
				  "=>";
		System.out.print(menu);
	}
	
	public String lerNomeJogadorBranco()
	{
		boolean resultadoValido = false;
		String resultado = "";
		
		while(!resultadoValido)
		{
			String mensagem = "\nDigite o nome do Jogador Branco:\n=>";
			System.out.print(mensagem);
			@SuppressWarnings("resource")
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
			System.out.print(mensagem);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new InputStreamReader(System.in));			
			resultado = scanner.nextLine();			
			if(resultado.length() > 0)			{
				resultadoValido = true;
			}
			else
			{
				System.out.println("\nÉ necessário digitar um nome!");
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
			@SuppressWarnings("resource")
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
		System.out.println("'"+jogada+"'"+" não é uma jogada válida.\n");
	}
	
	public void exibirErro(String mensagem)
	{
		System.out.println(mensagem+"\n\n");
	}
	
	public void exibirErroLogico()
	{
		System.out.println("'A jogada não é possível. Digite uma nova jogada.\n");
	}
	
	public void exibirPontos(Jogador jogadorAtual)
	{
		String mensagem = "Pontos: " + jogadorAtual.getPontos() + "\n";
		mensagem += "Peças Capturadas:\n";
		
		for(int contador=0;contador<jogadorAtual.getPecasCapturadas().size();contador++)
		{
			mensagem += jogadorAtual.getPecasCapturadas().get(contador).getTipoPeca().toString()+"\n";
		}
		
		System.out.println(mensagem);
	}
	
	public void exibirMensagem(String mensagem)
	{
		System.out.print(mensagem);
	}
}
