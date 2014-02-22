package br.edu.ifes.poo1.xadrez.cdp;

import java.io.Serializable;

public enum TipoComando implements Serializable{
	Desistencia, Empate, Pontos, Sair, Salvar, Jogada, RoquePequeno, RoqueGrande;
}
