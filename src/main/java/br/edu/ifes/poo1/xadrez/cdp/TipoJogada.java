package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;

public enum TipoJogada implements Serializable{
	Movimento, MovimentoXeque, MovimentoXequeMate, Captura, CapturaXeque, CapturaXequeMate, RoqueGrande, RoquePequeno, Promocao

}
