package br.com.edusoft.testejava.env.config;

public class TokenGenerationConfig {
	private final String usuario;
	private final String senha;

	public TokenGenerationConfig(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
}
