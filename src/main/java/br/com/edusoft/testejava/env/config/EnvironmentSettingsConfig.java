package br.com.edusoft.testejava.env.config;

public class EnvironmentSettingsConfig {
	private final TokenGenerationConfig tokenGeneration;

	public EnvironmentSettingsConfig(TokenGenerationConfig tokenGeneration) {
		this.tokenGeneration = tokenGeneration;
	}

	public TokenGenerationConfig getTokenGeneration() {
		return tokenGeneration;
	}
}
