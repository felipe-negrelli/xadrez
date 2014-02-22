package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;

public class Comando implements Serializable{
	
	private static final long serialVersionUID = -7756403955110210117L;
	private TipoComando tipoComando;
	private boolean comandoValido; 
	private Jogada jogada;

	
	public Comando()
	{
		this.comandoValido = false;
	}
	
	
	public Jogada getJogada() {
		return jogada;
	}

	public void setJogada(Jogada jogada) {
		this.jogada = jogada;
	}

	public TipoComando getTipoComando() {
		return tipoComando;
	}
	public void setTipoComando(TipoComando tipoComando) {
		this.tipoComando = tipoComando;
	}
	public boolean getComandoValido() {
		return comandoValido;
	}
	public void setComandoValido(boolean comandoValido) {
		this.comandoValido = comandoValido;
	}	
}
