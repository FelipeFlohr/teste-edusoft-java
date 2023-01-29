package br.com.edusoft.testejava.modules.aluno.datasources.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunoToken;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;

@Singleton
public class FetchAlunoTokenImpl implements IFetchAlunoToken {
	private final ISyncHttpService httpService;
	private final IEnvironmentSettings envSettings;

	@Inject
	public FetchAlunoTokenImpl(ISyncHttpService httpService, IEnvironmentSettings envSettings) {
		this.httpService = httpService;
		this.envSettings = envSettings;
	}

	@Override
	public String fetchToken() {
		final String getToken = this.httpService.get(AlunoUrls.tokenUrl)
				.addHeader("usuario", envSettings.getConfig().getTokenGeneration().getUsuario())
				.addHeader("senha", envSettings.getConfig().getTokenGeneration().getSenha())
				.callStringResponse();
		return getToken;
	}
}
