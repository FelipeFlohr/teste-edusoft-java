package br.com.edusoft.testejava.modules.aluno.datasources.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchGravaResultadoToken;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;

@Singleton
public class FetchGravaResultadoTokenImpl implements IFetchGravaResultadoToken {
	private final ISyncHttpService httpService;
	private final IEnvironmentSettings envSettings;

	@Inject
	public FetchGravaResultadoTokenImpl(ISyncHttpService httpService, IEnvironmentSettings envSettings) {
		this.httpService = httpService;
		this.envSettings = envSettings;
	}

	@Override
	public String fetchToken() {
		final String usuario = envSettings.getConfig().getTokenGeneration().getUsuario();
		final String senha = envSettings.getConfig().getTokenGeneration().getSenha();

		final String token = httpService.get(AlunoUrls.gravaTokenUrl)
				.addHeader("usuario", usuario)
				.addHeader("senha", senha)
				.callStringResponse();
		return token;
	}
}
