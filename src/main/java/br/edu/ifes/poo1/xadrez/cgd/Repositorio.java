package br.edu.ifes.poo1.xadrez.cgd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import br.edu.ifes.poo1.xadrez.cdp.*;;

public class Repositorio {
	
	public static <T> void salvarDados(List<T> t) throws Exception
	{
		String path = new File("").getAbsolutePath() + File.separator + "dados.dat";
		ObjectOutputStream out = null;
		try
		{
			out = new ObjectOutputStream(new FileOutputStream(path));			
			out.writeObject(t);
		}
		finally
		{
			out.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> lerDados() throws Exception
	{
		String path = new File("").getAbsolutePath() + File.separator + "dados.dat";
		ObjectInputStream in = null;		
		try
		{
			in = new ObjectInputStream(new FileInputStream(path));
			List<T> dados = (List<T>)in.readObject();	
			return dados;
		}
		finally
		{
			in.close();
		}
	}
}
