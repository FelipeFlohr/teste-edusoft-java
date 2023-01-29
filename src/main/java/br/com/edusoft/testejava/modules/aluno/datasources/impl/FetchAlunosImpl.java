package br.com.edusoft.testejava.modules.aluno.datasources.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunoToken;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.mappers.ConsultaAlunosMapper;
import br.com.edusoft.testejava.modules.aluno.models.ConsultaAlunos;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;
import br.com.edusoft.testejava.utils.logger.ILogger;

@Singleton
public class FetchAlunosImpl implements IFetchAlunos {
	private final ISyncHttpService httpService;
	private final IFetchAlunoToken tokenDatasource;
	private final ILogger logger;

	@Inject
	public FetchAlunosImpl(ISyncHttpService httpService, IFetchAlunoToken tokenDatasource, ILogger logger) {
		this.httpService = httpService;
		this.tokenDatasource = tokenDatasource;
		this.logger = logger;
	}

	@Override
	public ConsultaAlunos fetchAlunos() {
		logger.info("Obtendo os alunos...");

		final String token = tokenDatasource.fetchToken();
		final var alunos = httpService.post(AlunoUrls.fetchAlunos)
				.addHeader("token", token)
				.addHeader("Content-Type", "application/json")
				.callJsonResponse();

		final ConsultaAlunos alunosMapped = ConsultaAlunosMapper.fromMap(alunos);

		logger.okay("Alunos obtidos.");
		return alunosMapped;
	}
}
