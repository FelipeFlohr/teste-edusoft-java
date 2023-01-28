package br.com.edusoft.testejava.env.config;

public class EnvironmentSettingsConfig {
	private final TokenGenerationConfig tokenGeneration;
	private final FetchAlunosConfig fetchAlunos;

	public EnvironmentSettingsConfig(TokenGenerationConfig tokenGeneration, FetchAlunosConfig fetchAlunos) {
		this.tokenGeneration = tokenGeneration;
		this.fetchAlunos = fetchAlunos;
	}

	public TokenGenerationConfig getTokenGeneration() {
		return tokenGeneration;
	}

	public FetchAlunosConfig getFetchAlunos() {
		return fetchAlunos;
	}
}
