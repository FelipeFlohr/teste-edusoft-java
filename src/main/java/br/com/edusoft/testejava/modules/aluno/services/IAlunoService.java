package br.com.edusoft.testejava.modules.aluno.services;

import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;

public interface IAlunoService {
	public GravarResultado gerarGravarResultado();
	public GravarResultadoReturn postGravarResultado(GravarResultado gravarResultado);
}
