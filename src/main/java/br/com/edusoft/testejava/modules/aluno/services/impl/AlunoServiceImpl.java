package br.com.edusoft.testejava.modules.aluno.services.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.constants.AlunoUrls;
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
	private final IEnvironmentSettings envSettings;
	private final IFetchGravaResultadoToken gravaResultadoTokenFetcher;
	private final ISyncHttpService httpService;

	@Inject
	public AlunoServiceImpl(IAlunoEntity entity, IEnvironmentSettings envSettings,
			IFetchGravaResultadoToken gravaResultadoTokenFetcher, ISyncHttpService httpService) {
		this.entity = entity;
		this.envSettings = envSettings;
		this.gravaResultadoTokenFetcher = gravaResultadoTokenFetcher;
		this.httpService = httpService;
	}

	@Override
	public GravarResultado gerarGravarResultado(List<Aluno> alunos) {
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

	@Override
	public String generateAlunoRelatorio(Aluno aluno) {
		final DecimalFormat decimalFormat = new DecimalFormat("#.00");

		final String nomeAluno = aluno.getNome();
		final String notas = aluno.getNota()
				.stream()
				.map(nota -> nota.getNota())
				.map(nota -> decimalFormat.format(nota))
				.toList()
				.toString();
		final String totalFaltas = aluno.getNota()
				.stream()
				.map(nota -> nota.getFaltas())
				.reduce((prev, curr) -> prev + curr)
				.get()
				.toString();
		final String media = decimalFormat.format(entity.getMedia(aluno));
		final String resultado = entity.getAlunoStatus(aluno).getDisplayText();

		final String formatted = new StringBuilder()
				.append("------------------------------------\n")
				.append("Nome do aluno: " + nomeAluno + "\n")
				.append("Notas: " + notas + "\n")
				.append("Total de faltas: " + totalFaltas + "\n")
				.append("Média: " + media + "\n")
				.append("Resultado: " + resultado + "\n")
				.append("------------------------------------\n")
				.toString();
		return formatted;
	}

	@Override
	public String generateAlunosRelatorio(List<Aluno> alunos) {
		final String formatted = alunos.stream()
				.map(aluno -> this.generateAlunoRelatorio(aluno))
				.reduce((prev, curr) -> prev + curr)
				.get();
		return formatted;
	}
}
