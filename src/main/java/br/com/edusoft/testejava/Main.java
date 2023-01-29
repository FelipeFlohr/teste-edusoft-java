package br.com.edusoft.testejava;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchGravaResultadoToken;
import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;
import br.com.edusoft.testejava.modules.aluno.mappers.GravarResultadoMapper;
import br.com.edusoft.testejava.modules.aluno.models.ConsultaAlunos;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.ResultadoAluno;
import br.com.edusoft.testejava.modules.env.IEnvironmentSettings;

public class Main {
	public static void main(String[] args) {
		// Configura o Injector do Guice
		final Injector injector = Guice.createInjector(new Container());

		final IFetchAlunos fetcher = injector.getInstance(IFetchAlunos.class);
		final IAlunoEntity alunoEntity = injector.getInstance(IAlunoEntity.class);
		final IEnvironmentSettings envSettings = injector.getInstance(IEnvironmentSettings.class);

		final ConsultaAlunos consultaAlunos = fetcher.fetchAlunos();
		final List<ResultadoAluno> resultadoAluno = new ArrayList<>();

		consultaAlunos.getAlunos()
			.forEach(aluno -> {
				final ResultadoAluno res = new ResultadoAluno();
				res.setCodAluno(aluno.getCod());
				res.setMedia(alunoEntity.getMedia(aluno));
				res.setResultado(alunoEntity.getAlunoStatus(aluno));
				res.setSeuNome(envSettings.getConfig().getTesteRealizante().getNome());

				resultadoAluno.add(res);
			});

		final GravarResultado gravarResultado = new GravarResultado(resultadoAluno);
		final String jsonGravarResultado = GravarResultadoMapper.toStringJson(gravarResultado);
		System.out.println(jsonGravarResultado);
	}
}
