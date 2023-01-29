package br.com.edusoft.testejava;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;
import br.com.edusoft.testejava.modules.aluno.services.IAlunoService;
import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.LogLevel;

public class Main {
	public static void main(String[] args) {
		// Configura o Injector do Guice
		final Injector injector = Guice.createInjector(new Container());
		final ILogger logger = injector.getInstance(ILogger.class);

		logger.warn("Início da aplicação");

		final IFetchAlunos alunoFetcher = injector.getInstance(IFetchAlunos.class);
		final IAlunoService alunoService = injector.getInstance(IAlunoService.class);

		logger.warn("Obtendo o resultado...");
		final List<Aluno> alunos = alunoFetcher.fetchAlunos().getAlunos();
		final GravarResultado gravarResultado = alunoService.gerarGravarResultado(alunos);
		logger.okay("Resultado obtido.");

		logger.warn("Fazendo o POST do resultado...");
		final GravarResultadoReturn gravarResultadoReturn = alunoService.postGravarResultado(gravarResultado);
		logger.log("Feito o POST do resultado. Linhas alteradas: " + gravarResultadoReturn.getLinhasAfetadas(), gravarResultadoReturn.getLinhasAfetadas() == 3 ? LogLevel.OKAY : LogLevel.WARN);

		logger.info("Veja o relatório abaixo:\n\n");
		System.out.println(alunoService.generateAlunosRelatorio(alunos));
		System.out.println("\n\n");
		logger.warn("Encerrando aplicação...");
	}
}
