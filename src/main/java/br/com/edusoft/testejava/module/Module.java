package br.com.edusoft.testejava.module;

import com.google.inject.AbstractModule;

import br.com.edusoft.testejava.env.IEnvironmentSettings;
import br.com.edusoft.testejava.env.impl.EnvironmentSettingsImpl;
import br.com.edusoft.testejava.services.aluno.token.IAlunoTokenService;
import br.com.edusoft.testejava.services.aluno.token.impl.AlunoTokenServiceImpl;
import br.com.edusoft.testejava.services.http.get.IHttpGetService;
import br.com.edusoft.testejava.services.http.get.impl.HttpGetServiceProvider;
import br.com.edusoft.testejava.services.http.post.IHttpPostService;
import br.com.edusoft.testejava.services.http.post.impl.HttpPostServiceProvider;
import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.impl.LoggerImpl;

public class Module extends AbstractModule {
	@Override
	protected void configure() {
		// Logger
		bind(ILogger.class)
			.to(LoggerImpl.class);

		// Environment Settings
		bind(IEnvironmentSettings.class)
			.to(EnvironmentSettingsImpl.class);

		// HTTP Post Service
		bind(IHttpPostService.class)
			.toProvider(HttpPostServiceProvider.class);

		// HTTP Get Service
		bind(IHttpGetService.class)
			.toProvider(HttpGetServiceProvider.class);

		// Alunos Token Service
		bind(IAlunoTokenService.class)
			.to(AlunoTokenServiceImpl.class);
	}
}
