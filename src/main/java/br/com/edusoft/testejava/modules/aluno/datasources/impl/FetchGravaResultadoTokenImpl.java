package br.com.edusoft.testejava.modules.aluno.datasources.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchGravaResultadoToken;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;
import br.com.edusoft.testejava.utils.logger.ILogger;

@Singleton
public class FetchGravaResultadoTokenImpl implements IFetchGravaResultadoToken {
	private final ISyncHttpService httpService;
	private final IEnvironmentSettings envSettings;
	private final ILogger logger;

	@Inject
	public FetchGravaResultadoTokenImpl(ISyncHttpService httpService, IEnvironmentSettings envSettings, ILogger logger) {
		this.httpService = httpService;
		this.envSettings = envSettings;
		this.logger = logger;
	}

	@Override
	public String fetchToken() {
		logger.info("Obtendo o Token para gravar o resultado...");

		final String usuario = envSettings.getConfig().getTokenGeneration().getUsuario();
		final String senha = envSettings.getConfig().getTokenGeneration().getSenha();

		final String token = httpService.get(AlunoUrls.gravaTokenUrl)
				.addHeader("usuario", usuario)
				.addHeader("senha", senha)
				.callStringResponse();

		logger.okay("Obtido Token para gravar o resultado.");
		return token;
	}
}
