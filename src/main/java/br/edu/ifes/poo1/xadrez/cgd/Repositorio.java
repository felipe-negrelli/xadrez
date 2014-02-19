package br.edu.ifes.poo1.xadrez.cgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cdp.*;;

public class Repositorio {
	
	public static void salvarDados(List<Partida> listaPartidas) throws Exception
	{
		String path = new File("").getAbsolutePath() + File.separator + "partidas.dat";
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(path));
			
			//converte em vetores
			Partida[] vetorPartidas = new Partida[listaPartidas.size()];
			vetorPartidas = listaPartidas.toArray(vetorPartidas);			
			
			out.writeObject(vetorPartidas);
		}
		finally
		{
			out.close();
		}
	}
	
	public static List<Partida> lerDados() throws Exception
	{
		String path = new File("").getAbsolutePath() + File.separator + "partidas.dat";
		ObjectInputStream in = null;		
		try
		{
			in = new ObjectInputStream(new FileInputStream(path));

			//le o vetor de partidas
			Partida[] vetorPartidas = (Partida[])in.readObject();

			//converte de vetor para lista			
			List<Partida> partidas = Arrays.asList(vetorPartidas);
			
			return partidas;
		}
		finally
		{
			in.close();
		}
	}
}
