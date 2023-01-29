package br.com.edusoft.testejava.modules.aluno.datasources.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunoToken;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;
import br.com.edusoft.testejava.utils.logger.ILogger;

@Singleton
public class FetchAlunoTokenImpl implements IFetchAlunoToken {
	private final ISyncHttpService httpService;
	private final IEnvironmentSettings envSettings;
	private final ILogger logger;

	@Inject
	public FetchAlunoTokenImpl(ISyncHttpService httpService, IEnvironmentSettings envSettings, ILogger logger) {
		this.httpService = httpService;
		this.envSettings = envSettings;
		this.logger = logger;
	}

	@Override
	public String fetchToken() {
		logger.info("Obtendo o Token para a consulta dos alunos...");

		final String getToken = this.httpService.get(AlunoUrls.alunoTokenUrl)
				.addHeader("usuario", envSettings.getConfig().getTokenGeneration().getUsuario())
				.addHeader("senha", envSettings.getConfig().getTokenGeneration().getSenha())
				.callStringResponse();

		logger.okay("Obtido Token para a consulta dos alunos.");
		return getToken;
	}
}
