package br.com.edusoft.testejava.container;

import com.google.inject.AbstractModule;

import br.com.edusoft.testejava.modules.aluno.token.services.IAlunoTokenService;
import br.com.edusoft.testejava.modules.aluno.token.services.impl.AlunoTokenServiceImpl;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.env.impl.EnvironmentSettingsImpl;
import br.com.edusoft.testejava.modules.http.get.services.IHttpGetService;
import br.com.edusoft.testejava.modules.http.get.services.impl.HttpGetServiceProvider;
import br.com.edusoft.testejava.modules.http.post.services.IHttpPostService;
import br.com.edusoft.testejava.modules.http.post.services.impl.HttpPostServiceProvider;
import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.impl.LoggerImpl;

public class Container extends AbstractModule {
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
