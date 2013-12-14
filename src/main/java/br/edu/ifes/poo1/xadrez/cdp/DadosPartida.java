package br.edu.ifes.poo1.xadrez.cdp;

import java.util.Date;

public class DadosPartida {
	
	private Jogador ganhador;
	private Jogador perdedor;
	
	private Date dataInicial;
	private Date dataFinal;
	
	public DadosPartida()
	{
		
	}
	
	public Jogador getGanhador() {
		return ganhador;
	}
	public void setGanhador(Jogador ganhador) {
		this.ganhador = ganhador;
	}
	public Jogador getPerdedor() {
		return perdedor;
	}
	public void setPerdedor(Jogador perdedor) {
		this.perdedor = perdedor;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	

}
