package com.ferreira.vinicius.habitos.logica;

//COMO MUDEI A CLASSE DE PACOTE PRECISEI IMPORTAR AS CLASSES ONDE CONTEM OS METODOS QUE MEU CODIGO PRECISA PARA FUNCIONAR
//15/02
import com.ferreira.vinicius.habitos.persistencia.LoginRepository;

public class LoginService {

	private LoginRepository loginRepository;

	public LoginService() {
		this.loginRepository = new LoginRepository();
	}

	public boolean login(String usuario, String senha) {
		Usuario usuarioObj = loginRepository.procurarUsuario(usuario);

		if (usuario != null && usuarioObj.getSenha().equals(senha)) {
			loginRepository.registrarUltimoAcessoAutorizado(usuario);
			return this.acessoPermitido(usuario);

		} else {
			return false;
		}
	}

	private boolean acessoPermitido(String idUsuario) {
		Long ultimoAcesso = loginRepository.AcessarUltimoAcessoAutorizado(idUsuario);
		if (ultimoAcesso != null) {
			long horaAtual = System.currentTimeMillis();
			long tempoDecorrido = horaAtual - ultimoAcesso;
			return tempoDecorrido <= 30 * 60 * 1000;
		}
		return false;
	}

}