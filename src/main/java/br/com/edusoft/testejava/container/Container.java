package br.com.edusoft.testejava.container;

import com.google.inject.AbstractModule;

import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunoToken;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.datasources.impl.FetchAlunoTokenImpl;
import br.com.edusoft.testejava.modules.aluno.datasources.impl.FetchAlunosImpl;
import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;
import br.com.edusoft.testejava.modules.aluno.entities.impl.AlunoEntityProvider;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.env.impl.EnvironmentSettingsImpl;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;
import br.com.edusoft.testejava.modules.http.services.impl.SyncHttpServiceImpl;
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

		// Sync HTTP Service
		bind(ISyncHttpService.class)
			.to(SyncHttpServiceImpl.class);

		// Aluno
		bind(IFetchAlunoToken.class)
			.to(FetchAlunoTokenImpl.class);
		bind(IFetchAlunos.class)
			.to(FetchAlunosImpl.class);
		bind(IAlunoEntity.class)
			.toProvider(AlunoEntityProvider.class);
	}
}
