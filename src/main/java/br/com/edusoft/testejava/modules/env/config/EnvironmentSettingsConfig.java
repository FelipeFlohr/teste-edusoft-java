package br.com.edusoft.testejava.modules.env.config;

public class EnvironmentSettingsConfig {
	private final TokenGenerationConfig tokenGeneration;
	private final TesteRealizanteConfig testeRealizante;

	public EnvironmentSettingsConfig(TokenGenerationConfig tokenGeneration, TesteRealizanteConfig testeRealizante) {
		this.tokenGeneration = tokenGeneration;
		this.testeRealizante = testeRealizante;
	}

	public TokenGenerationConfig getTokenGeneration() {
		return tokenGeneration;
	}

	public TesteRealizanteConfig getTesteRealizante() {
		return testeRealizante;
	}
}
