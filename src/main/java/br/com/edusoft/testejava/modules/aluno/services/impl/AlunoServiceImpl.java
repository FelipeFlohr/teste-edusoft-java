package br.com.edusoft.testejava.modules.aluno.services.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;
import br.com.edusoft.testejava.modules.aluno.models.ResultadoAluno;
import br.com.edusoft.testejava.modules.aluno.services.IAlunoService;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;

@Singleton
public class AlunoServiceImpl implements IAlunoService {
	private final IAlunoEntity entity;
	private final IFetchAlunos alunosFetcher;
	private final IEnvironmentSettings envSettings;

	@Inject
	public AlunoServiceImpl(IAlunoEntity entity, IFetchAlunos alunosFetcher, IEnvironmentSettings envSettings) {
		this.entity = entity;
		this.alunosFetcher = alunosFetcher;
		this.envSettings = envSettings;
	}

	@Override
	public GravarResultado gerarGravarResultado() {
		final List<Aluno> alunos = alunosFetcher.fetchAlunos().getAlunos();
		final List<ResultadoAluno> resultadoAlunos = alunos.stream()
				.map(aluno -> {
					final ResultadoAluno resultadoAluno = new ResultadoAluno();
					resultadoAluno.setCodAluno(aluno.getCod());
					resultadoAluno.setMedia(entity.getMedia(aluno));
					resultadoAluno.setResultado(entity.getAlunoStatus(aluno));
					resultadoAluno.setSeuNome(envSettings.getConfig().getTesteRealizante().getNome());

					return resultadoAluno;
				})
				.toList();

		final GravarResultado gravarResultado = new GravarResultado(resultadoAlunos);
		return gravarResultado;
	}

	@Override
	public GravarResultadoReturn postGravarResultado(GravarResultado gravarResultado) {
		// TODO Auto-generated method stub
		return null;
	}
}
