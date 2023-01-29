package br.com.edusoft.testejava.modules.aluno.token.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.token.services.IAlunoTokenService;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.get.services.IHttpGetService;

@Singleton
public class AlunoTokenServiceImpl implements IAlunoTokenService {
	private final IHttpGetService httpService;
	private final IEnvironmentSettings envSettings;

	@Inject
	public AlunoTokenServiceImpl(IHttpGetService httpService, IEnvironmentSettings envSettings) {
		this.httpService = httpService;
		this.envSettings = envSettings;
	}

	@Override
	public String getToken() {
		final String token = httpService.addHeader("usuario", envSettings.getConfig().getTokenGeneration().getUsuario())
			.addHeader("senha", envSettings.getConfig().getTokenGeneration().getSenha())
			.callStringResponse(AlunoUrls.tokenUrl);
		return token;
	}
}
