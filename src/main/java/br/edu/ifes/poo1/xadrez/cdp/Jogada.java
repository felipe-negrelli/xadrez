package br.edu.ifes.poo1.xadrez.cdp;

public class Jogada {
	
	private Posicao posicaoOrigem;
	private Posicao posicaoDestino;
	private TipoJogada tipoJogada;
	private TipoPeca tipoPecaPromocao;
	private boolean jogadaValida;
	
	
	public Jogada(Posicao origem, Posicao destino, TipoJogada tipoJogada)
	{
		super();
		this.posicaoOrigem = origem;
		this.posicaoDestino = destino;
		this.tipoJogada = tipoJogada;
	}
	
	public Jogada(TipoJogada tipoJogada)
	{
		super();
		this.tipoJogada = tipoJogada;
	}
	
	
	public TipoPeca getTipoPecaPromocao() {
		return tipoPecaPromocao;
	}

	public void setTipoPecaPromocao(TipoPeca tipoPecaPromocao) {
		this.tipoPecaPromocao = tipoPecaPromocao;
	}
	
	public boolean isJogadaValida() {
		return jogadaValida;
	}

	public void setJogadaValida(boolean jogadaValida) {
		this.jogadaValida = jogadaValida;
	}
	
	

	public Posicao getPosicaoOrigem() {
		return posicaoOrigem;
	}

	public void setPosicaoOrigem(Posicao posicaoOrigem) {
		this.posicaoOrigem = posicaoOrigem;
	}

	public Posicao getPosicaoDestino() {
		return posicaoDestino;
	}

	public void setPosicaoDestino(Posicao posicaoDestino) {
		this.posicaoDestino = posicaoDestino;
	}

	public TipoJogada getTipoJogada() {
		return tipoJogada;
	}

	public void setTipoJogada(TipoJogada tipoJogada) {
		this.tipoJogada = tipoJogada;
	}
	
	public String toString()
	{
		if(this.tipoJogada == TipoJogada.Movimento)
		{
			return ""+this.posicaoOrigem.toString()+posicaoDestino.toString();
		}
		else
		{
			return ""+this.posicaoOrigem.toString()+"x"+posicaoDestino.toString();
		}
	}
	

}
