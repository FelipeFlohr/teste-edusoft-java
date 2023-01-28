package br.com.edusoft.testejava.env.config;

public class FetchAlunosConfig {
	private final String token;

	public FetchAlunosConfig(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
