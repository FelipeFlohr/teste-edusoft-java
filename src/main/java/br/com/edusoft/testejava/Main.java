package br.com.edusoft.testejava;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
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

		final IAlunoService alunoService = injector.getInstance(IAlunoService.class);

		logger.warn("Obtendo o resultado...");
		final GravarResultado gravarResultado = alunoService.gerarGravarResultado();
		logger.okay("Resultado obtido.");

		logger.warn("Fazendo o POST do resultado...");
		final GravarResultadoReturn gravarResultadoReturn = alunoService.postGravarResultado(gravarResultado);
		logger.log("Feito o POST do resultado. Linhas alteradas: " + gravarResultadoReturn.getLinhasAfetadas(), gravarResultadoReturn.getLinhasAfetadas() == 3 ? LogLevel.OKAY : LogLevel.WARN);
		logger.warn("Encerrando aplicação...");
	}
}
