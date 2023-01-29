package br.com.edusoft.testejava.modules.aluno.services.impl;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchGravaResultadoToken;
import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;
import br.com.edusoft.testejava.modules.aluno.mappers.GravarResultadoMapper;
import br.com.edusoft.testejava.modules.aluno.mappers.GravarResultadoReturnMapper;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;
import br.com.edusoft.testejava.modules.aluno.models.ResultadoAluno;
import br.com.edusoft.testejava.modules.aluno.services.IAlunoService;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;
import br.com.edusoft.testejava.modules.http.entities.post.HttpPostRequestBuilder;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;

@Singleton
public class AlunoServiceImpl implements IAlunoService {
	private final IAlunoEntity entity;
	private final IFetchAlunos alunosFetcher;
	private final IEnvironmentSettings envSettings;
	private final IFetchGravaResultadoToken gravaResultadoTokenFetcher;
	private final ISyncHttpService httpService;

	@Inject
	public AlunoServiceImpl(IAlunoEntity entity, IFetchAlunos alunosFetcher, IEnvironmentSettings envSettings,
			IFetchGravaResultadoToken gravaResultadoTokenFetcher, ISyncHttpService httpService) {
		this.entity = entity;
		this.alunosFetcher = alunosFetcher;
		this.envSettings = envSettings;
		this.gravaResultadoTokenFetcher = gravaResultadoTokenFetcher;
		this.httpService = httpService;
	}

	@Override
	public GravarResultado gerarGravarResultado() {
		final List<Aluno> alunos = alunosFetcher.fetchAlunos().getAlunos();
		final List<ResultadoAluno> resultadoAlunos = alunos.stream().map(aluno -> {
			final ResultadoAluno resultadoAluno = new ResultadoAluno();
			resultadoAluno.setCodAluno(aluno.getCod());
			resultadoAluno.setMedia(entity.getMedia(aluno));
			resultadoAluno.setResultado(entity.getAlunoStatus(aluno));
			resultadoAluno.setSeuNome(envSettings.getConfig().getTesteRealizante().getNome());

			return resultadoAluno;
		}).toList();

		final GravarResultado gravarResultado = new GravarResultado(resultadoAlunos);
		return gravarResultado;
	}

	@Override
	public GravarResultadoReturn postGravarResultado(GravarResultado gravarResultado) {
		final Map<String, Object> gravarResultadoMap = GravarResultadoMapper.toMap(gravarResultado);
		final String gravaResultadoToken = gravaResultadoTokenFetcher.fetchToken();

		final HttpPostRequestBuilder httpPost = httpService.post(AlunoUrls.gravaResultadoUrl)
				.setBody(gravarResultadoMap);
		final Map<String, Object> gravaResultadoMapReturn = httpPost.addHeader("token", gravaResultadoToken)
				.callJsonResponse();

		final GravarResultadoReturn gravarResultadoReturn = GravarResultadoReturnMapper
				.fromMap(gravaResultadoMapReturn);
		return gravarResultadoReturn;
	}
}
