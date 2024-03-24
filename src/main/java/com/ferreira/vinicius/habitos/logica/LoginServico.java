package com.ferreira.vinicius.habitos.logica;

//COMO MUDEI A CLASSE DE PACOTE PRECISEI IMPORTAR AS CLASSES ONDE CONTEM OS METODOS QUE MEU CODIGO PRECISA PARA FUNCIONAR
//15/02
import com.ferreira.vinicius.habitos.persistencia.LoginRepositorio;

public class LoginServico {

	private LoginRepositorio loginRepositorio;

	public LoginServico() {
		this.loginRepositorio = new LoginRepositorio();
	}

	public boolean login(String usuario, String senha) {
		Usuario usuarioObj = loginRepositorio.procurarUsuario(usuario);

		if (usuario != null && usuarioObj.getSenha().equals(senha)) {
			loginRepositorio.registrarUltimoAcessoAutorizado(usuario);
			return this.acessoPermitido(usuario);

		} else {
			return false;
		}
	}

	private boolean acessoPermitido(String idUsuario) {
		Long ultimoAcesso = loginRepositorio.AcessarUltimoAcessoAutorizado(idUsuario);
		if (ultimoAcesso != null) {
			long horaAtual = System.currentTimeMillis();
			long tempoDecorrido = horaAtual - ultimoAcesso;
			return tempoDecorrido <= 30 * 60 * 1000;
		}
		return false;
	}

}