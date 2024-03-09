package com.ferreira.vinicius.habitos.logica;

public class RespostaErro {
	String mensagem;
	
	public RespostaErro(String mensagem){
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
