package br.com.edusoft.testejava.modules.env.impl;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.env.config.EnvironmentSettingsConfig;
import br.com.edusoft.testejava.modules.env.config.TesteRealizanteConfig;
import br.com.edusoft.testejava.modules.env.config.TokenGenerationConfig;
import br.com.edusoft.testejava.modules.env.exceptions.NullPropertyException;
import br.com.edusoft.testejava.modules.env.exceptions.PropertiesNotFoundException;
import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.LogLevel;

@Singleton
public class EnvironmentSettingsImpl implements IEnvironmentSettings {
	private final ILogger logger;
	private final Properties props;
	private final EnvironmentSettingsConfig config;

	@Inject
	public EnvironmentSettingsImpl(ILogger logger) {
		this.logger = logger;
		this.props = this.getProperties();
		this.config = generateConfig();
	}

	public EnvironmentSettingsConfig getConfig() {
		return this.config;
	}

	private EnvironmentSettingsConfig generateConfig() {
		final TokenGenerationConfig tokenGeneration = new TokenGenerationConfig(this.getStrProp("TOKEN_GENERATION_USUARIO"), this.getStrProp("TOKEN_GENERATION_SENHA"));
		final TesteRealizanteConfig testeRealizante = new TesteRealizanteConfig(this.getStrProp("TESTE_REALIZANTE_NOME"));

		final EnvironmentSettingsConfig res = new EnvironmentSettingsConfig(tokenGeneration, testeRealizante);
		return res;
	}

	private String getStrProp(String key) {
		final String prop = props.getProperty(key);
		if (prop == null) {
			logger.log("A chave " + key + " possui um valor nulo nas configurações da aplicação.", LogLevel.FATAL);
			throw new NullPropertyException(key);
		}
		if (prop.trim().equals("")) {
			logger.log("A chave " + key + " possui um valor nulo nas configurações da aplicação.", LogLevel.FATAL);
			throw new NullPropertyException(key);
		}
		return prop;
	}

	private Properties getProperties() {
		Properties propsObj = new Properties();
		try {
			propsObj.load(EnvironmentSettingsImpl.class.getClassLoader().getResourceAsStream("application.properties"));
			return propsObj;
		} catch (IOException e) {
			logger.log("Não foi possível carregar o arquivo \"application.properties\" nos recursos da aplicação. Por favor, garanta a criação do arquivo.", LogLevel.FATAL);
			throw new PropertiesNotFoundException();
		}
	}
}
